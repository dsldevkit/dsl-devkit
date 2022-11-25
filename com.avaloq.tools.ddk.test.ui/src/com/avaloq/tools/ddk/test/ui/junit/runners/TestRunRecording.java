/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.test.ui.junit.runners;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.ui.PlatformUI;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.avaloq.tools.ddk.test.core.AbstractTestStep;
import com.avaloq.tools.ddk.test.core.TestStepListener;
import com.google.common.collect.Sets;
import com.google.common.primitives.Longs;


/**
 * Recording of a test run.
 */
// CHECKSTYLE:OFF
public class TestRunRecording extends RunListener implements TestStepListener, MouseListener {
  private static final String FILE_INFORMATION_SEPARATOR = "__";
  private static final int MOUSE_POINTER_SIZE = 24;
  private static final int INFO_BORDER_SIZE = 3;
  // CHECKSTYLE:ON
  // private static final int FILE_READ_BUFFER = 2048;
  private static final int INFO_FONT_SIZE = 18;
  private static final Logger LOGGER = Logger.getLogger(TestRunRecording.class.getSimpleName());
  private static final String IMAGE_TYPE = SWTBotPreferences.SCREENSHOT_FORMAT;
  private static final String IMAGE_FILE_EXTENSION = '.' + IMAGE_TYPE;
  private static final String CALL_STACK_FILE_EXTENSION = ".txt";
  private static boolean screenshotsCleared;
  private final Robot robot;
  private final Toolkit toolkit = Toolkit.getDefaultToolkit();
  private static final int STACK_DEPTH = 80;
  private static final ThreadMXBean THREAD_BEAN = ManagementFactory.getThreadMXBean();

  /**
   * The recording interval defines the (approximate) maximum interval in milliseconds between two consecutive screenshots.
   */
  public static final long DEFAULT_RECORDING_INTERVAL = 500;
  private static final int MINIMAL_RECORDING_INTERVAL = 100;
  private long recordingInterval;

  private final String screenshotFolder;
  private final Class<?> testClass;
  private String testClassName;
  private String testMethodName;
  private AbstractTestStep currentTestStep;
  private TestStepState currentTestStepState;

  private Thread captureThread;
  private final Object lock = new Object(); // used for synchronizing with anonymous classes
  private boolean captureThreadRunning;
  private long lastCaptureTime;

  private boolean testFailed;
  /** The associated exception in case of a failure. */
  private Throwable exception;
  private boolean testStepStarted;
  private boolean mouseClicked;
  private boolean mousePressedDown;
  private MouseEvent mouseEvent;

  /**
   * Creates a new instance of {@link TestRunRecording}.
   *
   * @param testClass
   *          the class under test
   * @param screenshotFolder
   *          the folder dedicated for screenshots
   */
  public TestRunRecording(final Class<?> testClass, final String screenshotFolder) {
    this(testClass, screenshotFolder, DEFAULT_RECORDING_INTERVAL);
  }

  /**
   * Creates a new instance of {@link TestRunRecording}.
   *
   * @param testClass
   *          the class under test
   * @param screenshotFolder
   *          the folder dedicated for screenshots
   * @param recordingInterval
   *          the interval in milliseconds between two consecutive screenshots
   */
  public TestRunRecording(final Class<?> testClass, final String screenshotFolder, final long recordingInterval) {
    this.testClass = testClass;
    this.screenshotFolder = screenshotFolder;
    setRecordingInterval(recordingInterval);
    try {
      robot = new Robot();
    } catch (AWTException e) {
      throw new WrappedException("TestRunRecording cannot be used in a headless environment.", e);
    }
  }

  /**
   * Sets the interval in milliseconds between two consecutive screenshots.
   *
   * @param recordingInterval
   *          the new recording interval in milliseconds
   */
  public final void setRecordingInterval(final long recordingInterval) {
    this.recordingInterval = Math.max(recordingInterval, MINIMAL_RECORDING_INTERVAL);
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
  public void testStarted(final Description description) {
    if (!screenshotsCleared) {
      deleteDirectoryContents(new File(screenshotFolder));
      screenshotsCleared = true;
    }
    testFailed = false;
    testStepStarted = false;
    if (description.getTestClass() != null) {
      testClassName = description.getTestClass().getName();
    } else {
      testClassName = testClass.getSimpleName();
    }
    testMethodName = description.getMethodName();
    AbstractTestStep.registerTestStepListener(this);
    UIThreadRunnable.syncExec(new VoidResult() {
      @Override
      public void run() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().addMouseListener(TestRunRecording.this);
      }
    });
    start();
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
  public void testFinished(final Description description) {
    stop();
    AbstractTestStep.removeTestStepListener(this);
    UIThreadRunnable.syncExec(new VoidResult() {
      @Override
      public void run() {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().removeMouseListener(TestRunRecording.this);
      }
    });
    if (!testFailed) {
      deleteScreenshots();
    }
  }

  /** {@inheritDoc} */
  @Override
  @SuppressWarnings("PMD.JUnit4TestShouldUseTestAnnotation")
  public void testFailure(final Failure failure) {
    methodInvokeFailure(failure.getException());
  }

  /** {@inheritDoc} */
  @Override
  public void stepStateChanged(final AbstractTestStep testStep, final TestStepState testStepState, final Throwable throwable) {
    synchronized (lock) {
      this.currentTestStep = testStep;
      this.currentTestStepState = testStepState;
      testStepStarted = true;
      if (TestStepState.FAILED == testStepState || TestStepState.ERRORED == testStepState) {
        exception = throwable;
        testFailed = true;
      }
      createScreenshot(0); // always create a screenshot when the step state changes
      createCallStack(0);
    }
  }

  /**
   * Handles an exception caused by invoking the test method.
   *
   * @param throwable
   *          the thrown {@link Throwable}, must not be {@code null}
   */
  public void methodInvokeFailure(final Throwable throwable) {
    synchronized (lock) {
      if (!testFailed) {
        exception = throwable;
        testFailed = true;
        createScreenshot(0); // immediately create a screenshot when a test failure is reported
        createCallStack(0);
      }
    }
  }

  /**
   * Creates a new asynchronous thread that regularly creates screenshots of the running test environment.
   * Stops a previously started and still running thread.
   */
  private void start() {
    if (captureThreadRunning) {
      stop();
    }
    deleteScreenshots();
    captureThreadRunning = true;
    captureThread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (captureThreadRunning) {
          synchronized (lock) {
            // if test steps are used, only create continuous screenshots in the run step state; for all other states 1 screenshot is enough
            if (currentTestStep != null && TestStepState.RUN == currentTestStepState) {
              createScreenshot(recordingInterval);
              createCallStack(recordingInterval);
            }
          }
          try {
            Thread.sleep(recordingInterval);
          } catch (final InterruptedException e) {
            // ignore, because #createScreenshot will make sure that a minimal recording interval is retained
          }
        }
      }
    });
    captureThread.start();
  }

  /**
   * Stops the asynchronous thread from creating screenshots.
   */
  private void stop() {
    captureThreadRunning = false;
    try {
      captureThread.join();
    } catch (final InterruptedException e) {
      // ignore: the capture thread is no longer important and shall silently be removed
    }
  }

  /**
   * Creates a new screenshot, if the last screenshot is older than the minimal recording interval.
   */
  private void createScreenshot() {
    createScreenshot(MINIMAL_RECORDING_INTERVAL);
  }

  /**
   * Creates a new screenshot, if the last screenshot is older than the minimal recording interval.
   */
  private void createCallStack() {
    createCallStack(MINIMAL_RECORDING_INTERVAL);
  }

  /**
   * Creates a new screenshot, if the last screenshot is older than the provided captureInterval.
   *
   * @param captureInterval
   *          the minimal interval in milliseconds which determines if the screenshot is created
   */
  private void createScreenshot(final long captureInterval) {
    synchronized (lock) {
      if (System.currentTimeMillis() > lastCaptureTime + captureInterval) {
        lastCaptureTime = System.currentTimeMillis(); // save time at beginning, so that a failed screenshot results in a skipped frame
        final BufferedImage image = capture();
        process(image);
        try {
          store(image);
        } catch (IOException e) {
          LOGGER.warning("Could not create screenshot: " + getImageFileName());
        }
      }
    }
  }

  /**
   * Creates a new call stack record, if the last record is older than the provided captureInterval.
   *
   * @param captureInterval
   *          the minimal interval in milliseconds which determines if the call stack is created
   */
  private void createCallStack(final long captureInterval) {
    synchronized (lock) {
      if (System.currentTimeMillis() > lastCaptureTime + captureInterval) {
        lastCaptureTime = System.currentTimeMillis(); // save time at beginning
        final String image = captureCallStack();
        try {
          storeCallStack(image);
        } catch (IOException e) {
          LOGGER.warning("Could not create screenshot: " + getImageFileName());
        }
      }
    }
  }

  /**
   * Captures a screenshot.
   *
   * @return the {@link BufferedImage}, never {@code null}
   */
  private BufferedImage capture() {
    final Rectangle screenBounds = new Rectangle(toolkit.getScreenSize());
    return robot.createScreenCapture(screenBounds);
  }

  /**
   * Captures a call stack.
   *
   * @return the call stack with test status information, never {@code null}
   */
  @SuppressWarnings("PMD.InsufficientStringBufferDeclaration")
  private String captureCallStack() {
    StringBuilder trace = new StringBuilder();
    // Same info as on the screenshot
    trace.append("TEST: " + testClassName + "." + testMethodName + "\r\n");
    if (testStepStarted && currentTestStep != null && currentTestStepState != null) {
      trace.append("TEST STEP: " + currentTestStep.getName() + ": " + currentTestStepState.toString() + "\r\n");
      if (testFailed && exception != null) {
        trace.append("TEST FAILED: " + exception.getClass().getSimpleName() + ": " + exception.getMessage() + "\r\n");
      }
    }
    trace.append(getThreadInfo());
    return trace.toString();
  }

  /**
   * Gets the task name.
   *
   * @param id
   *          the id
   * @param name
   *          the name
   * @return the task name
   */
  private static String getTaskName(final long id, final String name) {
    if (name == null) {
      return Long.toString(id);
    }
    return id + " (" + name + ")";
  }

  /**
   * Gathers the thread ids of threads that look deadlocked.
   *
   * @return the thread ids of deadlocked threads
   */
  private static Set<Long> getDeadlockThreadIds() {
    Set<Long> deadlockedThreads = Sets.newHashSet();
    long[] dead = THREAD_BEAN.findDeadlockedThreads();
    if (dead != null) {
      deadlockedThreads.addAll(Longs.asList(dead));
    }
    dead = THREAD_BEAN.findMonitorDeadlockedThreads();
    if (dead != null) {
      deadlockedThreads.addAll(Longs.asList(dead));
    }
    return deadlockedThreads;
  }

  /**
   * Prints the owned locks for the thread.
   * Must have explicitly obtained Monitor and Synchronizer information for anything to show up from here.
   *
   * @param info
   *          the {@code:ThreadInfo} for the thread in question, must not be {@code:null}
   * @return the owned lock information
   */
  private static String getOwnedLockInfo(final ThreadInfo info) {
    StringBuilder trace = new StringBuilder("  Holding locks for:\r\n");
    for (LockInfo lock : info.getLockedSynchronizers()) {
      trace.append("    " + lock.toString() + "\r\n");
    }
    for (MonitorInfo monitor : info.getLockedMonitors()) {
      trace.append("    " + monitor.toString() + "\r\n");
    }
    return trace.toString();
  }

  /**
   * Print the thread's stack trace.
   *
   * @param info
   *          the {@code:ThreadInfo} for the thread in question, must not be {@code:null}
   * @return the thread stack trace
   */
  @SuppressWarnings("PMD.InsufficientStringBufferDeclaration")
  private static String getCallStackTrace(final ThreadInfo info) {
    StringBuilder trace = new StringBuilder("  Stack:\r\n");
    int frameCount = 0;
    for (StackTraceElement frame : info.getStackTrace()) {
      trace.append("    " + frame.toString() + "\r\n");
      // Can't request lock information AND limit call stack size with only one ThreadInfo request. Fake it instead.
      if (frameCount++ >= STACK_DEPTH) {
        trace.append("    ...\r\n");
        break;
      }
    }
    return trace.toString();
  }

  /**
   * Print all of the thread's information and stack traces.
   *
   * @return the thread info
   */
  private static String getThreadInfo() {
    boolean contention = THREAD_BEAN.isThreadContentionMonitoringEnabled();
    Set<Long> deadlockedThreads = getDeadlockThreadIds();
    ThreadInfo[] threadInfos = THREAD_BEAN.dumpAllThreads(true, true);
    StringBuilder trace = new StringBuilder().append(threadInfos.length).append(" active threads\r\n");
    for (ThreadInfo info : threadInfos) {
      if (info == null) {
        trace.append("  Inactive\r\n");
        continue;
      }
      boolean isDeadlocked = deadlockedThreads.contains(Long.valueOf(info.getThreadId()));
      trace.append("Thread ").append(getTaskName(info.getThreadId(), info.getThreadName()));
      if (isDeadlocked) {
        trace.append(" <<DEADLOCK>>");
      }
      trace.append("\r\n");
      Thread.State state = info.getThreadState();
      trace.append("  State: " + state + "\r\n");
      trace.append("  Blocked count: " + info.getBlockedCount() + "\r\n");
      trace.append("  Waited count: " + info.getWaitedCount() + "\r\n");
      if (contention) {
        trace.append("  Blocked time: " + info.getBlockedTime() + "\r\n");
        trace.append("  Waited time: " + info.getWaitedTime() + "\r\n");
      }
      if (state == Thread.State.WAITING) {
        trace.append("  Waiting on " + info.getLockName() + "\r\n");
      } else if (state == Thread.State.BLOCKED) {
        trace.append("  Blocked on " + info.getLockName() + "\r\n");
        trace.append("  Blocked by " + getTaskName(info.getLockOwnerId(), info.getLockOwnerName()) + "\r\n");
      }
      if (state == Thread.State.WAITING || state == Thread.State.BLOCKED || isDeadlocked) {
        trace.append(getOwnedLockInfo(info));
      }
      trace.append(getCallStackTrace(info));
    }
    return trace.toString();
  }

  /**
   * Processes the given screenshot by adding corresponding information to the image.
   *
   * @param image
   *          the {@link BufferedImage}, must not be {@code null}
   */
  private void process(final BufferedImage image) {
    if (image == null) {
      return;
    }
    final Graphics2D graphics = image.createGraphics();
    // draw mouse location
    if (mouseEvent != null) {
      drawCrosshair(graphics, mouseEvent.x, mouseEvent.y);
    }
    // draw capture information
    int x = image.getWidth() / 2;
    int y = 0;
    drawInfoBox(graphics, testClassName + "." + testMethodName, x, y);
    if (testStepStarted && currentTestStep != null && currentTestStepState != null) {
      y += getInfoBoxHeight() + INFO_BORDER_SIZE;
      drawInfoBox(graphics, currentTestStep.getName() + ": " + currentTestStepState.toString(), x, y);
      if (testFailed && exception != null) {
        final String message = exception.getMessage();
        if (message != null) {
          y += getInfoBoxHeight() + INFO_BORDER_SIZE;
          drawInfoBox(graphics, exception.getClass().getSimpleName() + ": " + message, x, y);
        }
      }
    }
  }

  /**
   * Draws a crosshair at the given screen coordinates on the captured image.
   *
   * @param graphics
   *          the {@link Graphics2D}, must not be {@code null}
   * @param x
   *          the x screen coordinate
   * @param y
   *          the y screen coordinate
   */
  private void drawCrosshair(final Graphics2D graphics, final int x, final int y) {
    if (mouseClicked) {
      graphics.setColor(Color.GREEN);
    } else if (mousePressedDown) {
      graphics.setColor(Color.RED);
    } else {
      graphics.setColor(Color.BLUE);
    }
    graphics.drawOval(x - MOUSE_POINTER_SIZE / 2, y - MOUSE_POINTER_SIZE / 2, MOUSE_POINTER_SIZE, MOUSE_POINTER_SIZE);
    graphics.drawOval(x - MOUSE_POINTER_SIZE / 2 + 1, y - MOUSE_POINTER_SIZE / 2 + 1, MOUSE_POINTER_SIZE - 2, MOUSE_POINTER_SIZE - 2);
    graphics.drawLine(x, y - MOUSE_POINTER_SIZE / 2, x, y + MOUSE_POINTER_SIZE / 2);
    graphics.drawLine(x - MOUSE_POINTER_SIZE / 2, y, x + MOUSE_POINTER_SIZE / 2, y);
  }

  /**
   * Draws a black box with an info text on the captured image.
   *
   * @param graphics
   *          the {@link Graphics2D}, must not be {@code null}
   * @param info
   *          the text to draw, must not be {@code null}
   * @param x
   *          the center x coordinate of the box on the screenshot image
   * @param y
   *          the top y coordinate of the box on the screenshot image
   */
  private void drawInfoBox(final Graphics2D graphics, final String info, final int x, final int y) {
    graphics.setFont(new Font("Arial", Font.PLAIN, INFO_FONT_SIZE));
    final int stringWidth = graphics.getFontMetrics().stringWidth(info);
    final int leftCoord = x - stringWidth / 2;
    graphics.setColor(Color.BLACK);
    graphics.fillRoundRect(leftCoord - INFO_BORDER_SIZE, y, stringWidth + 2 * INFO_BORDER_SIZE, INFO_FONT_SIZE
        + 2 * INFO_BORDER_SIZE, INFO_BORDER_SIZE, INFO_BORDER_SIZE);
    if (testFailed) {
      graphics.setColor(Color.RED);
    } else {
      graphics.setColor(Color.GREEN);
    }
    graphics.drawString(info, leftCoord, y + INFO_FONT_SIZE + INFO_BORDER_SIZE);
  }

  /**
   * Returns the height an info box has.
   *
   * @return the height an info box has
   */
  private int getInfoBoxHeight() {
    return INFO_FONT_SIZE + 2 * INFO_BORDER_SIZE;
  }

  /**
   * Stores the captured image.
   *
   * @param image
   *          the {@link BufferedImage}, must not be {@code null}
   * @throws IOException
   *           if the image could not be written to file.
   */
  private void store(final BufferedImage image) throws IOException {
    final File screenshotDirectory = new File(getScreenshotDirectory());
    screenshotDirectory.mkdirs();
    ImageIO.write(image, IMAGE_TYPE, new File(getImageFileName()));
  }

  /**
   * Stores the call stack.
   *
   * @param trace
   *          the call stack information to store, must not be {@code null}
   * @throws IOException
   *           if the call stack could not be written to file.
   */
  private void storeCallStack(final String trace) throws IOException {
    // Put it next to screenshots (intentional)
    final File screenshotDirectory = new File(getScreenshotDirectory());
    screenshotDirectory.mkdirs();
    PrintWriter out = new PrintWriter(getCallStackFileName());
    out.print(trace);
    out.close();
  }

  /**
   * Returns the image file name to use for the current screenshot.
   *
   * @return the image file name to use for the current screenshot, never {@code null}
   */
  private String getImageFileName() {
    return getFileNamePrefix() + IMAGE_FILE_EXTENSION;
  }

  /**
   * Returns the image file name to use for the current screenshot.
   *
   * @return the image file name to use for the current screenshot, never {@code null}
   */
  private String getCallStackFileName() {
    return getFileNamePrefix() + CALL_STACK_FILE_EXTENSION;
  }

  /**
   * Returns a prefix for a file name.
   *
   * @return Prefix for trace file names
   */
  private String getFileNamePrefix() {
    final StringBuilder result = new StringBuilder(getScreenshotDirectory());
    final Date date = new Date(lastCaptureTime);
    final DateFormat formatter = new SimpleDateFormat("HH.mm.ss.SSS", Locale.UK);
    final String formattedDate = formatter.format(date);
    result.append(formattedDate);
    if (testStepStarted && currentTestStep != null) {
      result.append(FILE_INFORMATION_SEPARATOR).append(currentTestStep.getName());
      if (currentTestStepState != null) {
        result.append(FILE_INFORMATION_SEPARATOR).append(currentTestStepState.toString());
      }
    } else if (testFailed) {
      result.append("__FAILED");
    }
    return result.toString();
  }

  /**
   * Returns the screenshot directory for the current test method.
   *
   * @return the screenshot directory for the current test method, never {@code null}
   */
  private String getScreenshotDirectory() {
    return getTestClassScreenshotDirectory() + testMethodName + File.separatorChar;
  }

  /**
   * Returns the screenshot directory for the current test class.
   *
   * @return the screenshot directory for the current test class, never {@code null}
   */
  private String getTestClassScreenshotDirectory() {
    return screenshotFolder + File.separatorChar + testClassName + File.separatorChar;
  }

  /**
   * Deletes all screenshots taken with this {@link TestRunRecording} for the current test.
   * If the containing folder remains empty, then it is removed too.
   */
  private void deleteScreenshots() {
    deleteDirectory(new File(getScreenshotDirectory()));
    // delete the directory of the test class too (works only if there are no other test method directories)
    new File(getTestClassScreenshotDirectory()).delete();
  }

  /**
   * Deletes a directory and all its children.
   *
   * @param path
   *          the {@link File} folder to delete
   * @return {@code true} if successful, {@code false} otherwise
   */
  private boolean deleteDirectory(final File path) {
    final boolean success = deleteDirectoryContents(path);
    return success && path.delete();
  }

  /**
   * Deletes all contents (recursively) of a directory.
   *
   * @param path
   *          the {@link File} folder for which the contents are to be deleted
   * @return {@code true} if successful, {@code false} otherwise
   */
  private boolean deleteDirectoryContents(final File path) {
    boolean success = true;
    if (path.exists()) {
      final File[] files = path.listFiles();
      for (final File file : files) {
        if (file.isDirectory()) {
          success &= deleteDirectory(file);
        } else {
          success &= file.delete();
        }
      }
    }
    return success;
  }

  /** {@inheritDoc} */
  @Override
  public void mouseDoubleClick(final MouseEvent e) {
    // do nothing special, it will be displayed as 1 total click.
  }

  /** {@inheritDoc} */
  @Override
  public void mouseDown(final MouseEvent e) {
    mouseEvent = e;
    mousePressedDown = true;
  }

  /** {@inheritDoc} */
  @Override
  public void mouseUp(final MouseEvent e) {
    mousePressedDown = false;
    mouseEvent = e;
    mouseClicked = true;
    createScreenshot();
    createCallStack();
    mouseClicked = false;
  }
}

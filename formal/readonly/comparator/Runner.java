import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.xtext.formatting.impl.AbstractFormattingConfig.ElementLocator;
import org.eclipse.xtext.formatting.impl.FormattingConfig;

import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfigBasedStream;
import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfigBasedStreamSortLocatorsTest;
import com.avaloq.tools.ddk.xtext.formatting.locators.FixedLocator;
import com.avaloq.tools.ddk.xtext.formatting.locators.NoFormatLocator;

/** Throwaway: runs the JUnit test methods reflectively against the real compiled bundle, then probes TimSort contract violations. */
public class Runner {
  public static void main(String[] args) throws Exception {
    Class<?> c = ExtendedFormattingConfigBasedStreamSortLocatorsTest.class;
    for (Method m : c.getDeclaredMethods()) {
      if (m.getAnnotation(org.junit.jupiter.api.Test.class) == null) continue;
      Object t = c.getDeclaredConstructor().newInstance();
      try { m.invoke(t); System.out.println("PASS " + m.getName()); }
      catch (java.lang.reflect.InvocationTargetException e) { System.out.println("FAIL " + m.getName() + ": " + e.getCause()); }
    }
    // Probe: with >= 32 elements TimSort's merge phase may detect the contract violation.
    FormattingConfig cfg = new FormattingConfig(null, null, null);
    ExtendedFormattingConfigBasedStream s = new ExtendedFormattingConfigBasedStream(null, null, cfg, null, null, false, null);
    Method sort = ExtendedFormattingConfigBasedStream.class.getDeclaredMethod("sortLocators", List.class);
    sort.setAccessible(true);
    int unsorted = 0, iae = 0, trials = 20000;
    Random r = new Random(42);
    for (int i = 0; i < trials; i++) {
      int n = 2 + r.nextInt(80);
      List<ElementLocator> l = new ArrayList<>();
      for (int k = 0; k < n; k++) l.add(r.nextInt(3) == 0 ? new NoFormatLocator(cfg) : new FixedLocator(cfg, r.nextInt(10), false, false, false));
      try { sort.invoke(s, l); } catch (java.lang.reflect.InvocationTargetException e) { if (e.getCause() instanceof IllegalArgumentException) { iae++; continue; } throw e; }
      int prev = Integer.MIN_VALUE; boolean ok = true;
      for (ElementLocator e : l) if (e instanceof FixedLocator f) { if (f.getColumn() < prev) ok = false; prev = f.getColumn(); }
      if (!ok) unsorted++;
    }
    System.out.println("random trials=" + trials + " fixed-subsequence-unsorted=" + unsorted + " IllegalArgumentException(contract)=" + iae);
  }
}

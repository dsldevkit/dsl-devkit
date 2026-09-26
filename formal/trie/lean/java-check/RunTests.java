import java.lang.reflect.Method;
/** Minimal reflective runner (no Maven/Tycho): runs every @Test method of the given class, reports pass/fail. */
public class RunTests {
  public static void main(String[] a) throws Exception {
    Class<?> c = Class.forName(a[0]);
    int failed = 0, total = 0;
    for (Method m : c.getDeclaredMethods()) {
      boolean isTest = m.isAnnotationPresent(org.junit.jupiter.api.Test.class);
      for (java.lang.annotation.Annotation an : m.getAnnotations()) isTest |= an.annotationType().isAnnotationPresent(org.junit.jupiter.api.Test.class);
      if (!isTest) continue;
      total++;
      Object o = c.getDeclaredConstructor().newInstance();
      try { m.invoke(o); System.out.println("PASS " + m.getName()); }
      catch (java.lang.reflect.InvocationTargetException e) { failed++; System.out.println("FAIL " + m.getName() + ": " + e.getCause().toString().replace('￿', '?').replace('\0', '0')); }
    }
    System.out.println(failed + "/" + total + " failed");
  }
}

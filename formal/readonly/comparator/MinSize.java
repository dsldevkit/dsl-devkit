import java.lang.reflect.Method;
import java.util.*;
import org.eclipse.xtext.formatting.impl.AbstractFormattingConfig.ElementLocator;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import com.avaloq.tools.ddk.xtext.formatting.ExtendedFormattingConfigBasedStream;
import com.avaloq.tools.ddk.xtext.formatting.locators.*;
/** Throwaway: smallest random list size at which sortLocators throws the TimSort contract IAE. */
public class MinSize {
  public static void main(String[] a) throws Exception {
    FormattingConfig cfg = new FormattingConfig(null, null, null);
    var s = new ExtendedFormattingConfigBasedStream(null, null, cfg, null, null, false, null);
    Method sort = ExtendedFormattingConfigBasedStream.class.getDeclaredMethod("sortLocators", List.class); sort.setAccessible(true);
    Random r = new Random(1);
    for (int n = 2; n <= 128; n++) for (int t = 0; t < 5000; t++) {
      List<ElementLocator> l = new ArrayList<>();
      for (int k = 0; k < n; k++) l.add(r.nextInt(3) == 0 ? new NoFormatLocator(cfg) : new FixedLocator(cfg, r.nextInt(10), false, false, false));
      try { sort.invoke(s, l); } catch (java.lang.reflect.InvocationTargetException e) { System.out.println("min n=" + n + " -> " + e.getCause()); return; }
    }
  }
}

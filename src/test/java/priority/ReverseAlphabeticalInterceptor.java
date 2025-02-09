package priority;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReverseAlphabeticalInterceptor implements IMethodInterceptor { //lesson16
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        Collections.sort(methods, new Comparator<IMethodInstance>() {
            @Override
            public int compare(IMethodInstance o1, IMethodInstance o2) {
                return o2.getMethod().getMethodName().compareTo(o1.getMethod().getMethodName());
            }
        });
        return methods;
    }
}

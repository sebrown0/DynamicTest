/**
 * 
 */
package root.finders;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface MethodGetter {
	List<Method> getAllTestMethods();
	List<Method> getAllTestMethodsWithType(String type);
	Method getMethodsWithTypeAndName(String type, String name);
}

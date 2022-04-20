/**
 * 
 */
package root.finders;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import annotations.TestControl;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add getTestMethodOfTypeWithName(...).
 * @since 1.0
 */
public class MethodFinder {
	
	public static List<Method> getMethodsAnnotatedWith(final Class<? extends Annotation> annotation, final Class<?> clazz) {
    final List<Method> methods = new ArrayList<Method>();   
    for (final Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(annotation)) {
        methods.add(method);
      }
    }   
    return methods;
	}
	
	public static List<Method> getTestMethods(final Class<?> clazz) {    
    return getMethodsAnnotatedWith(TestControl.class, clazz);
	}

	public static Method getTestMethodOfTypeWithName(final Class<?> clazz, final String type, final String name) {
    Method method = null;   
    for (final Method m : clazz.getDeclaredMethods()) {
      if (m.isAnnotationPresent(TestControl.class)) {
      	TestControl ann = m.getAnnotation(TestControl.class);
      	if(ann.type().equalsIgnoreCase(type) && m.getName().equalsIgnoreCase(name)) {
      		method = m;
      		break;
      	}            
      }
    }   
    return method;
	}
	
	public static List<Method> getTestMethodsOfType(final Class<?> clazz, final String type) {
    final List<Method> methods = new ArrayList<Method>();   
    for (final Method method : clazz.getDeclaredMethods()) {
      if (method.isAnnotationPresent(TestControl.class)) {
      	TestControl ann = method.getAnnotation(TestControl.class);      	
      	if(ann.type().equals(type)) {
      		methods.add(method);
      	}            
      }
    }   
    return methods;
	}
}

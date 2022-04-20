/**
 * 
 */
package root.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import core_data.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get an instance of a class from either:
 * 	1. Enclosing class with method type [i.e. button] and method name [i.e. buttonSave].
 *  2. Method from Enclosing class.
 *  3. From classes canonical name.
 */
public class ClazzFactory {
	
//	public static Object getClazz(final Class<?> target, final String methodType, final String methodName, final CoreData coreData) {
//		Method m = MethodFinder.getTestMethodOfTypeWithName(EmployeeDetails.class, methodType, methodName);
//		
//		return getClazz(m, coreData);
//	}
	
	public static Object getClazz(Method m, CoreData coreData) {
		Object clazz = null;
		if(m != null) {
			String canonicalName = m.getDeclaringClass().getCanonicalName();
			clazz = getClazz(canonicalName, coreData);	
		}	
		
		return clazz;
	}

	
	public static Object getClazz(String canonicalName, CoreData coreData) {
		Object clazzObj = null;
		
		try {
			//object_models.modules.payroll.left_menu.employees.EmployeeDetails
//			object_models.modules.payroll.left_menu.employees.Banks
			Class<?> clazz = Class.forName(canonicalName);
			Constructor<?> cnstr = clazz.getConstructor(new Class[] {CoreData.class});
			clazzObj = cnstr.newInstance(coreData);
			
//			clazz = Class.forName(canonicalName).getConstructor(CoreData coreData).newInstance();
		} catch (	InstantiationException | IllegalAccessException | 
							IllegalArgumentException | InvocationTargetException |
							NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return clazzObj;
	}

}

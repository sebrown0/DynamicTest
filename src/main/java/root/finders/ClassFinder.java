/**
 * 
 */
package root.finders;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;

import core_data.CoreData;
import site_mapper.elements.ElementClass;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Update the way the fullPath is found.
 * @version 1.2
 * 	Remove method finders into separate class.
 * @since 1.0
 */
public class ClassFinder {
	private static String ROOT = "object_models";	

	public static Object getInstantiatedObject(ElementClass nodeClass, CoreData coreData){		
		final Class<?> clazz = getClazz(nodeClass);
		Object obj = null;
		
		Constructor<?> cnstr;
		try {
			cnstr = clazz.getConstructor(CoreData.class);
			obj = cnstr.newInstance(coreData);
		} catch (
				NoSuchMethodException | SecurityException | InstantiationException | 
				IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
System.out.println("->" + e.getMessage()); // TODO - remove or log 	
			LogManager.getLogger().error("Could not instantiate class for [" + nodeClass + "]");
		}		
		return 	obj;
	}
	
	public static Object getInstantiatedObject(ElementClass nodeClass){		
		final Class<?> clazz = getClazz(nodeClass);
		Object obj = null;
		
		Constructor<?> cnstr;
		try {
//			cnstr = clazz.getConstructor();
			cnstr = clazz.getConstructor(CoreData.class);
			obj = cnstr.newInstance();
		} catch (
				NoSuchMethodException | SecurityException | InstantiationException | 
				IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {

			LogManager.getLogger().error("Could not instantiate class for [" + nodeClass + "]");
		}		
		return 	obj;
	}
	
	public static Class<?> getClazz(ElementClass nodeClass){		
		try {
			String className = getPathToClass(nodeClass);
			//object_models.modules.payroll.top_right_nav.employees.EmployeeCreationWizard
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			LogManager.getLogger().error("Could not get class for [" + nodeClass + "]");
		}
		return null;
	}
	
	private static String getPathToClass(ElementClass nodeClass) {
		return getPathInLowerCase(nodeClass) + "." + nodeClass.getClassName();
	}
	private static String getPathInLowerCase(ElementClass nodeClass) {
		String path = 
				ROOT + ".modules." +
				nodeClass.getModuleName() + "." + 
				nodeClass.getParentPackage() + 
				getPackage(nodeClass);
//object_models.modules.payroll.top_right_nav.Employees
		return path.toLowerCase();
	}
	private static String getPackage(ElementClass nodeClass) {
		var pckge = nodeClass.getPackage();
		return (pckge != null) ? "." + pckge : "";
	}
}
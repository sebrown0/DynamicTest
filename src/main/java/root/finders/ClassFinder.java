/**
 * 
 */
package root.finders;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

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
	private final String inProject;
		
	public ClassFinder(final String inProject) {
		this.inProject = inProject;
	}

	public Object getInstantiatedObject(ElementClass nodeClass, CoreData coreData){		
		final Class<?> clazz = getClazz(nodeClass);
		Object obj = null;
		
		Constructor<?> cnstr;
		try {
			cnstr = clazz.getConstructor(CoreData.class);
			obj = cnstr.newInstance(coreData);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | 
						 IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logError(e, nodeClass);
		}		
		return 	obj;
	}
	
	public Object getInstantiatedObject(ElementClass nodeClass){		
		final Class<?> clazz = getClazz(nodeClass);
		Object obj = null;
		
		Constructor<?> cnstr;
		try {
			cnstr = clazz.getConstructor(CoreData.class);
			obj = cnstr.newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | 
						 IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logError(e, nodeClass);
		}		
		return 	obj;
	}
	
	public Class<?> getClazz(ElementClass nodeClass){			
			File f = new File(inProject);
			URLClassLoader cl = null;			
			
			try {				
				URL[] cp = {f.toURI().toURL()};
				cl = new URLClassLoader(cp);
				Class<?> c = cl.loadClass(getFullPath(nodeClass));
				cl.close();
				return c;				
			} catch (IOException | ClassNotFoundException e) {
				logError(e, nodeClass);
				try {
					cl.close();
				} catch (IOException e1) {
					logError(e, nodeClass);
				}
				logError(e, nodeClass);
			} 
			
			return null;
		}
	
		private String getFullPath(ElementClass nodeClass){
			return getPackage(nodeClass) + "." + nodeClass.getClassName();
		}
		
		private String getPackage(ElementClass nodeClass){
			String pack = 
					"library.object_models.modules" + 
					"." + nodeClass.getModuleName() +
					"." + nodeClass.getParentPackage() +
					"." + nodeClass.getPackage();
			return pack;
		}
		
		private void logError(Exception e, ElementClass nodeClass) {
			 LogManager
			 	.getLogger(ClassFinder.class)
			 	.error("Could not instantiate class for [" + nodeClass + "] ERROR -> " + e.getMessage());
		}
		
}
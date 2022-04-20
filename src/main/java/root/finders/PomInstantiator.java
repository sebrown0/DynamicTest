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
 * @since 1.0
 * 
 * Get an instantiated POM Class using the ClassFinder.
 * 
 * The class has to be in "library.object_models.modules"
 * of the library(project).
 */
public class PomInstantiator implements ClassInstantiator {
	private final ElementClass nodeClass;
	private final CoreData coreData;
	private final ClassFinder finder;
		
	public PomInstantiator(String inProject, ElementClass nodeClass, CoreData coreData) {
		this.nodeClass = nodeClass;
		this.coreData = coreData;
		this.finder = new PomClassFinder(inProject, nodeClass);
	}
	
	@Override
	public Object getInstantiatedClass() {
		final Class<?> clazz = finder.getClazz();
		Object obj = null;		
		Constructor<?> cnstr;
		try {
			cnstr = clazz.getConstructor(CoreData.class);
			obj = cnstr.newInstance(coreData);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | 
						 IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logError(e);
		}		
		return 	obj;
	}
	
	private void logError(Exception e) {
		 LogManager
		 	.getLogger(PomInstantiator.class)
		 	.error("Could not instantiate class for [" + nodeClass + "] ERROR -> " + e.getMessage());
	}

}

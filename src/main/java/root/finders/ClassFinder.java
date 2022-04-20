/**
 * 
 */
package root.finders;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Update the way the fullPath is found.
 * @version 1.2
 * 	Remove method finders into separate class.
 * @version 1.3
 * 	Change to interface.
 * @since 1.0
 */
public interface ClassFinder {	
	Class<?> getClazz();		
}

/**
 * 
 */
package root.elements;

import java.util.Optional;

//import dynamic_tests.finders.ClassFinder;
import library.common.controls.interfaces.ControlTest;
import library.pages.homepage.HomePage;
import root.finders.ClassFinder;
import site_mapper.elements.ElementClass;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Make static class.
 * @since 1.0
 * 
 * Get the node as a container, convert to ControlTest
 * and return it.
 */
public class ElementLoader {
	private static Optional<SiteMapElement> container;
	private static ElementClass nodeClass;
	private static HomePage homePage;
		
	public static ControlTest getControlTest(ElementClass nc, HomePage hp) {		
		setVariables(nc, hp);
		loadContainer();		
		return getContainerAsControlTest();
	}
	private static void setVariables(ElementClass nc, HomePage hp) {
		nodeClass = nc;
		homePage = hp;
	}
	private static void loadContainer() {
		container = getNodeAsSiteMapElement();
	}
	private static Optional<SiteMapElement> getNodeAsSiteMapElement() {		
		Class<?> clazz = getClazz();		
		return 
			Optional.ofNullable(
				SiteMapElementLoader.getAndLoadSiteMapElement(nodeClass, homePage, clazz));		
	}
	private static Class<?> getClazz(){		
		return ClassFinder.getClazz(nodeClass);
	}
	private static ControlTest getContainerAsControlTest() {
		ControlTest ct = null;
		if(container.isPresent()) {
			SiteMapElement e = container.get();
			if(e instanceof ControlTest) {
				ct = (ControlTest) e;
			}else {
				System.out.println("ElementLoader -> ***** getControlTest() 1 *****"); // TODO - remove or log
			}
		}else {
			System.out.println("ElementLoader -> ***** getControlTest() 2 *****"); // TODO - remove or log 	
			// TODO - THROW ERROR??
		}
		return ct;
	}
}

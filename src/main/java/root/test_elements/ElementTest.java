/**
 * 
 */
package root.test_elements;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

//import dynamic_tests.elements.ControlFinder;
//import dynamic_tests.mappers.TestNode;
//import dynamic_tests.test_adders.TestAdder;
import library.pages.homepage.HomePage;
import root.elements.ControlFinder;
import root.mappers.TestNode;
import root.test_adders.TestAdder;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Pass the container that has the control test.
 * @since 1.0
 * 
 * Is a bridge between the Element(SiteMapper -> POM) 
 * and the test(s) for the element.
 */
public class ElementTest implements TestElementDetails {
	private String elName;
	private String elType;
	private String elTestType;
	private String elmntsParent;
	private List<DynamicTest> testList = new ArrayList<>();	
	private ControlFinder controlFinder;
	private ElementTestFactory testFactory;
	
	//Added for demo 12/04/2022
//	private ElementFunction elFunction;
	
	public ElementTest(
		TestNode testNode, HomePage hp, 
		MenuItem item, ElementCreation e, ElementTestFactory testFactory) {

		this.elType = e.getElementType();
		this.elName = e.getElementName();
		this.testFactory = testFactory;
		this.elmntsParent = item.getName();
		
		controlFinder = new ControlFinder(testNode, hp, item, elName, testFactory);
				
//		elFunction = ((Element)e).getElementFunction();
	}

	public void addTests(TestAdder testAdder) {
		testFactory 
			.setCntrlFinder(controlFinder)
			.setTestList(testList);
		testAdder.addTestsWith(testFactory, this);
	}
	
	@Override //TestElementDetails
	public String getName() {
		return elName;
	}
	@Override //TestElementDetails
	public String getElementType() {
		return elType;
	}
	@Override //TestElementDetails
	public List<DynamicTest> getTestList() {
		return testList;
	}

	@Override //TestElementDetails
	public String getElementTestType() {
		return elTestType;
	}

	@Override //TestElementDetails
	public void setElementTestType(String type) {
		this.elTestType = type;
	}

	@Override
	public String getTestBelongsTo() {
		return elmntsParent;
	}
		
	/*
	 * DON'T DELETE - FOR TEST METHOD.
	 */
//	//can the item be passed????????????????????????????????????????????
//	protected void addTestMethod(String methodType, CoreData coreData) {
//		String methodName = methodType + StringUtil.capitiliseFirstChar(name);
//		Method m = 
//				((MethodGetter) controlTest)
//				.getMethodsWithTypeAndName(methodType, methodName);
//		
//		Optional
//			.ofNullable(ClazzFactory.getClazz(m, coreData))
//			.ifPresentOrElse(
//					c -> {
//						try {							
//							DynamicTest test = (DynamicTest)m.invoke(c);
//							tests.add(test);							
//						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//							new MethodError(methodName).run();
//						}
//					}, 
//					new MethodError(methodName)
//			);		
//	}
	
//	private class MethodError implements Runnable {
//		String methodName;
//		
//		public MethodError(String methodName) {
//			this.methodName = methodName;
//		}
//
//		@Override
//		public void run() {
//			LogManager
//				.getLogger(ElementTest.class)
//				.error(
//						String.format(
//								"Error setting dynamic test method, for [%s.%s]", 
//								item.getClassName(), methodName
//						)
//				);
//		}		
//	}
	
}

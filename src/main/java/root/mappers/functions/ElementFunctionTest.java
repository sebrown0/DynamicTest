/**
 * 
 */
package root.mappers.functions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import root.finders.MethodFinder;

//import dynamic_tests.finders.MethodFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the dynamic tests for each element in the node (MenuItem).
 */
public class ElementFunctionTest extends FunctionTest{
	private List<DynamicTest> tests = new ArrayList<>();
	private Method currentMethod;
	
	public ElementFunctionTest(
		Object obj, String itemName, List<DynamicContainer> menuItemTests) {
		super(obj, itemName, menuItemTests);
	}
	
	@Override
	public void addFunctionTest() {		
		getMethodsFromObject();
		if(methods != null) {	
			methods.forEach(m -> {
				currentMethod = m;
				getDynamicTestFromMethod();				
				if(dt != null) {
					tests.add(dt);
				}		
			});
			addTestToList();
		}else {
			LogManager
				.getLogger(ElementFunctionTest.class)
				.debug(String.format("[%s] has no test element functions", itemName));
		}								
	}
	
//	@Override
//	public void addFunctionTest(ElementFunction elFunction) {		
//		getMethodsFromObject();
//		if(methods != null) {	
//			methods.forEach(m -> {
//				currentMethod = m;
//				getDynamicTestFromMethod();				
//				if(dt != null) {
//					tests.add(dt);
//				}		
//			});
//			addTestToList();
//		}else {
//			LogManager
//				.getLogger(ElementFunctionTest.class)
//				.debug(String.format("[%s] has no test element functions", itemName));
//		}								
//	}
	
	@Override
	protected void getMethodsFromObject() {
		methods = MethodFinder.getTestMethodsOfType(obj.getClass(), "element");
	}
	
	@Override
	protected void getDynamicTestFromMethod() {		
		try {
			/*
			 * need to get the data(emp) to here.
			 */
			dt = (DynamicTest) currentMethod.invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LogManager
				.getLogger(ElementFunctionTest.class)
				.error(String.format("Error getting DT for method [%s]", currentMethod.getName()));
		}
	}
	@Override
	protected void addTestToList() {
		menuItemTests.add(
				DynamicContainer.dynamicContainer(
					"Element Functions", 	
					tests
					));		
	}
}

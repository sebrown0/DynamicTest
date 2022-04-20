/**
 * 
 */
package root.mappers.functions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import root.finders.MethodFinder;
import root.mappers.TestNode;
import site_mapper.jaxb.pom.ElementFunction;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the dynamic test for a container,
 * if there is one.
 * 
 * An object is created from the data in ElementClass.
 * The object's @TestControl function is found.
 * This returns the dynamic test for the container.
 * The DT is added to the DynamicContainer for the node.
 */
public class ContainerFunctionTest extends FunctionTest{
	private TestNode tn;
	private Method meth;
	private ElementFunction f;
	
	public ContainerFunctionTest(Object obj, String itemName, List<DynamicContainer> menuItemTests) {
		super(obj, itemName, menuItemTests);
	}
	
	@Override
	public void addFunctionTest() {
		f = tn.getFunc();
		if(f != null) {
			getMethodsFromObject();
			if(meth != null) {	
				getDynamicTestFromMethod();				
				if(dt != null) {
					addTestToList();
				}				
			}else {
				LogManager
					.getLogger(ContainerFunctionTest.class)
					.debug(String.format("[%s] has no test function", itemName));
			}		
		}				
	}
	
	@Override
	protected void getMethodsFromObject() {
		meth = 
			MethodFinder.getTestMethodOfTypeWithName(
					obj.getClass(), "container", "Container" + tn.getName() + "FunctionTest");
	}
	@Override
	protected void getDynamicTestFromMethod() {		
		try {
			dt = (DynamicTest) meth.invoke(obj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LogManager
				.getLogger(ContainerFunctionTest.class)
				.error(String.format("Error getting DT for method [%s]", meth.getName()));
		}
	}
	@Override
	protected void addTestToList() {
		menuItemTests.add(
				DynamicContainer.dynamicContainer(
					"Functions", 
					Arrays.asList(dt)				
					));		
	}

	public FunctionTest setTestNode(TestNode tn) {
		this.tn = tn;
		return this;
	}
//
//	@Override
//	public void addFunctionTest(ElementFunction func) {
//		// TODO Auto-generated method stub
//		
//	}
}

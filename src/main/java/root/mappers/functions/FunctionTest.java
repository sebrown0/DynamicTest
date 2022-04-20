/**
 * 
 */
package root.mappers.functions;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class FunctionTest {
	protected Object obj;
	protected String itemName;
	protected List<DynamicContainer> menuItemTests;	
	
	protected List<Method> methods;
	protected DynamicTest dt;
	
	public FunctionTest(Object obj, String itemName, List<DynamicContainer> menuItemTests) {
		this.obj = obj;
		this.itemName = itemName;
		this.menuItemTests = menuItemTests;
	}
		
	public abstract void addFunctionTest();
//	public abstract void addFunctionTest(ElementFunction func);S
	protected abstract void getMethodsFromObject();
	protected abstract void getDynamicTestFromMethod();
	protected abstract void addTestToList();
		
}

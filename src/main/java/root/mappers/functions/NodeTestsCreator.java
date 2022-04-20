/**
 * 
 */
package root.mappers.functions;

import java.util.List;

import org.junit.jupiter.api.DynamicContainer;

import core_data.CoreData;
//import dynamic_tests.elements.IncludedElements;
//import dynamic_tests.finders.ClassFinder;
//import dynamic_tests.mappers.TestNode;
//import dynamic_tests.test_elements.DynamicTestFactory;
//import dynamic_tests.test_elements.ElementTest;
//import dynamic_tests.test_elements.ElementTestFactory;
//import dynamic_tests.test_elements.TestElementDetails;
import library.pages.homepage.HomePage;
import root.elements.IncludedElements;
import root.finders.ClassFinder;
import root.mappers.TestNode;
import root.test_elements.DynamicTestFactory;
import root.test_elements.ElementTest;
import root.test_elements.ElementTestFactory;
import root.test_elements.TestElementDetails;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 * 
 * Add the specific tests for each 
 * test node in the item, 
 * i.e. all the tests for a button in an input group.
 */
public class NodeTestsCreator {
	private List<TestNode> testNodes;
	private IncludedElements includedElements;
	private MenuItem item;;
	private HomePage hp;
	private List<DynamicContainer> menuItemTests;
	
	private Object obj;
	private DynamicTestFactory testFactory = new DynamicTestFactory();
	
	public NodeTestsCreator(
		List<TestNode> testNodes, IncludedElements includedElements, 
		MenuItem item, HomePage hp,	List<DynamicContainer> menuItemTests) {
		
		this.testNodes = testNodes;
		this.includedElements = includedElements;
		this.item = item;
		this.hp = hp;
		this.menuItemTests = menuItemTests;
	}

	public NodeTestsCreator addTestsForEachTestNode(ElementTestFactory tf) {
//		obj = ClassFinder.getInstantiatedObject(item);
		obj = ClassFinder.getInstantiatedObject(item, (CoreData)hp);
		
		addElementFunctionTests();
		
		ContainerFunctionTest funcTest = 
			new ContainerFunctionTest(obj, item.getName(), menuItemTests); 
		
		testNodes.forEach(tn -> { 			
			addContainerFunctionTest(funcTest.setTestNode(tn)); 	
			addTestsForElements(tn, tf);			
		});	
		return this;
	}

	private void addContainerFunctionTest(FunctionTest funcTest) {
		funcTest.addFunctionTest();		
	}
	private void addElementFunctionTests() {
		ElementFunctionTest elmntTest = 
			new ElementFunctionTest(obj, item.getName(), menuItemTests);
		
		elmntTest.addFunctionTest();		//12/04 not doing anything
	}
//	private void addElementFunctionTests(Element el) {
//		ElementFunctionTest elmntTest = 
//			new ElementFunctionTest(obj, item.getName(), menuItemTests);
//		
//		elmntTest.addFunctionTest(el.getElementFunction());		//12/04 not doing anything
//	}		
	private void addTestsForElements(TestNode tn, ElementTestFactory tf) {
		List<Element> els = tn.getElements();
		if(els != null) {
			els.forEach(el -> {				
//				addElementFunctionTests(el);
				addTestForElement(tn, el, tf);				
			});							
		}			
	}
	
	private void addTestForElement(TestNode tn, Element el, ElementTestFactory tf) {
		if(includedElements.isIncluded(el.getElementType())) {

//			ElementFunction f = el.getElementFunction();
//			EmployeeContent e = f.getEmployee();
			
			ElementTest elTest = new ElementTest(tn, hp, item, el, tf);
			testFactory.addElementSpecificTestsTo(elTest, el);
			addTestToItemContainer(elTest);
		}
	}
	
	private void addTestToItemContainer(ElementTest t) {		
		menuItemTests.add(
				DynamicContainer.dynamicContainer(getKey(t), t.getTestList()));		
	}
		
	private String getKey(TestElementDetails e) {
		return e.getElementType() + "." + e.getName();
	}
}

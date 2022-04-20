/**
 * 
 */
package root.mappers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;

//import library.menu.MenuItem;
import library.pages.homepage.HomePage;
import root.common.XmlInfo;
import root.elements.IncludedElements;
import root.mappers.functions.NodeTestsCreator;
import root.test_elements.ElementTestFactory;
import root.test_results.DynamicTestSuiteData;
import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.node.Node;
import site_mapper.jaxb.node.ParentNode;
import site_mapper.jaxb.tree.TreeVisitor;
import site_mapper.jaxb.tree.TreeWalker;

//import dynamic_tests.common.XmlInfo;
//import dynamic_tests.elements.IncludedElements;
//import dynamic_tests.mappers.functions.NodeTestsCreator;
//import dynamic_tests.test_elements.ElementTestFactory;
//import dynamic_tests.test_results.DynamicTestSuiteData;
//import library.pages.homepage.HomePage;
//
//import site_mapper.jaxb.containers.Container;
import site_mapper.jaxb.menu_items.MenuItem;
//import site_mapper.jaxb.node.Node;
//import site_mapper.jaxb.node.ParentNode;
//import site_mapper.jaxb.tree.TreeVisitor;
//import site_mapper.jaxb.tree.TreeWalker;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add all the included tests to the item's 
 * list of tests.
 */
public class DynamicTestItem implements TreeVisitor {
	private IncludedElements includedElements;
	private XmlInfo testInfo;
	private TestNode lastTestNode;
	private List<TestNode> testNodes = new ArrayList<>();
	private MenuItem item;
	private List<DynamicContainer> menuItemTests;
	private HomePage hp;
	
	public DynamicTestItem(
		XmlInfo testInfo, MenuItem item,	
		List<DynamicContainer> menuItemContainers, HomePage hp) {
		
		this.includedElements = testInfo.getIncludedElements();
		this.testInfo = testInfo;
		this.menuItemTests = menuItemContainers;
		this.item = item;
		this.hp = hp;
		 	
		getElements();
	}
	
	private void getElements() {
		TreeWalker treeWalker = 
				new TreeWalker(
						this,
						new ParentNode(item.getHeaderContainer()),
						new ParentNode(item.getBodyContainer()),
						new ParentNode(item.getFooterContainer()));
						
		treeWalker.traverseTree();	
	}

	@Override //TreeVisitor
	public void addNode(Node node) {
		TestNode testNode =	new TestNode(lastTestNode, node);
		testNodes.add(testNode);
		setLastNode(testNode, node);				
	}
	
	private void setLastNode(TestNode testNode, Node node) {
		if(isParent(node)) {
			lastTestNode = testNode;	
		}else {
			lastTestNode = null;
		}
	}
	private boolean isParent(Node n) {
		List<Container> children = n.getContainers();
		return (children != null && children.size() > 0 ) ? true : false;
	}

	public void addTests() {		
		if(testNodes != null) {				
			NodeTestsCreator nodeTests = 
				new NodeTestsCreator(
					testNodes, includedElements, item, hp, menuItemTests);
			nodeTests.addTestsForEachTestNode(
					new ElementTestFactory(testInfo, new DynamicTestSuiteData(item)));
		}
	}
		
}

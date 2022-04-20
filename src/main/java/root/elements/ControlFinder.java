/**
 * 
 */
package root.elements;

import java.util.List;
import java.util.Optional;

//import dynamic_tests.mappers.TestNode;
//import dynamic_tests.test_elements.ElementTestFactory;
import library.common.controls.interfaces.Control;
import library.common.controls.interfaces.ControlTest;
import library.pages.homepage.HomePage;
import root.mappers.TestNode;
import root.test_elements.ElementTestFactory;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlFinder {
	private String name;
	private TestNode testNode;
	private MenuItem item;
	private HomePage hp;	
	private Optional<Control> cntrl = null;
	private ControlTest cntrlTest;
	private ElementTestFactory tf;
	
	public ControlFinder(TestNode testNode, HomePage hp, MenuItem item, String name, ElementTestFactory tf) {		
		this.testNode = testNode;
		this.hp = hp;
		this.item = item;
		this.name = name;
		this.tf = tf;
	}

	public ControlFinder loadControl() {
		List<String> prntNames = testNode.getPrntNames();
		//do once for each item
		loadContainerIfNecessary();

		//should always be at least one name (the element's node)
		if(prntNames != null) {
			int idx = 0;
			String prntName = prntNames.get(idx);
			ControlGroup grp = (ControlGroup) cntrlTest.getControl(prntName).get();
			while(prntName != null && idx < prntNames.size()-1) {				
				idx++;
				prntName = prntNames.get(idx);
				grp = (ControlGroup) grp.getControlByTitle(prntName).get();				
			}
			cntrl = grp.getControlByTitle(name);			
		}	
		return this;
	}
		
	private void loadContainerIfNecessary() {
		if(tf.itemIsNotLoaded()) {
			cntrlTest = loadTestsContainerAndGetAsControlTest();
			tf.setControlTest(cntrlTest);
			tf.isItemIsNotLoaded(false);
		}else {
			cntrlTest = tf.getControlTest();
		}		
	}
	
	private ControlTest loadTestsContainerAndGetAsControlTest() {		
		if(cntrlTest == null) {
			cntrlTest = getControlTest();
		}
		return cntrlTest;
	}

	private ControlTest getControlTest() {
		return ElementLoader.getControlTest(item, hp);
	}
	
	public Optional<Control> getControl() {
		return cntrl;
	}
	public ControlTest getControlsClass() {
		return cntrlTest;
	}
}

/**
 * 
 */
package root.test_elements;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import library.common.controls.interfaces.Control;
import library.common.controls.interfaces.ControlTest;
import root.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Create (add to list) test for control.
 * The test's message & execution is got
 * from the ElementTestCreator, i.e. CreateToolTipCheck.
 *  
 */
public class TestCreator {
	protected ControlFinder cntrlFinder;
	protected List<DynamicTest> testList;
	protected Optional<Control> cntrl;	
	protected ControlTest controlTest;
	
	public TestCreator(ControlFinder cntrlFinder,	List<DynamicTest> testList) {		
		this.cntrlFinder = cntrlFinder;
		this.testList = testList;
	}
	
	public TestCreator createTest(TestElementCreator elementTestCreator, List<DynamicTest> testList) {
		testList.add(
		DynamicTest.dynamicTest(
				elementTestCreator.getMessage(), 
			() ->	{ 
				getControlAndParent();
				elementTestCreator.createTestExecutor(cntrl); 
			}));			
		return this;
	}

	protected void getControlAndParent() {
		this.cntrl = cntrlFinder.loadControl().getControl();
		this.controlTest = cntrlFinder.getControlsClass();
	}
	
}

/**
 * 
 */
package root.test_elements;

import java.util.Optional;

import library.common.controls.interfaces.Control;
import root.common.XmlInfo;
import root.test_adders.TestAdderWithData;
import root.test_results.DynamicTestSuiteData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateTextCheckTestData extends TestElementCreator {
	private TestAdderWithData testAdderWithData;		
	
	public CreateTextCheckTestData(
		TestElementDetails testElementDetails, XmlInfo testInfo, 
		DynamicTestSuiteData testData, TestAdderWithData testAdderWithData) {
		
		super(testElementDetails, testInfo, testData, null);
		
		this.testAdderWithData = testAdderWithData;
	}

	@Override
	protected void executeTest(Optional<Control> cntrl) {
//		ZZZ_TestDataInserter.insertAnyTestData(testAdderWithData, controlTest);		
		assertEquals.assertTextEquals(testElementDetails, testAdderWithData, cntrl);		
	}

	@Override
	protected String getMessage() {
		return "Is [" + testElementDetails.getName() +"] text correct?";
	}
	
	@Override
	protected void setTestType() {
		 testElementDetails.setElementTestType("TestDataCheck");		
	}
}

/**
 * 
 */
package root.test_elements;

import java.util.Optional;

import library.common.controls.interfaces.Control;
import root.common.XmlInfo;
import root.test_results.DynamicTestSuiteData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

public class CreateTextCheckString extends TestElementCreator {
		
	public CreateTextCheckString(
			TestElementDetails testElementDetails, XmlInfo testInfo, 
			DynamicTestSuiteData testData, String textExpected) {
		
		super(testElementDetails, testInfo, testData, textExpected);

	}
	
	@Override
	public void executeTest(Optional<Control> cntrl) {		
		assertEquals.getTextActualAndAssertTextEquals(
			testElementDetails, 
			new TestElementData().setTextExpected(textExpected), 
			cntrl);
	}

	@Override
	public String getMessage() {
		return "Is [" + testElementDetails.getName() +"] text correct?";
	}

	@Override
	protected void setTestType() {
		 testElementDetails.setElementTestType("TextCheck");		
	}

}

/**
 * 
 */
package root.test_elements;

import java.util.Optional;

import library.common.controls.data.ControlTestData;
import library.common.controls.interfaces.Control;
import root.common.XmlInfo;
import root.test_results.DynamicTestSuiteData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateFaFaCheck extends TestElementCreator  {
		
	public CreateFaFaCheck(
		TestElementDetails testElementDetails, XmlInfo testInfo, 
		DynamicTestSuiteData testData, String textExpected) {
		
		super(testElementDetails, testInfo, testData, textExpected);
		
	}
	
	@Override
	protected void executeTest(Optional<Control> cntrl) {
		String faFaActual = ControlTestData.getFaFaText(cntrl);	
		
		assertEquals.assertTextEquals(
				testElementDetails, 
				new TestElementData().setTextExpected(textExpected).setTextActual(faFaActual), 
				cntrl);		
	}

	@Override
	protected String getMessage() {
		return "Is [" + testElementDetails.getName() +"] FaFa correct?";
	}
	
	@Override
	protected void setTestType() {
		 testElementDetails.setElementTestType("FaFaCheck");		
	}
}

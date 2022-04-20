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
@SuppressWarnings("unused")
public class CreateListCheck extends TestElementCreator {	
	private TestAdderWithData testAdderWithData;
		
	public CreateListCheck(
		TestElementDetails testElementDetails, XmlInfo testInfo, 
		DynamicTestSuiteData testData, TestAdderWithData testAdderWithData) {
		
		super(testElementDetails, testInfo, testData, null);

		this.testAdderWithData = testAdderWithData;
	}

		
	@Override
	protected void executeTest(Optional<Control> cntrl) {
		//noit implemented			
	}

	@Override
	protected String getMessage() {
		System.out.println("CreateListCheck.getMessage->NOT IMPLEMENTED"); // TODO - remove or log 	
		//noit implemented			
//		return "Is [" + testData.getElementName() +"] text correct?";
		return "";
	}

	@Override
	protected void setTestType() {
		 testElementDetails.setElementTestType("ListCheck");		
	}
}

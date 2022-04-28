/**
 * 
 */
package root.assertations;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import library.common.controls.data.ControlTestData;
import library.common.controls.data.InsertItem;
import library.common.controls.interfaces.Control;
import root.common.XmlInfo;
import root.test_adders.TestAdderWithData;
import root.test_elements.TestElementData;
import root.test_elements.TestElementDetails;
import root.test_results.DynamicTestFail;
import root.test_results.DynamicTestPass;
import root.test_results.DynamicTestSuiteData;
import root.test_strategy.DynamicTestReportStrategy;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

/*
 * TODO
 * ----
 * Split this up!!!
 * 
 */
public class AssertTextEquals implements ReportData {
	private DynamicTestSuiteData testSuiteData;	
	private Optional<Control> cntrl;
	private TestElementData testElementData;
	private TestElementDetails testElementDetails;	
	private List<String> includeInReport;
	private DynamicTestReportStrategy strat;
	
	@SuppressWarnings("unused")
	private final Logger LOGGER = LogManager.getLogger(AssertTextEquals.class);
	
	public AssertTextEquals(final XmlInfo testInfo, DynamicTestSuiteData testData) {			
			this.strat = testInfo.getTestReportStrategy();
			this.includeInReport = testInfo.getReportOnTests();
			this.testSuiteData = testData;			
		}
	
	@Override
	public String getTestSuiteName() {
		return testSuiteData.getTestSuiteName();
	}
	@Override
	public String getElementName() {
		return testElementDetails.getName();
	}
	@Override
	public String getElementType() {
		return testElementDetails.getElementType();
	}
	@Override
	public String getElementTestType() {
		return testElementDetails.getElementTestType();
	}	
	@Override
	public String getExpected() {		 
		return (testElementData != null) ? testElementData.getTextExpected() : null;		
	}
	@Override
	public String getActual() {		
		return (testElementData != null) ? testElementData.getTextActual() : null;
	}
	
	public void assertTextEquals(TestElementDetails testElementDetails, TestElementData testElementData, Optional<Control> cntrl) {
		this.testElementDetails = testElementDetails;
		this.testElementData = testElementData;
		this.cntrl = cntrl;
		runAssert();
	}
	
	public void getTextActualAndAssertTextEquals(TestElementDetails testElementDetails, TestElementData testElementData, Optional<Control> cntrl) {
		this.testElementDetails = testElementDetails;
		this.testElementData = testElementData;
		this.cntrl = cntrl;
		setTextActual();
		runAssert();
	}

	private void setTextActual() {
		testElementData.setTextActual(ControlTestData.getControlText(cntrl));
	}

	private void runAssert() {
		String actual = getActual(); 
		if(actual != null && actual.equals(getExpected())) {
			if(isIncludedInReport("Passed")) {
				strat.reportPass(new DynamicTestPass(this));	
			}			
		}else {
			if(isIncludedInReport("Fails")) {
				strat.reportFail(new DynamicTestFail(this));	
			}			
		}
	}
	
	private boolean isIncludedInReport(String testType) {
		if(includeInReport.contains("All")) {
			return true;
		}else if(includeInReport.contains(testType)) {
			return true;
		}
		return false;
	}
	
	/*
	 * WHEN WE GET HERE WE NEED THE DataInserter TO BE INITIALISED WITH 
	 * TestDataItem data, ControlTest controlTest, Control control
	 */
	public void assertTextEquals(TestElementDetails testElementDetails, TestAdderWithData testAdder, Optional<Control> cntrl) {
		cntrl.ifPresent(c -> {
			InsertItem insert = (InsertItem)c;
			System.out.println("DT->AssertTextEquals.assertTextEquals->*** ERROR ***"); // TODO - remove or log 	
//			insert.insert(cntrl, testAdder);
		});
		
		
//		System.out.println("assertTextEquals->NOT IMPLEMENTED"); // TODO - remove or log 	
//		this.testElmntName = testElementDetails.getName();
//		this.testElmntType = testElementDetails.getElementType();
//		this.cntrl = cntrl;
//		
//		TestDataOut dataOut = testAdder.getTestDataOut();
//		if(dataOut != null) {
//			Data testData = dataOut.getData();
//			//TODO
//			if(testData != null) {
//				TestDataItem testDataItem = testData.getTestDataList().get(0);
//				if(testDataItem != null) {
//					this.textExpected = testDataItem.getValue();
//					runAssert();	
//				}
////			runAssert(testData.getValue());						
//			}					
//		}else {
//			LOGGER
//				.info(
//					String.format(
//							"No test data to check expected result for [%s - %s]", 
//							controlTest.getClass().getSimpleName(), cntrl.get().getClass().getSimpleName()));
//		}				
	}



	
	
}

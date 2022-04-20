/**
 * 
 */
package root.test_strategy;

import root.assertations.ReportData;
import root.test_results.DynamicTestFail;
import root.test_results.DynamicTestIgnored;
import root.test_results.DynamicTestPass;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestReportStrategyConsole implements DynamicTestReportStrategy {
		
	@Override
	public void reportPass(DynamicTestPass result) {		
		System.out.println(
			String.format(
				"%s -> PASS", getMsg(result.getReportData())));		
	}

	@Override
	public void reportFail(DynamicTestFail result) {
		ReportData data = result.getReportData();
		System.out.println(
			String.format(
					"%s -> FAIL[expected=%s, was=%s]", 
					getMsg(data), 
					data.getExpected(), data.getActual()));  	
	}

	@Override
	public void reportIgnored(DynamicTestIgnored result) {
		System.out.println("IGNORE"); // TODO - remove or log
	}
	
	private String getMsg(ReportData data) {
		return String.format(
				"%s.%s.%s.%s", 
				data.getTestSuiteName(), data.getElementName(), 
				data.getElementType(), data.getElementTestType());
	}

}

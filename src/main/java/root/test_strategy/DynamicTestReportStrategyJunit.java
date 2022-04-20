/**
 * 
 */
package root.test_strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;

import root.test_results.DynamicTestFail;
import root.test_results.DynamicTestIgnored;
import root.test_results.DynamicTestPass;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Leverage JUnit to report the results.
 */
public class DynamicTestReportStrategyJunit implements DynamicTestReportStrategy {

	@Override
	public void reportPass(DynamicTestPass result) {
		assertTrue(true);
	}

	@Override
	public void reportFail(DynamicTestFail result) {
		assertEquals(result.getReportData().getExpected(), result.getReportData().getActual());
	}

	@Override
	public void reportIgnored(DynamicTestIgnored result) {
		LogManager
			.getLogger(DynamicTestReportStrategyJunit.class)
			.error("ReportIgnored not implemented for JUnit strategy.");
	}

}

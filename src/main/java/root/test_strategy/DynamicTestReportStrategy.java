/**
 * 
 */
package root.test_strategy;

import root.test_results.DynamicTestFail;
import root.test_results.DynamicTestIgnored;
import root.test_results.DynamicTestPass;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface DynamicTestReportStrategy {
	void reportPass(DynamicTestPass result);
	void reportFail(DynamicTestFail result);
	void reportIgnored(DynamicTestIgnored result);
}

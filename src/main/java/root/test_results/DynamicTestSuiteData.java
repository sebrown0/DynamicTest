/**
 * 
 */
package root.test_results;

import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestSuiteData {	
	private final String testSuiteName;	

	public DynamicTestSuiteData(final MenuItem item) {

		this.testSuiteName = item.getName();
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}
		
}

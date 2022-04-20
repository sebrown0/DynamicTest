/**
 * 
 */
package root.common;

import java.util.List;

import root.elements.IncludedElements;
import root.test_strategy.DynamicTestReportStrategy;

//import dynamic_tests.elements.IncludedElements;
//import dynamic_tests.test_strategy.DynamicTestReportStrategy;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * The information elements from the XML file.
 */
public interface XmlInfo {
	IncludedElements getIncludedElements();	
	List<String> getReportOnTests();
	DynamicTestReportStrategy getTestReportStrategy();
}

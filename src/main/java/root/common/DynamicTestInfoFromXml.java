/**
 * 
 */
package root.common;

import java.util.List;

import site_mapper.jaxb.pom.DynamicTestInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface DynamicTestInfoFromXml {
	List<String> getIncludedElementsForTest();
	DynamicTestInfo getDynamicTestInfo();
}

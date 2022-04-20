/**
 * 
 */
package root.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add getName & getType.
 * @version 1.2
 * 	Remove or update createTests() & getTests().
 * @since 1.0
 * 
 * Data required from ElementTest.
 * 
 */
public interface TestElementDetails {
	String getName();
	String getElementType();
	String getElementTestType();
	String getTestBelongsTo();
	
	List<DynamicTest> getTestList();
	
	void setElementTestType(String type);	
	
}

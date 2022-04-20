/**
 * 
 */
package root.test_adders;

import root.test_elements.ElementTestFactory;
import root.test_elements.TestElementDetails;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Add a dynamic test(s) for the element
 * to ElementTest, using the factory.
 */
public interface TestAdder {
	void addTestsWith(ElementTestFactory testFactory, TestElementDetails details);
}

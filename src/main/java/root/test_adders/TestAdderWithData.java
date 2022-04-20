/**
 * 
 */
package root.test_adders;

import root.test_elements.DataInserter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public interface TestAdderWithData extends TestAdder {
	DataInserter getDataInserter();
}

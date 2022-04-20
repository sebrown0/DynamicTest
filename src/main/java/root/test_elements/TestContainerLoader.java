/**
 * 
 */
package root.test_elements;

import library.common.controls.interfaces.ControlTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Use when we want to load a container for 
 * a test.
 */
public interface TestContainerLoader {
	void isItemIsNotLoaded(boolean itemIsNotLoaded);	
	boolean itemIsNotLoaded() ;
	ControlTest getControlTest();		
	void setControlTest(ControlTest controlTest);
		
}

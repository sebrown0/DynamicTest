/**
 * 
 */
package root.test_elements;

import root.test_adders.TestAdderButton;
import root.test_adders.TestAdderComboSelectOnly;
import root.test_adders.TestAdderLabel;
import root.test_adders.TestAdderTextOut;
import site_mapper.elements.ElementCreation;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DynamicTestFactory {

	/**
	 * Get the test for the element.
	 */
	public void addElementSpecificTestsTo(ElementTest test, ElementCreation el) {		
		String elType = el.getElementType();
		switch (elType) {
			case "button", "Button" -> {
				test.addTests(new TestAdderButton(el));
			}
			case "TextSelect", "TextOut" -> {
				test.addTests(new TestAdderTextOut(el));						
			}
			case "ComboSelectOnly" -> {
				test.addTests(new TestAdderComboSelectOnly(el));						
			}
			case "label", "Label" -> {
				test.addTests(new TestAdderLabel(el));						
			}
			default -> { 
				test = null;
				throw new IllegalArgumentException("Unexpected value: " + elType); 
			}
		}  		
	}	
		
}

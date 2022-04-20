/**
 * 
 */
package root.test_adders;

import root.test_elements.ElementTestFactory;
import root.test_elements.TestElementDetails;
import site_mapper.elements.ElementCreation;
import site_mapper.elements.ElementDetails;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestAdderButton implements TestAdder {
	private String text;
	private String fafa;	
	private String toolTipText;
	
	public TestAdderButton(ElementCreation e) {
		text = e.getText();
		fafa = e.getFafa();
		toolTipText = ((ElementDetails)e).getToolTipText();		
	}
		
	@Override
	public void addTestsWith(ElementTestFactory testFactory, TestElementDetails details) {
		if(fafa != null) testFactory.createButtonFaFaCheck(details, fafa); 
		if(text != null) testFactory.createTextCheck(details, text);
		if(toolTipText != null) testFactory.createToolTipCheck(details, toolTipText);					
	}
	
}

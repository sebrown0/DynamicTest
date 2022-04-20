/**
 * 
 */
package root.test_elements;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class TestElementData {
	private String textActual;
	private String textExpected;
	
	public String getTextActual() {
		return textActual;
	}
	public String getTextExpected() {
		return textExpected;
	}
	public TestElementData setTextActual(String textActual) {
		this.textActual = textActual;
		return this;
	}
	public TestElementData setTextExpected(String textExpected) {
		this.textExpected = textExpected;
		return this;
	}	
	
}

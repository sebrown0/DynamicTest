/**
 * 
 */
package root.assertations;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface ReportData {
	String getTestSuiteName();
	String getElementName();
	String getElementType();
	String getExpected();
	String getActual();
	String getElementTestType();
}

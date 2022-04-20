package root.common;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;

import root.elements.IncludedElements;
import root.elements.IncludedTests;
import root.test_strategy.DynamicTestReportStrategy;
import root.test_strategy.DynamicTestReportStrategyConsole;
import root.test_strategy.DynamicTestReportStrategyJunit;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for dynamic test info from the XML file.
 */
public class XmlDynamicTestContent implements XmlInfo { 
	private IncludedElements includedElements;
	private DynamicTestReportStrategy strat;
	
	private final DynamicTestInfoFromXml infoFromXml;
		
	public XmlDynamicTestContent(DynamicTestInfoFromXml infoFromXml) {
		this.infoFromXml = infoFromXml;
		
		setIncludedElements();
		setReportingStrategy();
	}

	private void setReportingStrategy() {		 
		var stratType = infoFromXml.getDynamicTestInfo().getReportStrategy();
		if(stratType.equalsIgnoreCase("junit")) {
			strat = new DynamicTestReportStrategyJunit();
		}else if(stratType.equalsIgnoreCase("console")) {
			strat = new DynamicTestReportStrategyConsole(); 	
		}else {
			LogManager
				.getLogger(XmlDynamicTestContent.class)
				.error(
					String.format(
							"Dynamic test reporting strategy not implemented for type [%s] (from XML)", 
							stratType));
		}
	}

	private void setIncludedElements() {
		includedElements = new IncludedTests(infoFromXml.getIncludedElementsForTest());
	}
	
	@Override
	public IncludedElements getIncludedElements() {
		return includedElements;
	}
	@Override
	public DynamicTestReportStrategy getTestReportStrategy() {	
		return strat;
	}
	@Override
	public List<String> getReportOnTests() {		
		return Arrays.asList(infoFromXml.getDynamicTestInfo().getReportResults());
	}	
	
}

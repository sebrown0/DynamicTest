/**
 * 
 */
package root.common;

import java.util.List;

import app.xml_content.XmlTestContent;
import site_mapper.jaxb.pom.DynamicTestInfo;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Take the XML content and transform it into
 * that required for dynamic tests.
 */
public class DynamicTestInfoTransformer implements DynamicTestInfoFromXml {
	private XmlTestContent content;
		
	public DynamicTestInfoTransformer(XmlTestContent content) {
		this.content = content;
	}
	
//private void copyInfo(DynamicTestInfo info) {
///*
// * This is from SiteMapper - so tightly coupled.
// */
//dynamicTestInfo = new DynamicTestInfoSetter();
//dynamicTestInfo
//	.setReportStrategy(info.getReportStrategy())
//	.setReportResultType(info.getReportResults());
//}
	
	/*
	 * TODO 
	 * THIS SHOULD TRANSFORM THE SiteMapper DynamicTestInfo
	 * INTO A DTest DynamicTestInfo. TO REDUCE THE COUPLING WITH SiteMapper.
	 */
	@Override
	public DynamicTestInfo getDynamicTestInfo() {
		return content.getDynamicTestInfo();
	}

	@Override
	public List<String> getIncludedElementsForTest() {	
		return content.getIncludeElementsForTest();
	}
	
}

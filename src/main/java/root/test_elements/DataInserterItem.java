/**
 * 
 */
package root.test_elements;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import library.common.controls.data.InsertItem;
import library.common.controls.data_inserters.DataInserterFactory;
import library.common.controls.data_inserters.TestDataInserter;
import library.common.controls.interfaces.Control;
import library.common.controls.interfaces.ControlTest;
import site_mapper.jaxb.pom.test_data.TestDataItem;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Insert the value from TestDataItem using the
 * insert with object if specified, or if the control
 * can accept a single value use "sendkeys".
 */
public class DataInserterItem implements DataInserter {
	private ControlTest controlTest;
	private Control control;	
	private TestDataItem data;
	
	private final Logger LOGGER = LogManager.getLogger(DataInserterItem.class);
	
	public DataInserterItem(TestDataItem data) {
		this.data = data;		
	}
	//remove
	public DataInserterItem(TestDataItem data, ControlTest controlTest, Control control) {
		this.data = data;
		this.controlTest = controlTest;
		this.control = control;
	}

	@Override
	public void insertData() {
		if(data != null) {
			var insertWith = data.getInsertWith();
			if(insertWith == null || insertWith.equals("") || insertWith.equals("default")) {
				if(control instanceof InsertItem) {
					InsertItem insertItem = (InsertItem) control;
					insertItem.insert(data.getValue());	
				}				
			}else {
				TestDataInserter dataInserter = DataInserterFactory.getDataInserter(data, controlTest);
				dataInserter.insertData();
			}
		}else {
			LOGGER.error("Cannot insert NULL data");
		}
	}

	@Override
	public DataInserter setControlTest(ControlTest controlTest) {
		this.controlTest = controlTest;		
		return this;
	}

	@Override
	public DataInserter setControl(Control control) {
		this.control = control;
		return this;
	}
	@Override
	public DataInserter setData(Object data) {
		if(data instanceof TestDataItem) this.data = (TestDataItem) data;
		return this;		
	}

}

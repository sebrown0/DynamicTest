/**
 * 
 */
package root.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import control_builder.control_getters.ControlGetter;
import control_data.ControlData;
import library.common.controls.adders.ControlAdder;
import library.common.controls.finder.ControlFinder;
import library.common.controls.getters.ElementGetter;
import library.common.controls.interfaces.Control;
import library.common.controls.interfaces.DisplayedText;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlGroup implements Control, DisplayedText{
	private WebDriver driver;
	private By locator;
	private WebElement cntrl;
	private ControlFinder controlFinder;
	private List<ControlData> controlData = new ArrayList<>();
	
	public ControlGroup() {	}

	public ControlGroup(WebDriver driver, By locator) {
		this.driver = driver;
		this.locator = locator;
		this.cntrl = driver.findElement(locator);
	}
	
	public ControlGroup addElements(List<ControlGetter> elements, ControlAdder adder) {
		if(elements != null) {
			elements.forEach(v -> {
				adder.addElement(v, controlData);
			});
		}
		return this;
	}

	public Optional<Control> getControlByTitle(String title) {
		controlFinder = new ControlFinder(controlData);
		return controlFinder.getControlByTitle(title);
	}
	
	@Override //Control
	public boolean isAvailable() {
		cntrl = new ElementGetter(driver).getElementIfClickable(this);
		return (cntrl != null) ? true : false;	
	}
	
	@Override //Control
	public By getLocator() {
		return locator;
	}

	@Override //Control
	public WebDriver getDriver() {
		return driver;
	}

	@Override //Control
	public WebElement getElement() {
		return cntrl;
	}
	
	@Override //DisplayedText
	public String getText() {
		if(isAvailable()) {
			return cntrl.getText().trim();	
		}else {
			return null;
		}		
	}
}

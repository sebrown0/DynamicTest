package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import factories.DriverFactory;
import library.dto.entites.company.Company;
import library.dto.entites.user.User;
import library.helpers.login.UserLoginPage;
import library.object_models.modules.payroll.PayrollModuleElements;
import library.object_models.modules.payroll.left_menu.employees.SalaryDetails;
import library.pages.homepage.HomePage;
import root.finders.ClassFinder;
import root.finders.ClassInstantiator;
import root.finders.PomClassFinder;
import root.finders.PomInstantiator;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial 
 * @since 1.0
 * 
 * 
 */
class ClassFinderTest {
	private static final String FROM_LIBRARY = 
			"C:/Users/SteveBrown/eclipse-workspace/2021/DakarHR-Library";
	
	private static MenuItem item;
	private static HomePage hpPayroll;
	
	@BeforeAll	
	public static void setup() {
		item = new MenuItem();
		item.setTestClassName("SalaryDetails");
		item.setTestModuleName("payroll");
		item.setTestMenuName("left_menu");
		item.setTestPackage("employees");
		
		Company co = new Company("A Comp");
		UserLoginPage userLoginPayroll = 
			new UserLoginPage(
					DriverFactory.getDriver(""), "http://deploy.dakarhr.com/DakarHR.php", new PayrollModuleElements(co));
		hpPayroll = userLoginPayroll.loginValidUser(new User("portal2", "123"));
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		hpPayroll.close();
	}
		
	@Test
	void create_classFinder() {
		ClassFinder finder = new PomClassFinder("", null);		
		assertTrue(finder != null);
	}
	
	@Test
	void getClazz_fromDakarHR_Library() {		
		ClassFinder finder = new PomClassFinder("root", item);		
		Class<?> clazz = finder.getClazz();
				
		assertEquals("SalaryDetails", clazz.getSimpleName());
	}
	
	@Test
	void getObj_fromDakarHR_Library() {
		ClassInstantiator instantiator = 
			new PomInstantiator(
				FROM_LIBRARY, 
				item, 
				hpPayroll);
				
		Object obj = instantiator.getInstantiatedClass();
		SalaryDetails salaryDetails = (SalaryDetails) obj;
		
		assertEquals("SalaryDetails", salaryDetails.getClass().getSimpleName());
	}
	
}

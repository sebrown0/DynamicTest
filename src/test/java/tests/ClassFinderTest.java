package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import root.finders.ClassFinder;
import site_mapper.jaxb.menu_items.MenuItem;

class ClassFinderTest {

	@Test
	void create_classFinder() {
		ClassFinder finder = 
			new ClassFinder(
					"/DakarHR-Library/src/main/java/library/object_models");
		
		assertTrue(finder != null);
	}
	
//	@Test
//	void getClazz_fromDTestCommon() {		
//		ClassFinder finder = new ClassFinder("root");
//		
//		Class<?> clazz = 
//				finder.getClazz(
//						"C:/Users/SteveBrown/eclipse-workspace/2021/DTestCommon", 
//						"core_data", 
//						"CoreData");
//		
//		assertEquals("CoreData", clazz.getSimpleName());
//	}

	@Test
	void getClazz_fromDakarHR_Library() {		
		ClassFinder finder = new ClassFinder("root");
		/*
		 * have to give library (project) path & name, i.e. "C:/Users/SteveBrown/eclipse-workspace/2021/DakarHR-Library"
		 * package com = library.object_models.modules
		 */
		MenuItem item = new MenuItem();
		item.setTestClassName("SalaryDetails");
		item.setTestModuleName("payroll");
		item.setTestMenuName("left_menu");
		item.setTestPackage("employees");
		
		Class<?> clazz = finder.getClazz(item);
				
		assertEquals("SalaryDetails", clazz.getSimpleName());
	}
	
//	@Test
//	void getClazz() {
//		// C:\Users\SteveBrown\eclipse-workspace\2021\DakarHR-Library\src\main\java\library\object_models\modules\payroll\left_menu\employees
////		ClassFinder finder = 
////			new ClassFinder(
////					"C:/Users/SteveBrown/eclipse-workspace/2021/DakarHR-Library/src/main/java/library/object_models/modules/payroll/left_menu/employees");
//		
//		ClassFinder finder = 
//				new ClassFinder(
//						"root");
//		
////		MenuItem item = new MenuItem();
////		item.setTestClassName("FunctionTest");
////		item.setTestModuleName("mappers");
////		item.setTestMenuName("functions");
//		
////		Class<?> clazz = finder.getClazz("C:/Users/SteveBrown/eclipse-workspace/2021/DynamicTest", "root.mappers.functions", "FunctionTest");
//		
////		Class<?> clazz = 
////				finder.getClazz(
////						"C:/Users/SteveBrown/eclipse-workspace/2021/DakarHR-Library", 
////						"library.object_models.modules.payroll.left_menu.employees", 
//	library.object_models.modules.payroll.left_menu.employees
////						"SalaryDetails");
//		
//		Class<?> clazz = 
//				finder.getClazz(
//						"C:/Users/SteveBrown/eclipse-workspace/2021/DTestCommon", 
//						"core_data", 
//						"CoreData");
//		
//		assertEquals("CoreData", clazz.getSimpleName());
//	}

}

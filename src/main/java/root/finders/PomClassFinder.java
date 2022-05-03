package root.finders;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.logging.log4j.LogManager;

import site_mapper.elements.ElementClass;
import static utils.text_utils.StringUtils.*;
import utils.clazz.PackageNameResolver;
import utils.text_utils.StringUtils;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial 
 * @since 1.0
 * 
 * Get a POM class file from the
 * specified project.
 */
public class PomClassFinder implements ClassFinder {
	private final String inProject;
	private final ElementClass nodeClass;
	
	public PomClassFinder(String inProject, ElementClass nodeClass) {
		this.inProject = inProject;
		this.nodeClass = nodeClass;
	}

	@Override
	public Class<?> getClazz(){			
			File f = new File(inProject);
			URLClassLoader cl = null;			
			
			try {				
				URL[] cp = {f.toURI().toURL()};
				cl = new URLClassLoader(cp);
				Class<?> c = cl.loadClass(getFullPath());
				cl.close();
				return c;				
			} catch (IOException | ClassNotFoundException e) {
				logError(e);
				try {
					cl.close();
				} catch (IOException e1) {
					logError(e);
				}
				logError(e);
			} 
			return null;
		}
	
		private String getFullPath(){
			return getPackage() + "." + nodeClass.getClassName();
		}
		
		private String getPackage(){
			String pack = 
					getSourcePackage() + 
					"." + firstCharToLower(nodeClass.getModuleName()) +
					"." + firstCharToLower(nodeClass.getParentPackage()) +
					"." + new PackageNameResolver(nodeClass.getPackage()).getPackageInCorrectFormat();
			return pack;
		}
		
		private String getSourcePackage() {
			String pckge = 
				StringUtils.replaceFwdSlashes(
						nodeClass.getSiteMapInfo().getParentPackage(), ".");					
			
			return pckge;
		}
				
		private void logError(Exception e) {
			 LogManager
			 	.getLogger(PomClassFinder.class)
			 	.error("Could not find class for [" + nodeClass + "] ERROR -> " + e.getMessage());
		}
}

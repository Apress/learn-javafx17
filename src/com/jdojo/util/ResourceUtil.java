package com.jdojo.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ResourceUtil {
	// Where the resources directory is, seen from current working
	// directory. This differs from build tool to build tool, and
	// from IDE to IDE, so you might have to adapt this.
	private final static String RSRC_PATH_FROM_CURRENT_DIR = "bin";
	
	public static URL getResourceURL(String inResourcesPath) {
        var fStr = (RSRC_PATH_FROM_CURRENT_DIR + 
        		"/resources/" +
        		inResourcesPath).replace("/", File.separator);
        try {
			return new File(fStr).getCanonicalFile().toURI().toURL();
		} catch (IOException e) {
			System.err.println("Cannot fetch URL for '" + inResourcesPath + "'");
			System.err.println("""
					If the path is correct, try to adapt the
			        RSRC_PATH_FROM_CURRENT_DIR constant in class
			        ResourceUtil""".stripIndent());
			e.printStackTrace(System.err);
			return null;
		}
	}

	public static String getResourceURLStr(String inResourcesPath) {
		return getResourceURL(inResourcesPath).toString();
	}

	public static String getResourcePath(String inResourcesPath) {
        var fStr = (RSRC_PATH_FROM_CURRENT_DIR + 
        		"/resources/" +
        		inResourcesPath).replace("/", File.separator);
		return new File(fStr).getAbsolutePath();
	}
}

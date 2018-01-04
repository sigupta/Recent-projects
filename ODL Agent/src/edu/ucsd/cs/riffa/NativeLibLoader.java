/*******************************************************************************
 * This software is Copyright © 2012 The Regents of the University of 
 * California. All Rights Reserved.
 * 
 * Permission to copy, modify, and distribute this software and its 
 * documentation for educational, research and non-profit purposes, without fee, 
 * and without a written agreement is hereby granted, provided that the above 
 * copyright notice, this paragraph and the following three paragraphs appear in
 * all copies.
 * 
 * Permission to make commercial use of this software may be obtained by 
 * contacting:
 * Technology Transfer Office
 * 9500 Gilman Drive, Mail Code 0910
 * University of California
 * La Jolla, CA 92093-0910
 * (858) 534-5815
 * invent@ucsd.edu
 * 
 * This software program and documentation are copyrighted by The Regents of the
 * University of California. The software program and documentation are supplied
 * "as is", without any accompanying services from The Regents. The Regents does
 * not warrant that the operation of the program will be uninterrupted or error-
 * free. The end-user understands that the program was developed for research 
 * purposes and is advised not to rely exclusively on the program for any 
 * reason.
 * 
 * IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO
 * ANY PARTY FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR
 * CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING
 * OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION,
 * EVEN IF THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE. THE UNIVERSITY OF
 * CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 * THE SOFTWARE PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, 
 * AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATIONS TO
 * PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR
 * MODIFICATIONS.
 */

/*
 * Filename: NativeLibLoader.java
 * Version: 2.0
 * Description: Java API for RIFFA.
 * Author: Matthew Jacobsen
 * History: @mattj: Initial release. Version 2.0.
 */

/**
 * Utility to load the JNI RIFFA library.
 */
package edu.ucsd.cs.riffa;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class NativeLibLoader {

	/**
	 * Default constructor.
	 */
	private NativeLibLoader() { }

	/**
	 * Returns a String representing the platform. Format: ${os.arch}/${os.name}
	 *
	 * @returns A String representing the platform.
	 */
	public static String getCurrentPlatformIdentifier() {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			osName = "Windows";
		}
		return System.getProperty("os.arch") + "/" + osName;
	}

	/**
	 * Loads a library from the same ClassLoader as this class at the path:
	 * /native/${os.arch}/${os.path}/lib[libName].[so|dll]
	 * If no such path is found, attemts to load the library by name using the
	 * System.loadLibrary function which searches the java.library.path system
	 * property.
	 *
	 * @param libName - Library name, without the lib prefix or any suffix.
	 */
	public static void loadLibrary(String libName) {
		boolean usingEmbedded = false;
		// Attempt to locate embedded native library within JAR at:
		// /native/${os.arch}/${os.name}/lib[libName].[so|dll]
		String[] exts = new String[] { "so", "dll" };
		StringBuffer url = new StringBuffer();
		url.append("/native/");
		url.append(getCurrentPlatformIdentifier());
		url.append("/lib");
		url.append(libName);
		url.append(".");
		URL nativeLibraryUrl = null;
		// Loop through extensions, stopping after finding first one
		for (int i=0; i < exts.length; i++) {
			nativeLibraryUrl = NativeLibLoader.class.getResource(url.toString() + exts[i]);
			if (nativeLibraryUrl != null)
				break;
		}

		if (nativeLibraryUrl != null) {
			// Native library found within JAR, extract and load
			try {
				final File libfile = File.createTempFile(libName + "-", ".lib");
				libfile.deleteOnExit(); // just in case

				final InputStream in = nativeLibraryUrl.openStream();
				final OutputStream out = new BufferedOutputStream(new FileOutputStream(libfile));

				int len = 0;
				byte[] buffer = new byte[8192];
				while ((len = in.read(buffer)) > -1)
					out.write(buffer, 0, len);
				out.close();
				in.close();

				System.load(libfile.getAbsolutePath());

				libfile.delete();

				usingEmbedded = true;

			} catch (IOException x) {
				// mission failed, do nothing
			}
		}

		// If not loaded, try loading using the java.library.path
		if (!usingEmbedded)
			System.loadLibrary(libName);
	}
}

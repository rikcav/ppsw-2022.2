package br.upe.ppsw.jabberpoint.model;

import java.io.File;
import java.io.FileNotFoundException;

public class AccessorFactory {

	public static IFileLoader loadAccessor(String file)
			throws FileNotFoundException {

		String filename = new File(file).getName();
		String extension = null;
		if (filename != null)
			extension = filename.substring(filename.lastIndexOf("."));

		if (".xml".equals(extension))
			return new XMLAccessor();
		else if (".json".equals(extension))
			return new JSONAccessor();
		else if (".html".equals(extension))
			return new HTMLAccessor();
		else
			return new DemoPresentation();
	}

	public static IFileSaver saveAccessor(String file)
			throws FileNotFoundException {

		String filename = new File(file).getName();
		String extension = null;
		if (filename != null)
			extension = filename.substring(filename.lastIndexOf("."));

		if (".xml".equals(extension))
			return new XMLAccessor();
		else if (".json".equals(extension))
			return new JSONAccessor();
		else if (".html".equals(extension))
			return new HTMLAccessor();
		else
			return null;
	}
}
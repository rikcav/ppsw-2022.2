package br.upe.ppsw.jabberpoint.control;

import java.io.File;
import java.io.FileNotFoundException;

import br.upe.ppsw.jabberpoint.model.DemoPresentation;
import br.upe.ppsw.jabberpoint.model.IFileLoader;
import br.upe.ppsw.jabberpoint.model.IFileSaver;
import br.upe.ppsw.jabberpoint.model.JSONAccessor;
import br.upe.ppsw.jabberpoint.model.XMLAccessor;
import br.upe.ppsw.jabberpoint.model.YMLAccessor;

public class AccessorController {

	public IFileLoader loadAccessor(String file) throws FileNotFoundException {

		String filename = new File(file).getName();
		String extension = null;
		if (filename != null)
			extension = filename.substring(filename.lastIndexOf("."));

		if (".xml".equals(extension))
			return new XMLAccessor();
		else if (".json".equals(extension))
			return new JSONAccessor();
		else if (".yml".equals(extension))
			return new YMLAccessor();
		else
			return new DemoPresentation();

	}

	public IFileSaver saveAccessor(String file) throws FileNotFoundException {

		String filename = new File(file).getName();
		String extension = null;
		if (filename != null)
			extension = filename.substring(filename.lastIndexOf("."));

		if (".xml".equals(extension))
			return new XMLAccessor();
		else if (".json".equals(extension))
			return new JSONAccessor();
		else if (".yml".equals(extension))
			return new YMLAccessor();
		else
			return null;

	}
}
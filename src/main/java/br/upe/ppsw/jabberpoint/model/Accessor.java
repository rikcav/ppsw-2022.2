package br.upe.ppsw.jabberpoint.model;

import java.io.IOException;

public abstract class Accessor implements IFileLoader, IFileSaver {

	public static final String DEMO_NAME = "Apresentação de Demonstração";
	public static final String DEFAULT_EXTENSION = ".xml";

	public Accessor() {
	}

	abstract public void loadFile(Presentation presentation, String fileName)
			throws IOException;

	abstract public void saveFile(Presentation presentation, String fileName)
			throws IOException;

}
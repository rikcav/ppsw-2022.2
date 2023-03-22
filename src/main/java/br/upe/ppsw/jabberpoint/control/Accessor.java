package br.upe.ppsw.jabberpoint.control;

import java.io.IOException;

import br.upe.ppsw.jabberpoint.model.Presentation;

public abstract class Accessor {

	public static final String DEMO_NAME = "Apresentação de Demonstração";
	public static final String DEFAULT_EXTENSION = ".xml";

	public Accessor() {
	}

	abstract public void loadFile(Presentation presentation, String fileName) throws IOException;

	abstract public void saveFile(Presentation presentation, String fileName) throws IOException;

}

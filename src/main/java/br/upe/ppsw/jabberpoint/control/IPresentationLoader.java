package br.upe.ppsw.jabberpoint.control;

import java.io.FileNotFoundException;

import br.upe.ppsw.jabberpoint.model.Presentation;

public interface IPresentationLoader {
	public void loadPresentation(Presentation presentation, String unusedFilename) throws FileNotFoundException;
}

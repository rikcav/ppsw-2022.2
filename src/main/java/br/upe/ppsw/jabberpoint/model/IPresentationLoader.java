package br.upe.ppsw.jabberpoint.model;

import java.io.FileNotFoundException;

public interface IPresentationLoader {
	public void loadPresentation(Presentation presentation, String unusedFilename)
			throws FileNotFoundException;
}

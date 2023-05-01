package br.upe.ppsw.jabberpoint.model;

import java.io.IOException;

public interface IFileLoader {
	public void loadFile(Presentation presentation, String unusedFilename)
			throws IOException;
}

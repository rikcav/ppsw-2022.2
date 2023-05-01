package br.upe.ppsw.jabberpoint.model;

import java.io.IOException;

public interface IFileSaver {
	public void saveFile(Presentation presentation, String fileName)
			throws IOException;
}

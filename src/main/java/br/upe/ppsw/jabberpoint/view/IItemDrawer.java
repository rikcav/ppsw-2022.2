package br.upe.ppsw.jabberpoint.view;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import br.upe.ppsw.jabberpoint.model.Style;

public interface IItemDrawer {
	public void draw(
			int x, int y, float scale,
			Graphics g, Style myStyle,
			ImageObserver o
			);
}

package br.upe.ppsw.jabberpoint.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Vector;

import br.upe.ppsw.jabberpoint.model.Slide;
import br.upe.ppsw.jabberpoint.model.SlideItem;
import br.upe.ppsw.jabberpoint.model.Style;
import br.upe.ppsw.jabberpoint.model.TextItem;

public class SlideDrawer {

	public final static int WIDTH = 1200;
	public final static int HEIGHT = 800;

	private Slide slide;

	public SlideDrawer(Slide slide) {
		this.slide = slide;
	}

	public void draw(Graphics g, Rectangle area, ImageObserver view) {
		float scale = getScale(area);

		int y = area.y;

		TextItem title = slide.title;
		Style style = Style.getStyle(title.getLevel());
		title.draw(area.x, y, scale, g, style, view);

		y += title.getBoundingBox(g, view, scale, style).height;

		Vector<SlideItem> items = slide.getSlideItems();
		for (int number = 0; number < items.size(); number++) {
			SlideItem slideItem = (SlideItem) items.elementAt(number);

			style = Style.getStyle(title.getLevel());
			slideItem.draw(area.x, y, scale, g, style, view);

			y += title.getBoundingBox(g, view, scale, style).height;
		}
	}

	private float getScale(Rectangle area) {
		return Math.min(((float) area.width) / ((float) WIDTH),
				((float) area.height) / ((float) HEIGHT));
	}
}

package br.upe.ppsw.jabberpoint.view;

import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.font.TextLayout;
import java.awt.image.ImageObserver;
//import java.util.Iterator;
//import java.util.List;

import br.upe.ppsw.jabberpoint.model.Style;
import br.upe.ppsw.jabberpoint.model.TextItem;

public class TextItemDrawer implements IItemDrawer {

	TextItem textItem = new TextItem();
	
	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver o) {
		if (textItem.getText() == null || textItem.getText().length() == 0) {
			return;
		}

//		List<TextLayout> layouts = textItem.getLayouts(g, myStyle, scale);
//		Point pen = new Point(x + (int) (myStyle.indent * scale), y + (int) (myStyle.leading * scale));

//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(myStyle.color);

//		Iterator<TextLayout> it = layouts.iterator();

//		while (it.hasNext()) {
//			TextLayout layout = it.next();
//
//			pen.y += layout.getAscent();
//			layout.draw(g2d, pen.x, pen.y);
//
//			pen.y += layout.getDescent();
//		}
	}

}

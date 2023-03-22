package br.upe.ppsw.jabberpoint.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import br.upe.ppsw.jabberpoint.model.Presentation;

public class KeyController extends KeyAdapter {

	private Presentation presentation;

	public KeyController(Presentation p) {
		presentation = p;
	}

	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
		case KeyEvent.VK_PAGE_DOWN:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_ENTER:
		case KeyEvent.VK_EQUALS:
		case KeyEvent.VK_N:
		case KeyEvent.VK_J:
			presentation.nextSlide();
			break;
		case KeyEvent.VK_PAGE_UP:
		case KeyEvent.VK_UP:
		case KeyEvent.VK_MINUS:
		case KeyEvent.VK_P:
		case KeyEvent.VK_K:
			presentation.prevSlide();
			break;
		case KeyEvent.VK_Q:
			System.exit(0);
			break; // fix?
		default:
			break;
		}
	}

}

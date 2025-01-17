package br.upe.ppsw.jabberpoint.control;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.upe.ppsw.jabberpoint.model.Accessor;
import br.upe.ppsw.jabberpoint.model.AccessorFactory;
import br.upe.ppsw.jabberpoint.model.HTMLAccessor;
import br.upe.ppsw.jabberpoint.model.Presentation;
import br.upe.ppsw.jabberpoint.model.XMLAccessor;
import br.upe.ppsw.jabberpoint.view.AboutBox;

public class MenuController extends MenuBar {

	private static final long serialVersionUID = 227L;

	private Frame parent;
	private Presentation presentation;

	protected static final String ABOUT = "Sobre";
	protected static final String FILE = "Arquivo";
	protected static final String EXIT = "Sair";
	protected static final String GOTO = "Pular para";
	protected static final String HELP = "Ajuda";
	protected static final String NEW = "Novo";
	protected static final String NEXT = "Próximo";
	protected static final String OPEN = "Abrir";
	protected static final String PAGENR = "Número do Slide?";
	protected static final String PREV = "Anterior";
	protected static final String SAVE = "Salvar";
	protected static final String VIEW = "Visualizar";

	protected static final String TESTFILE = "classpath:test.xml";
	protected static final String SAVEFILE = "classpath:dump.xml";

	protected static final String IOEX = "IO Exception: ";
	protected static final String LOADERR = "Erro ao carregar";
	protected static final String SAVEERR = "Erro ao salvar";

	public MenuController(Frame frame, Presentation pres) {
		parent = frame;
		presentation = pres;

		MenuItem menuItem;

		Menu fileMenu = new Menu(FILE);
		fileMenu.add(menuItem = mkMenuItem(OPEN, 'O'));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				try {
					openFile();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(
							parent,
							IOEX + e,
							LOADERR,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		fileMenu.add(menuItem = mkMenuItem(NEW));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.clear();
				parent.repaint();
			}
		});

		fileMenu.add(menuItem = mkMenuItem(SAVE, 'S'));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveFile();
				} catch (IOException exc) {
					JOptionPane.showMessageDialog(
							parent,
							IOEX + exc,
							SAVEERR,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		fileMenu.addSeparator();

		fileMenu.add(menuItem = mkMenuItem(EXIT));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.exit(0);
			}
		});

		add(fileMenu);

		Menu viewMenu = new Menu(VIEW);
		viewMenu.add(menuItem = mkMenuItem(NEXT));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.nextSlide();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(PREV));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				presentation.prevSlide();
			}
		});

		viewMenu.add(menuItem = mkMenuItem(GOTO));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
				int pageNumber = Integer.parseInt(pageNumberStr);
				presentation.setSlideNumber(pageNumber - 1);
			}
		});

		add(viewMenu);

		Menu helpMenu = new Menu(HELP);
		helpMenu.add(menuItem = mkMenuItem(ABOUT));

		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AboutBox.show(parent);
			}
		});

		setHelpMenu(helpMenu);
	}

	protected void openFile() throws FileNotFoundException {
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showOpenDialog(parent);
		if (option == JFileChooser.APPROVE_OPTION) {
			String filePath = fileChooser.getSelectedFile().getAbsolutePath();

			presentation.clear();
			Accessor accessor = (Accessor) AccessorFactory.loadAccessor(filePath);

			try {
				accessor.loadFile(presentation, filePath);
				presentation.setSlideNumber(0);
			} catch (IOException exc) {
				JOptionPane.showMessageDialog(
						parent,
						IOEX + exc,
						LOADERR,
						JOptionPane.ERROR_MESSAGE);
			}
			parent.repaint();
		}
	}
	
	public void saveFile() throws FileNotFoundException {
	    JFileChooser fileChooser = new JFileChooser();
	    int option = fileChooser.showSaveDialog(parent);
	    if (option == JFileChooser.APPROVE_OPTION) {
	        String filePath = fileChooser.getSelectedFile().getAbsolutePath();

	        Accessor accessor = (Accessor) AccessorFactory.saveAccessor(filePath);
	        try {
	            accessor.saveFile(presentation, filePath);
	        } catch (IOException exc) {
	            JOptionPane.showMessageDialog(
	            		parent,
	            		IOEX + exc,
	            		SAVEERR,
	            		JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}

	public MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}

	protected MenuItem mkMenuItem(String name, char shortcut) {
		MenuItem menuItem = new MenuItem(name, new MenuShortcut(shortcut));
		return menuItem;
	}
}

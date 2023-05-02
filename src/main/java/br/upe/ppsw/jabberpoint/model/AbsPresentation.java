package br.upe.ppsw.jabberpoint.model;

import java.util.List;

import br.upe.ppsw.jabberpoint.view.SlideViewerComponent;

public abstract class AbsPresentation {

	public abstract int getSize();
	public abstract String getTitle();
	public abstract void setTitle(String nt);
	public abstract void setShowView(SlideViewerComponent slideViewerComponent);
	public abstract int getSlideNumber();
	public abstract void setSlideNumber(int number);
	public abstract void prevSlide();
	public abstract void nextSlide();
	public abstract void clear();
	public abstract void append(Slide slide);
	public abstract Slide getSlide(int number);
	public abstract Slide getCurrentSlide();
	public abstract void exit(int n);
	public abstract List<Slide> getSlides();
}
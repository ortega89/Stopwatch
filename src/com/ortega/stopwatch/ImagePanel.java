package com.ortega.stopwatch;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
		setSize(img.getWidth(null), img.getHeight(null));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		if (g instanceof Graphics2D) {
			((Graphics2D) g).setBackground(Constants.TRANSPARENT_COLOR);
			getParent().repaint(getX(), getY(), getX()+getWidth(), getY()+getHeight());
			//g.clearRect(0, 0, getWidth(), getHeight());
            g.drawImage(img, 0, 0, null);
        }
	}
}

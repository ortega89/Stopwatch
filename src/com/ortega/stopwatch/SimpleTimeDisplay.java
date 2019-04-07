package com.ortega.stopwatch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SimpleTimeDisplay extends JLabel {
	private BufferedImage img;
	private Image screen;
	
	public SimpleTimeDisplay(Image screen) {
		this(screen.getWidth(null), screen.getHeight(null));
		this.screen = screen;
	}
	
	public SimpleTimeDisplay(int width, int height) {
		setSize(width, height);
		setBackground(Constants.TRANSPARENT_COLOR);
		setForeground(Color.GREEN);
		setOpaque(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(font());
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		clearImage();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		clearImage();
		Graphics ig = img.getGraphics();
		ig.drawImage(screen, 0, 0, null);
		
		ig.setFont(this.getFont());
		super.paintComponent(ig);
		g.drawImage(img, 0, 0, null);
	}

	private void clearImage() {
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setBackground(Constants.TRANSPARENT_COLOR);
		g.clearRect(0, 0, img.getWidth(), img.getHeight());
	}

	private Font font() {
		Font f = new Font("Courier New", Font.BOLD, 18);
		return f;
	}

	public void setData(String data) {
		setText(data);
	}
}

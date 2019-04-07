package com.ortega.stopwatch;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SpriteButton extends JButton {
	private Image imgUp;
	private Image imgDown;
	private boolean buttonDown;
	
	public SpriteButton(Image sprite) {
		setFocusable(false);
		setBackground(Constants.TRANSPARENT_COLOR);
		
		int buttonWidth = sprite.getWidth(null) / 2;
		int buttonHeight = sprite.getHeight(null);
		setSize(buttonWidth, buttonHeight);
		
		imgUp = new BufferedImage(buttonWidth, buttonHeight, BufferedImage.TYPE_INT_ARGB);
		imgDown = new BufferedImage(buttonWidth, buttonHeight, BufferedImage.TYPE_INT_ARGB);
		imgUp.getGraphics().drawImage(sprite, 0, 0, buttonWidth, buttonHeight,
				0, 0, buttonWidth, buttonHeight, null);
		imgDown.getGraphics().drawImage(sprite, 0, 0, buttonWidth, buttonHeight,
				buttonWidth, 0, buttonWidth*2, buttonHeight, null);
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				setButtonDown(false);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				setButtonDown(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setButtonDown(false);
			}
		});
	}

	protected void setButtonDown(boolean newValue) {
		if (buttonDown != newValue) {
			buttonDown = newValue;
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		getParent().repaint(getX(), getY(), getX()+getWidth(), getY()+getHeight());
		g.drawImage(buttonDown ? imgDown : imgUp, 0, 0, null);
	}
	
	
}

package com.ortega.stopwatch;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class WindowDragger {
	private JFrame window;
	private int grabX;
	private int grabY;
	private boolean dragging;

	public WindowDragger(JFrame window) {
		this.window = window;
		addListeners();
	}
	
	private void addListeners() {
		window.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					grabAt(e.getX(), e.getY());
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				release();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				dragTo(e.getXOnScreen(), e.getYOnScreen());
			}
		});
		
		window.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				dragTo(e.getXOnScreen(), e.getYOnScreen());
			}
		});
	}

	public void grabAt(int x, int y) {
		grabX = x;
		grabY = y;
		dragging = true;
	}
	
	public void dragTo(int screenX, int screenY) {
		if (!dragging) {
			return;
		}
		int newX = screenX - grabX;
		int newY = screenY - grabY;
		if (window.getX() != newX || window.getY() != newY) {
			window.setLocation(newX, newY);
		}
	}
	
	public void release() {
		dragging = false;
	}
}

package com.ortega.stopwatch;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class StopwatchFrame extends JFrame {
	private Assets assets;
	private SimpleTimeDisplay display;
	private Timer timer;
	private long startTime;
	private long pauseTime;
	private long idleTime;
	
	public StopwatchFrame() throws IOException {
		assets = Assets.getInstance();
		
		setTitle("Stopwatch");
		initBehavior();
		
		setSize(assets.getEgg().getWidth(null), assets.getEgg().getHeight(null));
		setLocationRelativeTo(null);
		
		JPanel panel = initContentPane();
		initControlsAt(panel);
	}
	
	private JPanel initContentPane() {
		JPanel panel = new ImagePanel(assets.getEgg());
		setContentPane(panel);
		panel.setLayout(null);
		panel.setSize(getSize());
		return panel;
	}

	private void initControlsAt(JPanel panel) {
		display = new SimpleTimeDisplay(assets.getScreen());	
		display.setData("00:00.000");
		panel.add(display);
		
		JPanel titlePanel = new ImagePanel(assets.getTitleImg());
		panel.add(titlePanel);
		
		SpriteButton playButton = new SpriteButton(assets.getPlayPause());
		panel.add(playButton);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!timer.isRunning()) {
					long now = System.currentTimeMillis();
					if (pauseTime == 0) {
						idleTime = 0; 
						startTime = System.currentTimeMillis();
					} else {
						idleTime += now - pauseTime;
					}
					timer.start();
				} else {
					pauseTime = System.currentTimeMillis();
					timer.stop();
				}
			}
		});
		
		SpriteButton resetButton = new SpriteButton(assets.getReset());
		panel.add(resetButton);
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				idleTime = 0;
				pauseTime = 0;
				startTime = System.currentTimeMillis();
				updateDisplay();
			}
		});
		
		SpriteButton offButton = new SpriteButton(assets.getOff());
		panel.add(offButton);
		offButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StopwatchFrame.this.dispatchEvent(
						new WindowEvent(StopwatchFrame.this, WindowEvent.WINDOW_CLOSING));;
			}
		});
		
		timer = new Timer(20, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateDisplay();
			}
		});
		
		center(display);
		center(titlePanel);
		titlePanel.setLocation(titlePanel.getX(), display.getY() - titlePanel.getHeight());
		playButton.setLocation(display.getX()+display.getWidth() / 2 - playButton.getWidth() / 2, display.getY()+display.getHeight()+1);
		resetButton.setLocation(playButton.getX()-resetButton.getWidth(), playButton.getY());
		offButton.setLocation(playButton.getX()+playButton.getWidth(), playButton.getY());
	}

	protected void updateDisplay() {
		long passed = System.currentTimeMillis() - idleTime - startTime;
		long ms = passed % 1000;
		long s = (passed / 1000) % 60;
		long m = (passed / 60000) % 100;
		String value = String.format("%02d:%02d.%03d", m, s, ms);
		display.setData(value);
	}

	private void center(JComponent c) {
		Container p = c.getParent();
		int x = (p.getWidth() - c.getWidth()) / 2;
		int y = (p.getHeight() - c.getHeight()) / 2;
		c.setLocation(x, y);
	}

	private void initBehavior() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setBackground(Constants.TRANSPARENT_COLOR);
		setAlwaysOnTop(true);
		new WindowDragger(this);
	}
}

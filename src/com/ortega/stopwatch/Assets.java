package com.ortega.stopwatch;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Assets {
	private static Assets instance;
	
	private Image egg;
	private Image titleImg;
	private Image playPause;
	private Image reset;
	private Image off;
	private Image screen;
	
	public static synchronized Assets getInstance() throws IOException {
		if (instance == null) {
			instance = new Assets();
		}
		return instance;
	}
	
	private Assets() throws IOException {
		InputStream is = getClass().getResourceAsStream("/egg1-2.png");
		egg = ImageIO.read(is);
		
		is = getClass().getResourceAsStream("/title3-5.png");
		titleImg = ImageIO.read(is);
		
		is = getClass().getResourceAsStream("/play1-5.png");
		playPause = ImageIO.read(is);
		
		is = getClass().getResourceAsStream("/reset1-10.png");
		reset = ImageIO.read(is);
		
		is = getClass().getResourceAsStream("/off1-10.png");
		off = ImageIO.read(is);
		
		is = getClass().getResourceAsStream("/display.png");
		screen = ImageIO.read(is);
	}

	public Image getEgg() {
		return egg;
	}

	public Image getTitleImg() {
		return titleImg;
	}

	public Image getPlayPause() {
		return playPause;
	}

	public Image getReset() {
		return reset;
	}

	public Image getOff() {
		return off;
	}

	public Image getScreen() {
		return screen;
	}
}

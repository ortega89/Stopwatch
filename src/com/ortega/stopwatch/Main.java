package com.ortega.stopwatch;

import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import javax.swing.UIManager;

public class Main {
	public static void main(String[] args) throws Exception {
		checkTransparency();
		setLookAndFeel();
		StopwatchFrame frame = new StopwatchFrame();
		frame.setVisible(true);
	}

	private static void setLookAndFeel() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}

	private static void checkTransparency() {
		GraphicsEnvironment ge = 
	            GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        final boolean isTranslucencySupported = 
        		gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT);

        if (!gd.isWindowTranslucencySupported(WindowTranslucency.PERPIXEL_TRANSPARENT)) {
            System.err.println("Shaped windows are not supported");
            System.exit(0);
        }
        
        if (!isTranslucencySupported) {
            System.out.println("Translucency is not supported");
            System.exit(0);
        }
	}
}

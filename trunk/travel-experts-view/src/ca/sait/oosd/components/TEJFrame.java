package ca.sait.oosd.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class TEJFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	public TEJFrame(String title) {
		super("Travel Experts :: " + title);
		initComponent();
	}
	
	/**
	 * This initialize the basic JFrame components
	 */
	private void initComponent() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
        this.pack();
		
		//finally display the frame
		this.setVisible(true);
	}
	
	/**
	 * This align the window in the center of the screen. 
	 * The initial screen size need to be change
	 */
	protected void alignFrameOnScreen(int width, int height) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);
		
		this.setLocation(x, y);
	}
	
	protected abstract void initGUI();
	protected abstract void adjustSize(int width, int height);

}

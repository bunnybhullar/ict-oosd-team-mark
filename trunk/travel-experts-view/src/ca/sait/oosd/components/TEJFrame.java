package ca.sait.oosd.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public abstract class TEJFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private int width = 600;
	private int height = 400;
	
	public TEJFrame() {
		initComponent();
	}
	
	/**
	 * This initialize the basic JFrame components
	 */
	private void initComponent() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
        this.pack();
//		this.setSize(width, height);
		
//		this.pack();
//		alignFrameOnScreen();
		
		//finally display the frame
		this.setVisible(true);
	}
	
	/**
	 * This align the window in the center of the screen. 
	 * The initial screen size need to be change
	 */
	protected void alignFrameOnScreen(TEJFrame frame) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width / 2) - (frame.width / 2);
		int y = (screen.height / 2) - (frame.height / 2);
		
		this.setLocation(x, y);
	}

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }
	
	protected abstract void initGUI();
	protected abstract void adjustSize(int width, int height);

}

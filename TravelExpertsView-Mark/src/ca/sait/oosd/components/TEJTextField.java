package ca.sait.oosd.components;

import javax.swing.JTextField;

public class TEJTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	private int maxColumnLenght = 25;

	/**
	 * default constructor designed for Text only fields. Text aligned to left hand side
	 * and default length of the text field is 25 characters.
	 */
	public TEJTextField() {
		super();
		
		initComponent(JTextField.LEFT);
	}
	
	public TEJTextField(int horizontalAlignment) {
		super();
		
		initComponent(horizontalAlignment);
	}
	
	public TEJTextField(int maxColumnLenght, int horizontalAlignment) {
		this.maxColumnLenght = maxColumnLenght;
		initComponent(horizontalAlignment);
	}
	
	private void initComponent(int horizontalAlignment) {
		this.setHorizontalAlignment(horizontalAlignment);
		this.setColumns(maxColumnLenght);
	}
}

package ca.sait.oosd.components;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @author duminda
 * @purpose This is the abstract class for the button with an image
 * 
 */
public class ImageButton extends JButton{

    public ImageButton(String image, String caption, int height, int weight) {
        this(new ImageIcon(image), caption, height, weight);
        
    }

    public ImageButton(ImageIcon imageIcon, String caption, int height, int weight) {
        super(caption, imageIcon);
        this.setVerticalAlignment(SwingConstants.TOP);
        this.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.setHorizontalTextPosition(SwingConstants.CENTER);

        Dimension dimension = new Dimension(weight, height);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);

    }

}

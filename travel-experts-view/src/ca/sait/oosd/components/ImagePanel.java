package ca.sait.oosd.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author duminda
 * @purpose This panel hold an image
 * 
 */
public class ImagePanel extends JPanel{

    private Image image;
    private int height, width;
    

    public ImagePanel(Image image, int height, int width) {
        this.image = image;
        this.height = height;
        this.width = width;
        
        Dimension dimension = new Dimension(width, height);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);

        this.setLayout(null);
        
    }

    public ImagePanel(String image, int height, int width) {
        this(new ImageIcon(image).getImage(), height, width);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.width, this.height, null);
    }
}

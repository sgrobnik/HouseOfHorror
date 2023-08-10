// Class : ImagePanel
// paints the background image onto the JPanel (does not resize image)

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;

class ImagePanel extends JPanel {
    private BufferedImage image;
    public ImagePanel(BufferedImage image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}


package ie.dcu.computing.gitlab.java.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UIButton extends JButton {

    public UIButton(String defaultText) {
        this(defaultText, null);
    }

    public UIButton(String defaultText, String iconFileLocation) {
        if (iconFileLocation != null) {
            try {
                BufferedImage icon = ImageIO.read(new File(iconFileLocation));
                setIcon(new ImageIcon(icon));
            }
            catch (IOException e) {
                System.out.println("Cannot find icon file");
                setFont(new Font("Helvetica", Font.PLAIN, 13));
                setText(defaultText);
            }
        }
        else {
            setFont(new Font("Helvetica", Font.PLAIN, 13));
            setText(defaultText);
        }
    }
}

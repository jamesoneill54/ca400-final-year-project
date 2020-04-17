package ie.dcu.computing.gitlab.java.ui;

import javax.swing.*;
import java.awt.*;

public class UILabel extends JLabel {

    UILabel(String text, String labelType) {
        if (labelType.equals("identifier")) {
            setPreferredSize(new Dimension(135, 20));
            setFont(new Font("Helvetica", Font.PLAIN, 13));
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        else {
            setPreferredSize(new Dimension(80, 20));
            setFont(new Font("Monaco", Font.PLAIN, 13));
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            setBackground(Color.WHITE);
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        setText(text);
        setOpaque(true);
    }
}

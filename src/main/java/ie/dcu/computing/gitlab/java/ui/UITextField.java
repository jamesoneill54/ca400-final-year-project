package ie.dcu.computing.gitlab.java.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UITextField extends JTextField {

    UITextField(String initialText) {
        setFont(new Font("Monaco", Font.PLAIN, 13));
        setText(initialText);
        setColumns(5);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();
                if (((typedChar < '0' || typedChar > '9')) && (typedChar != KeyEvent.VK_BACK_SPACE) && (typedChar != '.')) {
                    e.consume();
                }
            }
        });
    }
}

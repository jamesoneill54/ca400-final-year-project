package ie.dcu.computing.gitlab.java.ui;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class TestUILabel {

    @Test
    void instantiateIdentifierTest() {
        UILabel uiLabel = new UILabel("Test", "identifier");
        Assert.assertEquals(new Dimension(135, 20), uiLabel.getPreferredSize());
        Assert.assertEquals(SwingConstants.RIGHT, uiLabel.getHorizontalAlignment());
        Assert.assertEquals("Test", uiLabel.getText());
        Assert.assertTrue(uiLabel.isOpaque());
    }

    @Test
    void instantiateStatusTest() {
        UILabel uiLabel = new UILabel("Test", "status");
        Assert.assertEquals(new Dimension(80, 20), uiLabel.getPreferredSize());
        Assert.assertEquals(SwingConstants.CENTER, uiLabel.getHorizontalAlignment());
        Assert.assertEquals(Color.WHITE, uiLabel.getBackground());
        Assert.assertEquals("Test", uiLabel.getText());
        Assert.assertTrue(uiLabel.isOpaque());
    }
}

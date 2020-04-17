package ie.dcu.computing.gitlab.java.ui;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class TestUIButton {

    @Test
    void instantiationTest() {
        UIButton uiButton = new UIButton("Test", "./res/ui-icons/play-button.png");
        Assert.assertNotNull(uiButton.getIcon());

        uiButton = new UIButton("Test", "no location");
        Assert.assertEquals("Test", uiButton.getText());
        Assert.assertNull(uiButton.getIcon());
    }
}

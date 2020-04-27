package ie.dcu.computing.gitlab.java.ui;

import ie.dcu.computing.gitlab.java.AntColonyOptimisation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

public class TestStatusPanel {

    AntColonyOptimisation acoAlgorithm = new AntColonyOptimisation(1, 2, 1);

    @Test
    void instantiationTest() {
        StatusPanel statusPanel = new StatusPanel(acoAlgorithm);

        Assert.assertEquals("Ready", statusPanel.getStatus().getText());
        Assert.assertEquals("0/0", statusPanel.getIterationCount().getText());
        Assert.assertEquals("0/0", statusPanel.getAttemptCount().getText());
        Assert.assertEquals("0/0", statusPanel.getAliveAntsCount().getText());
        Assert.assertEquals("0/0", statusPanel.getSuccessfulAntsCount().getText());
        Assert.assertEquals("0/0", statusPanel.getUnsuccessfulAntsCount().getText());
        Assert.assertEquals("Not found", statusPanel.getBestRouteLength().getText());
        Assert.assertEquals("Not found", statusPanel.getGlobalBestRouteLength().getText());
    }

    @Test
    void updateStatusTest() {
        StatusPanel statusPanel = new StatusPanel(acoAlgorithm);

        statusPanel.updateStatus("Ready");
        Assert.assertEquals("Ready", statusPanel.getStatus().getText());
        Assert.assertEquals(Color.WHITE, statusPanel.getStatus().getBackground());

        statusPanel.updateStatus("Finished");
        Assert.assertEquals("Finished", statusPanel.getStatus().getText());
        Assert.assertEquals(Color.WHITE, statusPanel.getStatus().getBackground());

        statusPanel.updateStatus("Running");
        Assert.assertEquals("Running", statusPanel.getStatus().getText());
        Assert.assertEquals(Color.GREEN, statusPanel.getStatus().getBackground());

        statusPanel.updateStatus("Paused");
        Assert.assertEquals("Paused", statusPanel.getStatus().getText());
        Assert.assertEquals(Color.YELLOW, statusPanel.getStatus().getBackground());

        statusPanel.updateStatus("Stopped");
        Assert.assertEquals("Stopped", statusPanel.getStatus().getText());
        Assert.assertEquals(Color.PINK, statusPanel.getStatus().getBackground());
    }

    @Test
    void updateStatusExceptionTest() {
        StatusPanel statusPanel = new StatusPanel(acoAlgorithm);

        try {
            statusPanel.updateStatus("Raedy");
            Assert.fail("Expected exception did not occur.");
        }
        catch (IllegalArgumentException e) {
            Assert.assertEquals("Status 'Raedy' not recognised.", e.getMessage());
        }
    }

    @Test
    void updateIterationCountTest() {
        StatusPanel statusPanel = new StatusPanel(acoAlgorithm);

        Assert.assertEquals("0/0", statusPanel.getIterationCount().getText());
        statusPanel.updateIterationCount();
        Assert.assertEquals("1/5", statusPanel.getIterationCount().getText());
    }

    @Test
    void updateAttemptCountTest() {
        StatusPanel statusPanel = new StatusPanel(acoAlgorithm);

        Assert.assertEquals("0/0", statusPanel.getAttemptCount().getText());
        statusPanel.updateAttemptCount();
        Assert.assertEquals("1/4", statusPanel.getAttemptCount().getText());
    }

    @Test
    void updateAntCounts() throws IOException {
        StatusPanel statusPanel = new StatusPanel(acoAlgorithm);

        Assert.assertEquals("0/0", statusPanel.getAliveAntsCount().getText());
        Assert.assertEquals("0/0", statusPanel.getSuccessfulAntsCount().getText());
        Assert.assertEquals("0/0", statusPanel.getUnsuccessfulAntsCount().getText());

        statusPanel.updateAntCounts();
        Assert.assertEquals("1/1", statusPanel.getAliveAntsCount().getText());
        Assert.assertEquals("0/1", statusPanel.getSuccessfulAntsCount().getText());
        Assert.assertEquals("0/1", statusPanel.getUnsuccessfulAntsCount().getText());

        acoAlgorithm.setGoal(0, 1);
        acoAlgorithm.setHome(0, 0);
        acoAlgorithm.setCreateResults(false);
        acoAlgorithm.startOptimisation();
        statusPanel.updateAntCounts();
        Assert.assertEquals("0/1", statusPanel.getAliveAntsCount().getText());
        Assert.assertEquals("1/1", statusPanel.getSuccessfulAntsCount().getText());
        Assert.assertEquals("0/1", statusPanel.getUnsuccessfulAntsCount().getText());
    }

    @Test
    void updateBestRouteLengthsTest() throws IOException {
        StatusPanel statusPanel = new StatusPanel(acoAlgorithm);

        Assert.assertEquals("Not found", statusPanel.getBestRouteLength().getText());
        Assert.assertEquals("Not found", statusPanel.getGlobalBestRouteLength().getText());
        statusPanel.updateBestRouteLengths();
        Assert.assertEquals("Not found", statusPanel.getBestRouteLength().getText());
        Assert.assertEquals("Not found", statusPanel.getGlobalBestRouteLength().getText());

        acoAlgorithm.setGoal(0, 1);
        acoAlgorithm.setHome(0, 0);
        acoAlgorithm.setCreateResults(false);
        acoAlgorithm.startOptimisation();
        statusPanel.updateBestRouteLengths();
        Assert.assertEquals("2", statusPanel.getBestRouteLength().getText());
        Assert.assertEquals("2", statusPanel.getGlobalBestRouteLength().getText());
    }
}

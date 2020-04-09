package ie.dcu.computing.gitlab.java;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestPerformanceLogger {

    PerformanceLogger performanceLogger = new PerformanceLogger("testresults.txt");

    public TestPerformanceLogger() throws IOException {
    }

    @Test
    void setFirstBestLengthTest() {
        int iterNum = 1;
        performanceLogger.setFirstBestLength(iterNum);
        Assert.assertEquals(performanceLogger.firstBestLength, iterNum);
    }

    @Test
    void setGlobalBestTest() {
        performanceLogger.setGlobalBestLength(13, 1);
        Assert.assertEquals(performanceLogger.globalBestLength, 13);

        performanceLogger.setGlobalBestLength(14, 2);
        Assert.assertEquals(performanceLogger.globalBestLength, 13);
        Assert.assertEquals(performanceLogger.firstBestLength, 0);

        performanceLogger.setGlobalBestLength(11, 3);
        Assert.assertEquals(performanceLogger.globalBestLength, 11);
        Assert.assertEquals(performanceLogger.firstBestLength, 3);
    }

}

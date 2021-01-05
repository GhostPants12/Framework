package util;

import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TestListener extends RunListener {
    private Logger log = LogManager.getRootLogger();

    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
        saveScreenshot();
    }

    private void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenCapture,
                    new File(String.format(".//target/screenshots/%s%s",getCurrentTime(), ".png")));
        } catch (IOException ex){
            log.error("Failed to save screenshot: " + ex.getMessage());
        }
    }

    private String getCurrentTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return Instant.now().atZone(ZoneId.systemDefault()).format(formatter);
    }
}

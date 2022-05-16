package core;

import lombok.extern.slf4j.Slf4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class PrestaTestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult test) {
        log.info("Test {} starting...", test.getName());
    }

    @Override
    public void onTestFailure(ITestResult test) {
        ScreenShotMaker.takeAScreenShot(test.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult test) {
        ScreenShotMaker.takeAScreenShot(test.getName());
    }
}

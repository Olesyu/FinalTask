package pages;

import org.openqa.selenium.By;

public class MainPage extends BasePage {
    private final By applicationIframe = By.id("framelive");

    public ApplicationPage locateAndSwitchToAppIframe() {
        waitForIframeAndSwitchToIt(applicationIframe, 10);
        return new ApplicationPage();
    }

}

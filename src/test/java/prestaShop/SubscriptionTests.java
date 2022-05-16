package prestaShop;

import org.testng.annotations.Test;
import pages.ApplicationPage;
import pages.MainPage;

public class SubscriptionTests extends BaseTest{

    @Test
    public void subscribeWithInvalidEmailTest() {
        String textNearSubscription = "Get our latest news and special sales";
        String textUnderSubscription = "You may unsubscribe at any moment. For that purpose, please find my contact info in the legal notice.";
        MainPage mainPage = new MainPage();
        String nearEmailSubscribeText = mainPage
                .locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .getFooterBlock()
                .getNearEmailSubscribeText();

        getSoftAssertion().assertThat(nearEmailSubscribeText)
                .as("Text near subscription email field")
                .isEqualTo(textNearSubscription);

        String underEmailSubscribeText = new ApplicationPage()
                .getFooterBlock()
                .getUnderEmailSubscribeText();

        getSoftAssertion().assertThat(underEmailSubscribeText)
                .as("Text under subscription email field")
                .isEqualTo(textUnderSubscription);

        boolean buttonTextInUpperCase = new ApplicationPage()
                .getFooterBlock()
                .isButtonTextInUpperCase();

        getSoftAssertion().assertThat(buttonTextInUpperCase)
                .as("Button 'SUBSCRIBE' in upper case ")
                .isTrue();
        getSoftAssertion().assertAll();
    }
}

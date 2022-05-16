package prestaShop;

import org.testng.annotations.Test;
import pages.ApplicationPage;
import pages.MainPage;

public class LanguagePanelTests extends BaseTest{

    @Test
    public void checkLanguagesTest() {
        int expectedNumberOfLanguages = 44;
        String languageISOCodeToBePresentInDropDown = "uk";
        MainPage mainPage = new MainPage();
        int numberOfLanguages = mainPage
                .locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .clickOnLanguageDropDownButton()
                .getNumberOfLanguages();


        getSoftAssertion().assertThat(numberOfLanguages)
                .as("Number of languages in dropdown menu")
                .isEqualTo(expectedNumberOfLanguages);

        boolean isLanguagePresent = new ApplicationPage()
                .checkIfLanguageIsPresent(languageISOCodeToBePresentInDropDown);

        getSoftAssertion().assertThat(isLanguagePresent)
                .as("Ukrainian language is present in dropdown")
                .isTrue();
        getSoftAssertion().assertAll();
    }
}

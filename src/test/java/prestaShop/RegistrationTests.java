package prestaShop;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import pages.CreateAccountPage;
import pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;


public class RegistrationTests extends BaseTest{

    @Test
    public void registrationWithValidDataTest() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress(firstName + "." + lastName);
        String password = faker.internet().password(5, 8);

        MainPage mainPage = new MainPage();
        String nameOfLoggedInUser = mainPage
                .locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .clickOnSignInButton()
                .clickOnCreateNewAccount()
                .getPersonalInformationBlock()
                .fillFormWithValidData(firstName, lastName, email, password)
                .clickOnDataPrivacyCheckBox()
                .returnOnParentPage()
                .clickOnSaveButton()
                .getNameOfLoggedInUser();

        assertThat(nameOfLoggedInUser)
                .as("Logged in name corresponds to recently created")
                .isEqualTo(firstName + " " + lastName);
    }

    //--------------------------------------------------------------------------------
    // This test for demonstration purposes only!!!! Red tomato is not actually red :)
    @Test
    public void registrationWithInvalidDataAdditionalTest() {
        Faker faker = new Faker();
        String firstName = "James8";
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress(firstName + "." + lastName);
        String password = faker.internet().password(5, 8);

        MainPage mainPage = new MainPage();
        String outlineColorOfFirstnameField = mainPage
                .locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .clickOnSignInButton()
                .clickOnCreateNewAccount()
                .getPersonalInformationBlock()
                .fillFormWithValidData(firstName, lastName, email, password)
                .clickOnDataPrivacyCheckBox()
                .clickOnSaveButtonWithInvalidData()
                .getOutlineColorOfFirstnameField();
        boolean isPopUpOpened = new CreateAccountPage().getPersonalInformationBlock().checkIfPopupWithInvalidNameAppears();

        getSoftAssertion().assertThat(outlineColorOfFirstnameField)
                .as("Color of first name field")
                .isEqualTo("Red");
        getSoftAssertion().assertThat(isPopUpOpened)
                .as("popUp 'Invalid name' opened")
                .isTrue();
        getSoftAssertion().assertAll();
    }
    //--------------------------------------------------------------------------------

    @Test
    public void registrationWithInvalidDataTest() {
        Faker faker = new Faker();
        String firstName = "James8";
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress(firstName + "." + lastName);
        String password = faker.internet().password(5, 8);

        MainPage mainPage = new MainPage();
        String outlineColorOfFirstnameField = mainPage
                .locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .clickOnSignInButton()
                .clickOnCreateNewAccount()
                .getPersonalInformationBlock()
                .fillFormWithValidData(firstName, lastName, email, password)
                .getOutlineColorOfFirstnameField();
        boolean isPopUpOpened = new CreateAccountPage().getPersonalInformationBlock().checkIfPopupWithInvalidNameAppears();

        getSoftAssertion().assertThat(outlineColorOfFirstnameField)
                .as("Color of first name field")
                .isEqualTo("Red");
        getSoftAssertion().assertThat(isPopUpOpened)
                .as("popUp 'Invalid name' opened")
                .isTrue();
        getSoftAssertion().assertAll();
    }
}

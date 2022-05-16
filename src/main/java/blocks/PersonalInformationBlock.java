package blocks;

import helper.ColorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import pages.BasePage;

public class PersonalInformationBlock<T> extends BasePage {

    private final By fieldFirstname = By.id("field-firstname");
    private final By fieldLastname = By.id("field-lastname");
    private final By fieldEmail = By.id("field-email");
    private final By fieldPassword = By.id("field-password");
    private final By checkBoxDataPrivacy = By.xpath("//input[@name='customer_privacy']");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By firstNameHelpBlock = By.xpath("//input[@id='field-firstname']/ancestor::div[1]/div[@class='help-block']");
    private final By invalidNamePopUp = By.xpath("//input[@id='field-firstname']/ancestor::div[1]/div[@class='help-block']/ul/li[contains(text(), 'Invalid format.')]");

    private final T parentPage;

    public PersonalInformationBlock (T parent) {
        this.parentPage = parent;
    }

    public PersonalInformationBlock<T> fillFormWithValidData(String firstName, String lastName, String email, String password) {
        waitUntilVisible(fieldFirstname, 10).sendKeys(firstName);
        findElement(fieldLastname).sendKeys(lastName);
        findElement(fieldEmail).sendKeys(email);
        findElement(fieldPassword).sendKeys(password);
        return this;
    }

    public PersonalInformationBlock<T> clickOnDataPrivacyCheckBox() {
        findScrollAndClick(checkBoxDataPrivacy);
        return this;
    }

    public void clickOnSaveButton() {
        findScrollAndClick(saveButton);
    }

    public PersonalInformationBlock<T> clickOnSaveButtonWithInvalidData() {
        findScrollAndClick(saveButton);
        return this;
    }

    public String getOutlineColorOfFirstnameField() {
        try {
            waitUntilVisible(firstNameHelpBlock, 3);
            return new ColorUtils().getColorNameFromRGBCSSString(getComputedStylePropertyOutlineColor(findElement(fieldFirstname)));
        } catch (TimeoutException e){
            return new ColorUtils().getColorNameFromRGBCSSString(getComputedStylePropertyOutlineColor(findElement(fieldFirstname)));
        }
    }

    public boolean checkIfPopupWithInvalidNameAppears() {
        try {
            waitUntilVisible(firstNameHelpBlock, 3);
            findElements(invalidNamePopUp);
            return true;
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public T returnOnParentPage() {
        return parentPage;
    }
}

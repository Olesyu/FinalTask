package blocks;

import org.openqa.selenium.By;
import pages.BasePage;

public class AddressesBlock<T> extends BasePage {

    private final By fieldAddress1 = By.id("field-address1");
    private final By fieldPostcode = By.id("field-postcode");
    private final By fieldCity = By.id("field-city");
    private final By saveButton = By.name("confirm-addresses");

    private final T parentPage;

    public AddressesBlock(T parent) {
        this.parentPage = parent;
    }

    public AddressesBlock<T> fillFormWithValidData(String address, String postalCode, String city) {
        waitUntilVisible(fieldAddress1, secondsToWaitElement).sendKeys(address);
        findElement(fieldPostcode).sendKeys(postalCode);
        findElement(fieldCity).sendKeys(city);
        return this;
    }

    public void clickOnSaveButton() {
        findScrollAndClick(saveButton);
    }

    public AddressesBlock<T> clickOnSaveButtonWithInvalidData() {
        findScrollAndClick(saveButton);
        return this;
    }

    public T returnOnParentPage() {
        return parentPage;
    }
}

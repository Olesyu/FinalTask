package pages;

import blocks.MenuBlock;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

public class ProductPage extends BasePage{

    private final By contentWrapper = By.id("main");
    private final By paperTypeLocator = By.id("group_4");
    private final By quantityLocator = By.id("quantity_wanted");
    private final By buttonAnnToCardLocator = By.xpath("//button[@data-button-action='add-to-cart']");
    private final By productCustomizationInputLocator = By.id("field-textField1");
    private final By productSaveCustomizationButtonLocator = By.xpath("//button[@name='submitCustomizedData']");
    private final By yourCustomizationMessageLocator = By.xpath("//*[contains(@class, 'customization-message')]");
    private final String colorSelectorInputLocator = "//input[@class='input-color' and @title='%s']";

    private final MenuBlock menuBlock = new MenuBlock(getDriver());

    public MenuBlock getMenuBlock() {
        return menuBlock;
    }

    public ProductPage waitUntilPageLoaded(int seconds) {
        waitUntilVisible(contentWrapper, seconds);
        return this;
    }

    public ProductPage selectPaperTypeByText(String paperType) {
        WebElement paperSelector = waitUntilVisible(paperTypeLocator, 5);
        Select selectObj = new Select(paperSelector);
        selectObj.selectByVisibleText(paperType);
        return this;
    }

    public ProductPage selectQuantity(int quantity) {
        String quantityString = Integer.toString(quantity);
        WebElement quantityInput = findElement(quantityLocator);
        if (!Objects.equals(quantityInput.getAttribute("value"), quantityString)) {
            quantityInput.clear();
            quantityInput.sendKeys(Keys.CONTROL + "A");
            quantityInput.sendKeys(Keys.BACK_SPACE);
            quantityInput.sendKeys(quantityString);
        }
        return this;
    }

    public ModalPopUpPurchasePage clickAddToCard() {
        findScrollAndClick(buttonAnnToCardLocator);
        return new ModalPopUpPurchasePage();
    }

    public ProductPage enterCustomizationText(String customizationString) {
        WebElement customizationInput = waitUntilVisible(productCustomizationInputLocator, secondsToWaitElement);
        customizationInput.clear();
        customizationInput.sendKeys(customizationString);
        return this;
    }

    public ProductPage clickSaveCustomization() {
        findScrollAndClick(productSaveCustomizationButtonLocator);
        waitUntilVisible(yourCustomizationMessageLocator, secondsToWaitElement);
        return this;
    }

    public ProductPage selectProductColor(String productColor) {
        String capitalizedColor = StringUtils.capitalize(StringUtils.lowerCase(productColor));
        By locator = By.xpath(String.format(colorSelectorInputLocator, capitalizedColor));
        findScrollAndClick(locator);
        return this;
    }
}

package blocks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.ProductsPage;

public class FooterBlock extends BasePage {


    private final By nearEmailSubscribeFieldText = By.id("block-newsletter-label");
    private final By underEmailSubscribeFieldText = By.xpath("//div[@id='blockEmailSubscription_displayFooterBefore']/div/div/form/div/div[2]/p");
    private final By subscribeButton = By.xpath("//input[@name='submitNewsletter' and @value='Subscribe']");
    private final By priceDropLocator = By.id("link-product-page-prices-drop-1");


    public String getNearEmailSubscribeText() {
        WebElement nearEmail = findElement(nearEmailSubscribeFieldText);
        scrollIntoView(nearEmail);
        return nearEmail.getText();
    }

    public String getUnderEmailSubscribeText() {
        WebElement underEmail = findElement(underEmailSubscribeFieldText);
        scrollIntoView(underEmail);
        return underEmail.getText();
    }

    public boolean isButtonTextInUpperCase() {
        WebElement subscribeBtn = findElement(subscribeButton);
        scrollIntoView(subscribeBtn);
        return getComputedStylePropertyTextTransform(subscribeBtn).equals("uppercase");
    }

    public ProductsPage clickOnPriceDropButton() {
        findScrollAndClick(priceDropLocator);
        return new ProductsPage();
    }

}

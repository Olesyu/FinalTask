package pages;

import blocks.CheckOutSummaryBlock;
import helper.CustomUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

public class PaymentCheckOutPage extends BasePage {
    private final By payByCheckLocator = By.id("payment-option-1");
    private final By termsOfServiceCheckBoxLocator = By.id("conditions_to_approve[terms-and-conditions]");
    private final By paymentAmountLocator = By.xpath("//div[@id='payment-option-1-additional-information']/section/dl/dd[1]");
    private final By continueButton = By.xpath("//div[@id='payment-confirmation']/div/button");

    private final CheckOutSummaryBlock checkOutSummaryBlock = new CheckOutSummaryBlock(getDriver());

    public CheckOutSummaryBlock getCheckoutSummeryBlock() {
        return checkOutSummaryBlock;
    }

    public PaymentCheckOutPage clickOnPayByCheck() {
        findScrollAndClick(payByCheckLocator);
        return this;
    }

    public double getSumOfSubtotalAndShipping() {
        return checkOutSummaryBlock.getSumOfSubtotalAndShipping();
    }

    public PaymentCheckOutPage clickOnCheckBoxTermsOfService() {
        findScrollAndClick(termsOfServiceCheckBoxLocator);
        return this;
    }

    public double getPaymentAmount() {
        String amountValue = waitUntilVisible(paymentAmountLocator, secondsToWaitElement).getText();
        String formattedString = StringUtils.substringBefore(amountValue, "(");
        return CustomUtils.getPriceValueFromString(formattedString);
    }

    public OrderConfirmationPage pressPlaceOrderButton() {
        findScrollAndClick(continueButton);
        return new OrderConfirmationPage();
    }
}

package prestaShop;

import com.github.javafaker.Faker;
import elements.OrderItemElement;
import helper.CustomUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.OrderConfirmationPage;
import pages.PaymentCheckOutPage;
import pages.ShoppingCardPage;

import java.util.List;

public class EndToEndCheckoutTests extends BaseTest{

    @Test
    public void checkoutEndToEndTest() {
        String textToSearch = "Mug";
        String productToChoose = "Customizable Mug";
        String productCustomization = "Best mug ever";
        String textToSearch2 = "T-Shir";
        String productToChoose2 = "Hummingbird Printed T-Shirt";
        String colorToSelect = "Black";
        int quantity = 1;
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress(firstName + "." + lastName);
        String password = "";
        String address = faker.address().fullAddress();
        String postalCode = StringUtils.left(faker.address().zipCode(), 5);
        String city = faker.address().city();

        String orderConfirmationString = "YOUR ORDER IS CONFIRMED";

        MainPage mainPage = new MainPage();
        double sumOfCardsTotal = mainPage.locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .getMenuBlock().makeASearch(textToSearch)
                .clickOnCertainProductName(productToChoose)
                .enterCustomizationText(productCustomization)
                .clickSaveCustomization()
                .selectQuantity(quantity)
                .clickAddToCard()
                .clickOContinueShoppingButton()
                .getMenuBlock().makeASearch(textToSearch2)
                .clickOnCertainProductName(productToChoose2)
                .selectProductColor(colorToSelect)
                .clickAddToCard()
                .clickOnProceedToCheckOutButton()
                .getSumOfCardsTotal();

        double pageTotalValue = new ShoppingCardPage().getPageTotalValue();

        getSoftAssertion().assertThat(CustomUtils.trimDouble(sumOfCardsTotal))
                .as("Total is correct")
                .isEqualTo(CustomUtils.trimDouble(pageTotalValue));

        double sumOfSubtotalAndShipping = new ShoppingCardPage().clickOnProceedToCheckoutButton()
                .getPersonalInformationBlock()
                .fillFormWithValidData(firstName, lastName, email, password)
                .clickOnDataPrivacyCheckBox()
                .returnOnParentPage()
                .clickOnPersonalInformationIContinueButton()
                .getAddressesBlock()
                .fillFormWithValidData(address, postalCode, city)
                .returnOnParentPage()
                .clickOnAddressesContinueButton()
                .selectMyCarrierDeliveryMethod()
                .pressShippingContinueButton()
                .clickOnPayByCheck()
                .getSumOfSubtotalAndShipping();

        double paymentAmount = new PaymentCheckOutPage().getPaymentAmount();

        getSoftAssertion().assertThat(sumOfSubtotalAndShipping)
                .as("Sum of subtotal and shipping equals payment amount")
                .isEqualTo(paymentAmount);

        String orderConfirmedText = new PaymentCheckOutPage().clickOnCheckBoxTermsOfService().pressPlaceOrderButton().getOrderConfirmedText();

        getSoftAssertion().assertThat(orderConfirmedText)
                .as("Order confirmation text")
                .contains(orderConfirmationString);

        List<OrderItemElement> allOrderedProductCards = new OrderConfirmationPage().getAllOrderedProductCards();

        double subTotalAmount = new OrderConfirmationPage().getSubTotalAmount();
        double shippingAmount = new OrderConfirmationPage().getShippingAmount();
        double totalAmount = new OrderConfirmationPage().getTotalAmount();
        double calculatedTotalAmount = new OrderConfirmationPage().getCardsTotalSum();

        for (OrderItemElement card : allOrderedProductCards) {
            getSoftAssertion().assertThat(card.getCalculatedTotalAmount())
                    .as("Product: '" + card.getProductTitle() + "' total calculates correctly")
                    .isEqualTo(card.getTotalProductsAmount());
        }

        getSoftAssertion().assertThat(calculatedTotalAmount).as("Subtotal calculates correct").isEqualTo(subTotalAmount);

        getSoftAssertion().assertThat(totalAmount).as("Total in summary calculates correctly").isEqualTo(CustomUtils.trimDouble(shippingAmount + subTotalAmount));

        getSoftAssertion().assertAll();

    }
}

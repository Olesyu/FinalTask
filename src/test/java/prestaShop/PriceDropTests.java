package prestaShop;

import elements.ProductCardElement;
import org.testng.annotations.Test;
import pages.MainPage;

import java.util.List;

public class PriceDropTests extends BaseTest{

    @Test
    public void priceDropCheckTest() {
        MainPage mainPage = new MainPage();
        List<ProductCardElement> allPriceDropProductCards = mainPage.locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .getFooterBlock()
                .clickOnPriceDropButton()
                .waitUntilOverlayHidden()
                .getAllProductCards();

        getSoftAssertion().assertThat(allPriceDropProductCards)
                .as("Every product has old and new price")
                .allMatch(card -> card.getRegularPriceString() != null && card.getPriceString() != null);
        getSoftAssertion().assertThat(allPriceDropProductCards)
                .as("Promo price calculates correctly")
                .allMatch(ProductCardElement::checkIfDiscountedPriceCalculatesCorrectly);
        getSoftAssertion().assertAll();
    }
}

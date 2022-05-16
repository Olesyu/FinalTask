package prestaShop;

import elements.ProductCardElement;
import org.testng.annotations.Test;
import pages.MainPage;

import java.util.List;

public class PopularProductsTests extends BaseTest {
    @Test
    public void checkPopularProductsTests() {
        int expectedNumberOfCards = 8;
        double priceBiggerThan = 0.00;
        MainPage mainPage = new MainPage();
        List<ProductCardElement> allPopularProductCards = mainPage.locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .getAllPopularProductCards();

        getSoftAssertion().assertThat(allPopularProductCards)
                .as("Number of popular products")
                .hasSize(expectedNumberOfCards);

        getSoftAssertion().assertThat(allPopularProductCards)
                .as("Every popular product has name")
                .allMatch(card -> card.getProductTitle() != null);

        getSoftAssertion().assertThat(allPopularProductCards)
                .as("Every popular product has price")
                .allMatch(card -> card.getPriceString() != null);

        getSoftAssertion().assertThat(allPopularProductCards)
                .as("All cards prices bigger than: " + priceBiggerThan)
                .allMatch(card -> card.getPrice() > priceBiggerThan);

        getSoftAssertion().assertAll();
    }
}

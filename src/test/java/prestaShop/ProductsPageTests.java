package prestaShop;

import elements.ProductCardElement;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ProductsPage;

import java.util.Comparator;
import java.util.List;

public class ProductsPageTests extends BaseTest {

    @Test
    public void sortingCheckTest() {
        MainPage mainPage = new MainPage();
        List<ProductCardElement> allProductCardsNameAsc = mainPage.locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .clickOnAllProductsLink()
                .waitUntilOverlayHidden()
                .sortByNameAscend()
                .waitUntilOverlayHidden()
                .getAllProductCards();

        getSoftAssertion().assertThat(allProductCardsNameAsc)
                .as("Products sorted by name Ascend")
                .map(ProductCardElement::getProductTitle)
                .isSortedAccordingTo(Comparator.naturalOrder());


        List<ProductCardElement> allProductCardsNameDesc = new ProductsPage()
                .sortByNameDescend()
                .waitUntilOverlayHidden()
                .getAllProductCards();

        getSoftAssertion().assertThat(allProductCardsNameDesc)
                .as("Products sorted by name Descend")
                .map(ProductCardElement::getProductTitle)
                .isSortedAccordingTo(Comparator.reverseOrder());

        List<ProductCardElement> allProductCardsPriceAsc = new ProductsPage()
                .sortByPriceAscend()
                .waitUntilOverlayHidden()
                .getAllProductCards();

        getSoftAssertion().assertThat(allProductCardsPriceAsc)
                .as("Products sorted by price Ascend")
                .map(ProductCardElement::getPrice)
                .isSortedAccordingTo(Comparator.naturalOrder());

        List<ProductCardElement> allProductCardsPriceDesc = new ProductsPage()
                .sortByPriceDescend()
                .waitUntilOverlayHidden()
                .getAllProductCards();

        getSoftAssertion().assertThat(allProductCardsPriceDesc)
                .as("Products sorted by price Descend")
                .map(ProductCardElement::getPrice)
                .isSortedAccordingTo(Comparator.reverseOrder());

        getSoftAssertion().assertAll();
    }
}

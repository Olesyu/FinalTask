package prestaShop;

import org.testng.annotations.Test;
import pages.MainPage;
import pages.ModalPopUpPurchasePage;

public class ShoppingCartTests extends BaseTest{

    @Test
    public void addingToCardTest() {
        String textToSearch = "Bear";
        String productToChoose = "Brown Bear Notebook";
        String paperType = "Doted";
        String popUpTitleString = "Product successfully added to your shopping cart";
        int quantity = 5;
        MainPage mainPage = new MainPage();
        String popUpTitle = mainPage.locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .searchForProduct(textToSearch)
                .clickOnCertainProductName(productToChoose)
                .waitUntilPageLoaded(10)
                .selectPaperTypeByText(paperType)
                .selectQuantity(quantity)
                .clickAddToCard()
                .waitUntilPageLoaded(10)
                .getPopUpTitle();

        getSoftAssertion().assertThat(popUpTitle).as("Modal popup title").contains(popUpTitleString);

        String actualPaperType = new ModalPopUpPurchasePage().getPaperType();
        getSoftAssertion().assertThat(actualPaperType).as("Chosen paper type").isEqualTo(paperType);

        int actualQuantity = new ModalPopUpPurchasePage().getQuantity();
        getSoftAssertion().assertThat(actualQuantity).as("Chosen quantity").isEqualTo(quantity);

        boolean isTotalCorrect = new ModalPopUpPurchasePage().checkIfTotalCorrect();
        getSoftAssertion().assertThat(isTotalCorrect).as("Total calculates correct").isTrue();

        getSoftAssertion().assertAll();
    }
}

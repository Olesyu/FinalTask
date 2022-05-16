package prestaShop;

import org.testng.annotations.Test;
import pages.ApplicationPage;
import pages.MainPage;

public class MenuTests extends BaseTest{

    @Test
    public void checkCategoriesTest() {
        MainPage mainPage = new MainPage();
        boolean isManCategoryDisplayed = mainPage.locateAndSwitchToAppIframe()
                .waitUntilApplicationLoaded(secondsToWaitLoading)
                .getMenuBlock()
                .hoverClothesMenu()
                .checkMenSubItemAppears();

        getSoftAssertion().assertThat(isManCategoryDisplayed).as("MAN subItem displayed").isTrue();

        boolean isWomanCategoryDisplayed = new ApplicationPage()
                .getMenuBlock()
                .checkWomenSubItemAppears();

        getSoftAssertion().assertThat(isWomanCategoryDisplayed).as("WOMAN subItem displayed").isTrue();

        boolean isStationeryCategoryDisplayed = new ApplicationPage()
                .getMenuBlock()
                .hoverAccessoriesMenu()
                .checkStationerySubItemAppears();

        getSoftAssertion().assertThat(isStationeryCategoryDisplayed).as("STATIONERY subItem displayed").isTrue();

        boolean isHomeAccessoriesCategoryDisplayed = new ApplicationPage()
                .getMenuBlock()
                .checkHomeAccessoriesSubItemAppears();

        getSoftAssertion().assertThat(isHomeAccessoriesCategoryDisplayed).as("HOME ACCESSORIES subItem displayed").isTrue();

        boolean isNoArtSubCategories = new ApplicationPage()
                .getMenuBlock()
                .hoverArtMenu()
                .checkIfNoArtSubCategories();

        getSoftAssertion().assertThat(isNoArtSubCategories).as("ART has no subItems").isTrue();

        getSoftAssertion().assertAll();
    }
}

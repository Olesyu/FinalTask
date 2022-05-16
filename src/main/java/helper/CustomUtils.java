package helper;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;

import java.sql.Array;
import java.util.*;

public class CustomUtils {

    public static double getDiscountPercentageFromPrices(double regularPrice, double discountedPrice) {
        return (regularPrice - discountedPrice) / regularPrice * 100.0;
    }

    public static double getDiscountedPriceOnPercentage(double regularPrice, double discountPercent) {
        return regularPrice - (regularPrice / 100.0 * discountPercent);
    }

    public static double trimDouble(double input) {
        return Math.round(input * 100.0) / 100.0;
    }

    public static boolean checkDoubleEquals(double a, double b) {
        return Double.compare(trimDouble(a), trimDouble(b)) == 0;
    }

    /**
     * @param stringPrice String that contains price
     * @return Numeric price
     */
    public static double getPriceValueFromString(String stringPrice) {
        try {
            return Double.parseDouble(stringPrice.replaceAll("[^0-9.,]", ""));
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    /**
     * @param parentElement Parent element to look for
     * @param locator       Locator of nested element
     * @return Element`s text
     */
    public static String tryToGetElementText(WebElement parentElement, By locator) {
        try {
            return parentElement.findElement(locator).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public static void scrollInToView(WebElement element, WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static class Resolution {
        public String name;
        public int width;
        public int height;

        public Resolution(String input) {
            this.name = input;
            this.getWidthAndHeight();
        }

        private void getWidthAndHeight() {
            String[] resolutionString = this.name.replaceAll("[^0-9x]", "").split("x");
            if (resolutionString.length > 1) {
                this.width = Integer.parseInt(resolutionString[0]);
                this.height = Integer.parseInt(resolutionString[1]);
            } else {
                this.width = 0;
                this.height = 0;
            }
        }
    }

    public static Dimension getDimensionsFromString(String input) {
        String[] resolutionString = input.replaceAll("[^0-9x]", "").split("x");
        return new Dimension(Integer.parseInt(resolutionString[0]), Integer.parseInt(resolutionString[1]));
    }

}

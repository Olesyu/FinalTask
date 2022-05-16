package pages;

import org.openqa.selenium.By;

public class LogInPage extends BasePage{
    private final By createAccountButton = By.xpath("//div[@id='content']/div/a");

    public CreateAccountPage clickOnCreateNewAccount() {
        findScrollAndClick(createAccountButton);
        return new CreateAccountPage();
    }
}

package pages;

import blocks.PersonalInformationBlock;

public class CreateAccountPage extends BasePage {

   private final PersonalInformationBlock<CreateAccountPage> personalInformationBlock = new PersonalInformationBlock<>(this);

   public PersonalInformationBlock<CreateAccountPage> getPersonalInformationBlock() {
       return personalInformationBlock;
   }

    public ApplicationPage clickOnSaveButton() {
        personalInformationBlock.clickOnSaveButton();
        return new ApplicationPage();
    }

}

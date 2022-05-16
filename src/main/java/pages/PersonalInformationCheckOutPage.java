package pages;

import blocks.PersonalInformationBlock;

public class PersonalInformationCheckOutPage extends BasePage{

    private final PersonalInformationBlock<PersonalInformationCheckOutPage> personalInformationBlock = new PersonalInformationBlock<>(this);

    public PersonalInformationBlock<PersonalInformationCheckOutPage> getPersonalInformationBlock() {
        return personalInformationBlock;
    }

    public AddressesCheckOutPage clickOnPersonalInformationIContinueButton() {
        personalInformationBlock.clickOnSaveButton();
        return new AddressesCheckOutPage();
    }

}

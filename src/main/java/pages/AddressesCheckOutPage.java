package pages;

import blocks.AddressesBlock;

public class AddressesCheckOutPage extends BasePage{

    private final AddressesBlock<AddressesCheckOutPage> personalInformationBlock = new AddressesBlock<>(this);

    public AddressesBlock<AddressesCheckOutPage> getAddressesBlock() {
        return personalInformationBlock;
    }

    public ShippingCheckOutPage clickOnAddressesContinueButton() {
        personalInformationBlock.clickOnSaveButton();
        return new ShippingCheckOutPage();
    }
}

package pages;

import io.qameta.allure.Step;
import libs.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import parentPage.ParentPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;
import sun.plugin.dom.core.Text;

public class EMLPurchaseRegistrationPage extends ParentPage {
    public EMLPurchaseRegistrationPage(WebDriver webDriver) {
        super(webDriver, "/lotteries/instant-lotteries/registry");
    }

    @FindBy(xpath = "//input[@class='masked-input']")
    private TextInput phoneNumberInput;

    @FindBy(xpath = "//button[@class='button ci-button ci-button_sms ci-button_under button_theme_green ng-star-inserted']")
    private Button sendSMSButton;

    @FindBy(xpath = "//ng-component/app-check-information//app-msl-input-pin//input")
    //(xpath = "//input[@class='pin__input ng-pristine ng-valid ng-touched']")
    private TextInput confirmationSmsCodeInput;

    @FindBy(xpath = "//div[@class='pin-title ng-star-inserted']")
    private TextBlock confirmationCodeFromSmsHeader;

    @FindBy(xpath = "//button[@class='button button_theme_green ci-button ci-button_reg ng-star-inserted']")
    private Button registrationButton;

    @FindBy(xpath = "//div[@class='ci-data-value ci-data-value_other ci-text-red ng-star-inserted']")
    private TextBlock checkSum;

    @Step
    public void enterPhoneNumberForPurchase(String number){
        actionWithWebElements.enterTextIntoInput(phoneNumberInput, number);
    }

    @Step
    public void clickSendSMSButton(){
        actionWithWebElements.clickOnElement(sendSMSButton);
    }

    @Step
    public void waitUntilSmsCodeWillBeSent(){
        Utils.waitABit(3);
        actionWithWebElements.waitVisibilityOfElement(confirmationCodeFromSmsHeader);
        actionWithWebElements.waitVisibilityOfElement(confirmationSmsCodeInput);
    }

    @Step
    public void enterSmsIntoInput(String smsCode){
        actionWithWebElements.enterTextIntoInput(confirmationSmsCodeInput, smsCode);
    }

    @Step
    public void clickRegistrationButton(){
        actionWithWebElements.clickOnElement(registrationButton);
    }

    @Step
    public String autoLotocheckSum(int ticketCount){
        int sum = ticketCount * 100;
        String checkSum = (String.valueOf(sum)) + ".00 грн";
        return checkSum;
    }

    @Step
    public String getCheckSum(){
        String sum = actionWithWebElements.getTextFromElement(checkSum);
         return sum;
    }


}

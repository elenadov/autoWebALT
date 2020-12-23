package pages;

import io.qameta.allure.Step;
import libs.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import parentPage.ParentPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;
import java.sql.SQLException;


public class LoginFormPage extends ParentPage {

    @FindBy (xpath = "//*[@class='title']")
    private TextBlock title;

    @FindBy(xpath = "//div[@class='pin-title']")
    private TextBlock text;

    @FindBy(xpath = "//button[@class='button button_theme_green modal-button']")
    private Button continueButton;

    public LoginFormPage(WebDriver webDriver) {
        super(webDriver, "/auth");
    }

    @FindBy(xpath = "//*[@class='empty-value']")
    private TextInput loginInput;

    @FindBy(xpath = "//*[@class='secured empty-value']")
    private TextInput passwordInput;

    @FindBy(xpath = "//*[@class='client-phone ng-star-inserted'][1]")
    private Button phoneNumber;

    @FindBy(xpath = "//*[@class='pin__input ng-pristine ng-valid ng-touched']")
    private TextInput smsCodeInput;

    @FindBy(xpath = "//*[@class='pin__input ng-pristine ng-valid ng-touched']")
    private By smsCodeInput1;

    @FindBy(xpath = "//button[@class='button auth-button auth-button_type_confirm button_theme_dark-green ng-star-inserted']")
    private Button enter;

    @FindBy(xpath = "//*[@class='button auth-button auth-button_type_confirm button_theme_dark-green ng-star-inserted']")
    private Button confirmSmsCodeInput;

    @Step
    public void fillInLoginInput(String login){
        actionWithWebElements.enterTextIntoInput(loginInput, login);
    }

    @Step
    public void fillInPasswordInput(String pass){
        actionWithWebElements.enterTextIntoInput(passwordInput, pass);
    }

    @Step
    public void enterValidLogin(){
        fillInLoginInput(configProperties.OPERATOR_LOGIN_FOR_TEST());
    }

    @Step
    public void enterValidPassword(){
        fillInPasswordInput(configProperties.OPERATOR_PASSWORD_FOR_TEST());
    }

    @Step
    public void enterValidLoginPass(){
        enterValidLogin();
        enterValidPassword();
    }

    @Step
    public void clickOnPhoneNumber(){
        actionWithWebElements.clickOnElement(phoneNumber);
        actionWithWebElements.waitForText(text, "Код підтвердження з SMS:");
    }

    @Step
    public void signIn(){
        enterValidLoginPass();
        clickVhidToSignIn();
        clickOnPhoneNumber();
        waitUntilSmsCodeIsReceived();
    }

    @Step
    public boolean isSmsCodeInputFieldDisplayed() {
        return actionWithWebElements.isElementDisplayed(smsCodeInput);
    }

    @Step
    public void clickVhidToSignIn(){
        actionWithWebElements.clickOnElement(enter);
    }

    @Step
    public void waitUntilSmsCodeIsReceived(){
        Utils.waitABit(3);
        actionWithWebElements.clickOnElement(title);
    }

    @Step
    public void enterSmsCodeIntoField(String code) throws SQLException {

        actionWithWebElements.enterTextIntoInput1(smsCodeInput, code);
    }

    @Step
    public void clickSmsCodeInputConfirmation(){
        actionWithWebElements.clickOnElement(confirmSmsCodeInput);
    }

    @Step
    public void clickContinueButton() {
        actionWithWebElements.clickOnElement(continueButton);
    }
}

package pages;

import io.qameta.allure.Step;
import libs.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import parentPage.ParentPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class Lotteries extends ParentPage {

    public Lotteries(WebDriver webDriver) {
        super(webDriver, "/lotteries");
    }

    @FindBy(xpath = "//div[@class='change-log-container change-log-container_only-latest']")
    private TextBlock newOSAnnouncment;

    @FindBy(xpath = "//button[@class='button button_theme_green modal-button modal-button_close-changelog ng-star-inserted']")
    private Button continueNewOSButton;

    @FindBy(xpath = "//div[@class='modal-dialog-background is-visible']")
    private TextBlock jackpot;

    @FindBy(xpath = "//app-lotteries//button[@class='button modal-button button_theme_green']")
    private Button continueJackpotButton;

    @FindBy(xpath = "/html/body/app-root/div[2]/section/app-lotteries/app-game-list/div")
    private TextBlock lotteriesList;



    @FindBy(xpath = "//app-one-button-error//div[@class='modal-dialog-container']")
    private TextBlock registrationIsSuccessfulPopUp;

    @FindBy(xpath = "//button[@class='button button_theme_green modal-button']")
    private Button continueWorkAfterRegistrationSuccess;

    public boolean isPageLoaded(){
        if (actionWithWebElements.isElementDisplayed(newOSAnnouncment)){
            actionWithWebElements.clickOnElement(continueNewOSButton);
            if(actionWithWebElements.isElementDisplayed(jackpot)){
                actionWithWebElements.clickOnElement(continueJackpotButton);
                actionWithWebElements.isElementDisplayed(lotteriesList);
                checkCurrentUrl();
            }
            else if(actionWithWebElements.isElementDisplayed(lotteriesList)){
                checkCurrentUrl();
            }
            return true;
        }
        else if(actionWithWebElements.isElementDisplayed(jackpot)){
            actionWithWebElements.clickOnElement(continueJackpotButton);
            actionWithWebElements.isElementDisplayed(lotteriesList);
            checkCurrentUrl();
            return true;
        }
        else if(actionWithWebElements.isElementDisplayed(lotteriesList)){
            checkCurrentUrl();
            return true;
        }
        return false;
    }



    @Step
    public void isRegistrationSuccesfulPopUpVisible(){
        actionWithWebElements.isElementDisplayed(registrationIsSuccessfulPopUp);
    }

    @Step
    public void clickContinueWorkAfterRegistrationSuccess(){
        actionWithWebElements.clickOnElement(continueWorkAfterRegistrationSuccess);
    }
//    @Step
//    public double autoLotoCheckSum(int ticketCount){
//        double autoLotoTicketSum = 100;
//        double sum = ticketCount * autoLotoTicketSum;
//        return sum;
//    }
//
//    @Step
//    public void isAutoLotoCheckSumIsCorrect(){
//        checkExpectedResult("Sum is not correct", );
//    }
}

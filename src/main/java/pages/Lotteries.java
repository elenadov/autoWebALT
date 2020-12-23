package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import parentPage.ParentPage;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import javax.xml.soap.Text;

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
    ///html/body/app-root/div[2]/section/app-lotteries/app-game-list/div
    ////div[@class='game-list-container ng-tns-c135-2']
    private TextBlock lotteriesList;

    @FindBy(xpath = "//app-one-button-error/div/div")
    ////app-one-button-error//div[@class='modal-dialog-container']
    ////app-one-button-error/div/div
    private TextBlock loginOrPasswordIsIncorrectPopUp;

    @FindBy(xpath = "//app-lotteries//li[3]")
    private Button emlPurchaseMenu;

    public boolean waitUntilPageIsLoaded(){
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
    public boolean isLoginOrPasswordIsInvalidPopUpVisible(){
        return actionWithWebElements.isElementDisplayed(loginOrPasswordIsIncorrectPopUp);
    }

    @Step
    public void chooseAutoLotoPurchaseMenu(){
        actionWithWebElements.clickOnElement(emlPurchaseMenu);
    }
}

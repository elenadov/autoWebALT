package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import parentPage.ParentPage;
import ru.yandex.qatools.htmlelements.element.Button;

public class EMLPurchaseMenuPage extends ParentPage {

    public EMLPurchaseMenuPage(WebDriver webDriver) {
        super(webDriver, "/lotteries/instant-lotteries/init/1");
    }

    @FindBy(xpath = "//app-lotteries//li[3]")
    private Button emlPurchaseMenu;

    @FindBy(xpath = "//div[@id='game_index_0']")
    private Button autoLotoPurchaseMenu;

    @FindBy(xpath = "//button[@class='buttons-group__item buttons-group__item_first buttons-group__item_theme_square-radial ng-star-inserted']")
    private Button oneTicket;

    @FindBy (xpath = "//button[@class='button']")
    private Button confirmPurchase;

    @Step
    public void chooseEMLPurchaseMenu(){
        actionWithWebElements.clickOnElement(emlPurchaseMenu);
    }

    @Step
    public void chooseAutoLotoPurchaseMenu(){
        actionWithWebElements.clickOnElement(autoLotoPurchaseMenu);
    }

    @Step
    public void selectOneTicketAutoLotoPurchase(){
        actionWithWebElements.clickOnElement(oneTicket);
    }

    @Step
    public void confirmPurchase(){
        actionWithWebElements.clickOnElement(confirmPurchase);
    }


}

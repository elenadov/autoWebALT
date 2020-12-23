package autoLoto;

import abstractParentTest.AbstractParentTest;
import io.qameta.allure.*;
import org.junit.Test;

import java.sql.SQLException;

@Epic("Login")
@Feature("Authorization")
public class AutoLotoTicketBuyTest extends AbstractParentTest {
    @Description("Authorization")
    @Story("Authorization")
    @Link("")
    @Link(name = "allure", type = "mylink")
    @Severity(SeverityLevel.CRITICAL)

    @Test()
    public void autoLoto1TicketPurchase() throws SQLException, ClassNotFoundException {
        int ticketCount = 1;

        loginForm.openPage();
        loginForm.signIn();
        loginForm.enterSmsCodeIntoField(database.selectValue(configProperties.GET_SMS_CODE_FOR_AUTH()));
        loginForm.clickSmsCodeInputConfirmation();

        checkExpectedResult("Page hasn't loaded yet",lotteries.isPageLoaded());

        oracleSQLDBConnect();
        emlPurchaseMenuPage.chooseEMLPurchaseMenu();
        emlPurchaseMenuPage.chooseAutoLotoPurchaseMenu();
        emlPurchaseMenuPage.selectOneTicketAutoLotoPurchase();
        emlPurchaseMenuPage.confirmPurchase();

        checkExpectedText("The sum of Auto Loto check is incorrect!",emlPurchaseRegistrationPage.autoLotocheckSum(ticketCount),
                emlPurchaseRegistrationPage.getCheckSum());

        emlPurchaseRegistrationPage.enterPhoneNumberForPurchase("988497471");
        emlPurchaseRegistrationPage.clickSendSMSButton();
        emlPurchaseRegistrationPage.waitUntilSmsCodeWillBeSent();
        emlPurchaseRegistrationPage.enterSmsIntoInput(database.selectValue((configProperties.GET_SMS_CODE_FOR_SELL())));
        emlPurchaseRegistrationPage.clickRegistrationButton();
        lotteries.isRegistrationSuccesfulPopUpVisible();
        lotteries.clickContinueWorkAfterRegistrationSuccess();

        checkExpectedResult("Page has not loaded after the registration", lotteries.isPageLoaded());

    }
}

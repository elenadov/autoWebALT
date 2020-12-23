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
    public void autoLoto1TicketPurchase() throws SQLException {
        loginForm.openPage();
        loginForm.signIn();
        loginForm.enterSmsCodeIntoField(database.selectValue(configProperties.GET_SMS_CODE_FOR_AUTH()));

        checkExpectedResult("Page hasn't loaded yet",lotteries.waitUntilPageIsLoaded());

        lotteries.chooseAutoLotoPurchaseMenu();
    }
}

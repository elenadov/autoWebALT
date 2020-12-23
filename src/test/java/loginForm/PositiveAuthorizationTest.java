package loginForm;

import abstractParentTest.AbstractParentTest;
import io.qameta.allure.*;
import org.junit.Test;

import java.sql.SQLException;

@Epic("Login")
@Feature("Authorization")
public class PositiveAuthorizationTest extends AbstractParentTest {

    @Description("Authorization")
    @Story("Authorization")
    @Link("")
    @Link(name = "allure", type = "mylink")
    @Severity(SeverityLevel.CRITICAL)

    @Test()
    public void authorization() throws SQLException {
        loginForm.openPage();
        loginForm.enterValidLoginPass();
        loginForm.clickVhidToSignIn();
        loginForm.clickOnPhoneNumber();
        loginForm.waitUntilSmsCodeIsReceived();
        loginForm.enterSmsCodeIntoField(database.selectValue(configProperties.GET_SMS_CODE_FOR_AUTH()));
        loginForm.clickSmsCodeInputConfirmation();

        checkExpectedResult("Page is not loaded", lotteries.waitUntilPageIsLoaded());
            }
}
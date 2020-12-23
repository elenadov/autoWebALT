package abstractParentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import libs.ConfigProperties;
import libs.MySQL_Database;
import libs.Oracle_SQL_Database;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.After;
import libs.Database;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.Parameterized;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.EMLPurchaseMenuPage;
import pages.EMLPurchaseRegistrationPage;
import pages.LoginFormPage;
import pages.Lotteries;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AbstractParentTest {
    WebDriver webDriver;
    protected LoginFormPage loginForm;
    protected Lotteries lotteries;
    protected EMLPurchaseMenuPage emlPurchaseMenuPage;
    protected EMLPurchaseRegistrationPage emlPurchaseRegistrationPage;

    protected static ConfigProperties configProperties =
            ConfigFactory.create(ConfigProperties.class);
    protected Logger logger = Logger.getLogger(getClass());
    protected Database database;

    @Before
    public void mySQLDBConnect() throws SQLException, ClassNotFoundException {
        database = MySQL_Database.getDataBase();
    }

    @Step
    public void oracleSQLDBConnect() throws SQLException, ClassNotFoundException {
        database = Oracle_SQL_Database.getOracleDataBase();
    }

    @Before
    public void SetUp() throws Exception {
        webDriver = driverInit();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        loginForm = new LoginFormPage(webDriver);
        lotteries = new Lotteries(webDriver);
        emlPurchaseMenuPage = new EMLPurchaseMenuPage(webDriver);
        emlPurchaseRegistrationPage = new EMLPurchaseRegistrationPage(webDriver);}

    @Parameterized.Parameters
    private WebDriver driverInit() throws Exception {
        String hub = "https://d.popelnukh:E2H9elZQaQ2xrWoIsvTCl6RSUMHpw5aSCnmnskeQNGzneyd1qZ@hub.lambdatest.com/wd/hub";
        String browser = System.getProperty("browser");
        if ((browser == null) || ("chrome".equalsIgnoreCase(browser))) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if ("ie".equalsIgnoreCase(browser)) {
            WebDriverManager.iedriver().arch32().setup();
            return new InternetExplorerDriver();
        } else if ("remote".equals(browser)){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build", "5.15");
            capabilities.setCapability("name", "beda");
            capabilities.setCapability("platform", "Windows 10");
            capabilities.setCapability("browserName", "Chrome");
            capabilities.setCapability("version","83.0");
            capabilities.setCapability("resolution","1280x1024");
            capabilities.setCapability("tunnel",true);
            return webDriver = new RemoteWebDriver(new URL(hub), capabilities);
        }else {
            throw new Exception("Check browser var ");
        }
    }

    @After
    public void tearDown() throws SQLException {
        database.quit();
    }

    @Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        @Attachment(value = "Page screenshot", type = "image/png")
        public byte[] saveScreenshot(byte[] screenShot) {
            return screenShot;
        }

        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            saveScreenshot(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES));
        }

        @Override
        protected void finished(Description description) {
            logger.info(String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
            } catch (Exception e) {
                logger.error(e);
            }
        }
    };

    @Step
    protected void checkExpectedResult(String message, boolean actualResult) {
        Assert.assertEquals(message, true, actualResult);
    }

    @Step
    protected void checkExpectedText(String message, String expectedResult, String actualResult) {
        Assert.assertEquals(message, expectedResult, actualResult);
    }

    @Step
    protected void checkExpectedText(String message, ArrayList<String> expectedResult, ArrayList<String> actualResult){
        Assert.assertEquals(message,expectedResult,actualResult);
    }

}

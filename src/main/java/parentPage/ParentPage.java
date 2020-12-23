package parentPage;

import io.qameta.allure.Step;
import libs.ActionWithWebElements;
import libs.ConfigProperties;
import libs.Database;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.pageElements.WebDriverAwareDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class ParentPage { protected WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
//    public WebDriverWait webDriverWait_10, webDriverWait_20, webDriverWait_30;
    protected ActionWithWebElements actionWithWebElements;
    public ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);
    protected String baseUrl;
    String expectedUrl;
//    protected Database database;

    public ParentPage(WebDriver webDriver, String partUrl){
        baseUrl = configProperties.base_url();
        this.webDriver = webDriver;
        PageFactory.initElements(
                new WebDriverAwareDecorator(
                        new HtmlElementLocatorFactory(webDriver), webDriver), this);
        actionWithWebElements = new ActionWithWebElements(webDriver);
        expectedUrl = baseUrl + partUrl;
//        this.webDriver = webDriver;
//        webDriverWait_10 = new WebDriverWait(webDriver, 10);
//        webDriverWait_20 = new WebDriverWait(webDriver, 20);
//        webDriverWait_30 = new WebDriverWait(webDriver, 30);
    }
    @Step
    public void openPage() {
        try {
            webDriver.get(expectedUrl);
        } catch (Exception e) {
            Assert.fail("Can't work with page");
        }
    }

    @Step
    public void checkCurrentUrl(){
        try{
            Assert.assertEquals("Url is not expected", expectedUrl, webDriver.getCurrentUrl());
        }catch (Exception e){
            logger.error("Can't get url " + e);
            Assert.fail("Can't get url " + e);
        }
    }
}

package pages.pageElements;

import libs.ActionWithWebElements;
import libs.ConfigProperties;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class CommonActionWithElements extends HtmlElement implements WebDriverAware {
    protected WebDriver webDriver;
    protected ActionWithWebElements actionWithWebElements;
    protected ConfigProperties configProperties;

    @Override
    public void setWebDriver(WebDriver driver) {
        this.webDriver = driver;
        actionWithWebElements = new ActionWithWebElements(webDriver);
    }
}
package libs;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

public class ActionWithWebElements {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait_10, webDriverWait_20, webDriverWait_30;
    Actions actions;
    int triesCount = 2;

    public ActionWithWebElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait_10 = new WebDriverWait(webDriver, 10);
        webDriverWait_20 = new WebDriverWait(webDriver, 20);
        webDriverWait_30 = new WebDriverWait(webDriver, 30);
        actions = new Actions(webDriver);
    }

    public void enterTextIntoInput(WebElement webElement, String text) {
        try {
//            webDriverWait_10.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "'" + " was inputed into input");
        } catch (Exception e) {
            stopTestAndPrintMessage();
        }
    }

    public void enterTextIntoInput1(WebElement webElement, String text) {
        try {
//            webDriverWait_10.until(ExpectedConditions.visibilityOf(webElement));
//            webElement.clear();
            webElement.sendKeys(text);
            logger.info("'" + text + "'" + " was inputed into input");
        } catch (Exception e) {
            stopTestAndPrintMessage();
        }
    }

    private String getElementName(WebElement webElement) {
        String elementName = "";
        if (webElement instanceof TypifiedElement) {
            elementName = "'" + ((TypifiedElement) webElement).getName() + "'";
        }
        return elementName;
    }

    public void clickOnElement(WebElement webElement) {
        try {
            webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info("Element was clicked " + getElementName(webElement));
        } catch (Exception e) {
            stopTestAndPrintMessage();
        }
    }

    public boolean isElementDisplayed(WebElement webElement) {
        for (int i = 0; i <= triesCount; i++) {
            try {
                waitVisibilityOfElement(webElement);
                boolean state = webElement.isDisplayed();
                logger.info(getElementName(webElement) + " is displayed ->" + state);
                return state;
            } catch (Exception e) {
                logger.info(getElementName(webElement) + " is displayed -> False");
            }
        }
        return false;
    }

    private void stopTestAndPrintMessage() {
        logger.error("Can't work with element ");
        Assert.fail("Can't work with element ");
    }

    /*It’s not clear why, but the method works the other way around, so the crossover is deliberately allowed in the lines
    * boolean isStateCheck = state.toLowerCase().equals("uncheck");
        boolean isStateUncheck = state.toLowerCase().equals("check");*/

    public void setStateToCheckBox(WebElement checkBox, String state) {
        boolean isStateCheck = state.toLowerCase().equals("uncheck");
        boolean isStateUncheck = state.toLowerCase().equals("check");
        boolean isCheckBoxSelected = checkBox.isSelected();
        if (isStateCheck || isStateUncheck) {
            if ((isStateCheck && isCheckBoxSelected) || (isStateUncheck && !isCheckBoxSelected)) {
                logger.info("CheckBox is already needed state");
            } else if ((isStateCheck && !isCheckBoxSelected) || (isStateUncheck && isCheckBoxSelected)) {
                clickOnElement(checkBox);
            } else {
                logger.error("State should be only 'check' or 'uncheck'");
                stopTestAndPrintMessage();
            }
        }
    }

    public void waitInvisibilityOfElement(WebElement webElement) {
        webDriverWait_10.until(ExpectedConditions.invisibilityOf(webElement));
        logger.info("Element was closed" + getElementName(webElement));
    }


    public void waitForPopUp(WebElement webElement) {
        webDriverWait_10.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitVisibilityOfElement(WebElement webElement) {
        ExpectedConditions.visibilityOf(webElement);
    }

    public void waitForVisibilityOfElementLocated(By locator, WebElement webElement) {
        for (int i = 0; i <= triesCount; i++) {
            try {
                webDriverWait_10.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (Exception e) {
                logger.info(getElementName(webElement) + " is visible -> False");
            }
        }
    }

    public void waitForVisibilityOfAllElement(WebElement webElement) {
        for (int i = 0; i <= triesCount; i++) {
            try {
                webDriverWait_10.until(ExpectedConditions.visibilityOf(webElement));
            } catch (Exception e) {
                logger.info(getElementName(webElement) + " is visible -> False");
            }
        }
    }

    public void waitForText(WebElement webElement, String text) {
        webDriverWait_10.until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    public void waitForClickableElement(WebElement webElement) {
        webDriverWait_10.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public String getTextFromElement(WebElement webElement) {
        String text = webElement.getText().trim();
        return text;
    }

    public void mouseOver(WebElement webElement) {
        actions.moveToElement(webElement).build().perform();
    }

    public void selectVisibleTextInDropDown(WebElement dropDown, String text) {
        try {
            Select select = new Select(dropDown);
            select.selectByVisibleText(text);
            logger.info(text + " was selected in Drop Down");
        }catch (Exception e){
            stopTestAndPrintMessage();
        }
    }
}

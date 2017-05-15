package Utilities;

import BaseClasses.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

/**
 * Created by MSH on 14/05/2017.
 * This class is used as a helper class for Expected Conditions
 */
public class ExpectedWaitCondition {

    public static WebElement ToBeClickable(By locator, WebDriverWait wait)
    {
        ExpectedCondition<WebElement> function = ExpectedConditions.elementToBeClickable(locator);
        return wait.until(function);
    }

    public static Boolean TextNotToBe(By locator, String value, WebDriverWait wait)
    {
        ExpectedCondition<Boolean> function = ExpectedConditions.not(ExpectedConditions.textToBe(locator, value));
        return wait.until(function);
    }

    public static void ToBeInvisible(By locator, WebDriverWait wait)
    {
        ExpectedCondition<Boolean> function = ExpectedConditions.invisibilityOfElementLocated(locator);
        wait.until(function);
    }

    public static WebElement ToBeVisible(By locator, WebDriverWait wait)
    {
        ExpectedCondition<WebElement> function = ExpectedConditions.visibilityOfElementLocated(locator);
        return wait.until(function);
    }

    public static WebElement ToBePresent(By locator, WebDriverWait wait)
    {
        ExpectedCondition<WebElement> function = ExpectedConditions.presenceOfElementLocated(locator);
        return wait.until(function);
    }
    public static void TextToMatchUsringRegex(By locator, Pattern pattern, WebDriverWait wait){
        ExpectedCondition<Boolean> function = ExpectedConditions.textMatches(locator, pattern);
        wait.until(function);}
}

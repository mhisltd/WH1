package POM;

import Utilities.ExpectedWaitCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by MSH on 15/05/2017.
 * This class was created to override afew methods specifically for mobile browser
 */
public class HomePageMobilePOM extends HomePagePOM
{
    private WebDriver _webDriver;
    private final String BalanceLink = "balanceLink";

    // Constructor
    public HomePageMobilePOM(WebDriver webDriver)
    {
        super(webDriver);
        _webDriver = webDriver;
    }

    @Override
    public WebElement GetBalance()
    {
        WebElement element = ExpectedWaitCondition.ToBeClickable(By.id(BalanceLink), new WebDriverWait(_webDriver, 30));
        return element;
    }

    // This method overrides the insert bet amount functionality for mobile device
    @Override
    public void InsertBetAmount(String betValue)
    {
        GetBetSlipMobileButton().click();
        GetBetSlipContentArea().click();
        GetNumberPad(betValue);
    }
}

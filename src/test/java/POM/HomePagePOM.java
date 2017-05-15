package POM;

import Utilities.ExpectedWaitCondition;
import Utilities.NumPadMapper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by MSH on 12/05/2017.
 * Page Object Model class for the WilliamHill home page
 * Most of the methods used in this class are used to facilitate tests by providing the required web elements.
 */
public class HomePagePOM {

    // Constants
    private final String LoginDropDown = "accountTabButton";
    private final String UserNameField = "loginUsernameInput";
    private final String PasswordField = "loginPasswordInput";
    private final String LoginButton = "loginButton";
    private final String AccountTabButton= "accountTabButton";
    private final String LogoutLink = "logoutLink";
    private final String OverlayFrame = "wh-global-overlay";
    private final String FirstBetOddButton = "betbutton__odds";
    private final String MobileBetSlipCount = "mobile-betslip-count";
    private final String BetSlipButtonToolbar = "betslip-btn-toolbar";
    private final String ContentAreaXpath = "//*[contains(@id,'stake-input__')]";
    private final String BetSlipSubMenuTable = "betslip-sub-menu-table";
    private final String NumericKeyPad = "numberpad";
    private final String TotalToReturnPrice = "total-to-return-price";
    private final String TotalStakePrice = "total-stake-price";
    private final String ReceiptNoticeBox = "receipt-notice-box";
    private final String BalanceLink = "balanceLink";
    private final String ReceiptToggleButtonXpath = "//*[@id=\"bet-receipt\"]/header/h2/div";
    private final String RefreshBalanceButton = ".header__balance-refresh";
    private final String PlaceBetButton = "place-bet-button";
    private final String ClearBetSlipCssSelect = ".clear";
    private final String EmptyBetSlipMessageCssSelect = "#betslip-message-empty > strong";
    private final String InsufficientFundMsgCssSelect = "#error-box-header > strong";
    private final String ContentAreaCss = "input[id^='stake-input__']";
    private final String ReceiptToggleButtonCss = ".betslip-header__toggle";
    private final String ShowBetReceiptHeader = "span.betslip-header__title-text";
    private final String UserBalance = "userBalance";
    private final String WaitToLoginCss = "#accountTabButton > span.account-tab__text.-login";

    // Fields
    private List<WebElement> _numericKeys;
    private HashMap<String, WebElement> _numericKeyPadMap;
    private final WebDriverWait _wait;
    private WebDriver _webDriver;



    public HomePagePOM(WebDriver webDriver)
    {
        _webDriver = webDriver;
        _wait = new WebDriverWait(_webDriver, 30);
    }

    public HomePagePOM()
    {
        _wait = null;
    }

    // Method returns Login drop down menu's web element
    public WebElement GetLoginDropDown()
    {
        return ExpectedWaitCondition.ToBeClickable(By.id(LoginDropDown), _wait);
    }

    // Method returns alert's web element that was added this morning on the web and was later removed
    public WebElement GetAlert()
    {
        return ExpectedWaitCondition.ToBeClickable(By.cssSelector(".btn--cancel"), _wait);
    }

    // Method returns username field's webelement
    public WebElement GetUsernameField()
    {
        return ExpectedWaitCondition.ToBeVisible(By.id(UserNameField), _wait);
    }

    // Method returns password field's weblement
    public WebElement GetPasswordField()
    {
        return ExpectedWaitCondition.ToBeVisible(By.id(PasswordField), _wait);
    }

    // Method returns login button's webelement
    public WebElement GetLoginButton()
    {
        return ExpectedWaitCondition.ToBeClickable(By.id(LoginButton), _wait);
    }

    // Method returns Account tab button's webelement
    public WebElement GetAccountTabButton()
    {
        return ExpectedWaitCondition.ToBeClickable(By.id(AccountTabButton), _wait);
    }

    // Method returns logout button's webelement
    public WebElement GetLogoutButton()
    {
        return ExpectedWaitCondition.ToBeClickable(By.id(LogoutLink), _wait);
    }

    // Method returns webelement for requested sports
    public WebElement GetSportTypeMenuButton(String sportType)
    {
        return ExpectedWaitCondition.ToBeVisible(By.id(sportType), _wait);
    }

    // Method returns webelement for first activebet available
    public WebElement GetFirstActiveBetButton()
    {
        ExpectedWaitCondition.ToBeInvisible(By.id(OverlayFrame), _wait);
        return ExpectedWaitCondition.ToBeClickable(By.className(FirstBetOddButton), _wait);
    }

    // Method returns webelement of betslip count
    public WebElement GetBetSlipCount()
    {
        WebElement element =ExpectedWaitCondition.ToBePresent(By.id(MobileBetSlipCount), _wait);
        return element;
    }

    // Method returns webelement for betslip button for mobile version.
    public WebElement GetBetSlipMobileButton()
    {
        ExpectedWaitCondition.TextToMatchUsringRegex(By.id(BetSlipButtonToolbar), Pattern.compile("\\d+"), _wait);
        WebElement element =  ExpectedWaitCondition.ToBeClickable(By.id(BetSlipButtonToolbar), _wait);

        return element;
    }

    // Method returns webelement for BetValue textbox
    public WebElement GetBetSlipContentArea()
    {
        WebElement element = ExpectedWaitCondition.ToBeVisible(By.cssSelector(ContentAreaCss), _wait);
        return element;
    }

    // This method assist to key in the bet value for mobile version
    public void GetNumberPad(String value)
    {
        if (_numericKeys == null){
            ExpectedWaitCondition.ToBeInvisible(By.id(BetSlipSubMenuTable), _wait);
            List<WebElement> numericKeys = ExpectedWaitCondition.ToBeVisible(By.id(NumericKeyPad), _wait).findElements(By.tagName("button"));
            NumPadMapper numpad = new NumPadMapper(_webDriver);
            _numericKeyPadMap = numpad.GetNumberKeys(numericKeys);
        }

        char[] valueToChar = value.toCharArray();

        for (int j = 0; j< valueToChar.length; j++)
        {
            _numericKeyPadMap.get(String.valueOf(valueToChar[j])).click();
        }
        _numericKeyPadMap.get("OK").click();
    }

    // This method fetch the Return amount
    public String GetToReturnValue()
    {
        return ExpectedWaitCondition.ToBeVisible(By.id(TotalToReturnPrice), _wait).getText();
    }

    // This method returns the Stake value displayed on the slip
    public String GetStakeValue()
    {
        return ExpectedWaitCondition.ToBeVisible(By.id(TotalStakePrice), _wait).getText();
    }

    // This method returns the Available balance displayed
    public WebElement GetBalance()
    {
        ExpectedWaitCondition.ToBeVisible(By.id(UserBalance), _wait);
        WebElement element = ExpectedWaitCondition.ToBeClickable(By.id(UserBalance), _wait);
        return element;
    }

    // This method returns the webelement of bet confirmation
    public WebElement GetBetPlacedConfirmation()
    {
        WebElement element = ExpectedWaitCondition.ToBeVisible(By.id(ReceiptNoticeBox), _wait);
        return element;
    }

    // This method returns the webelement of Receipt toggle button
    public WebElement GetReceiptToggleButton()
    {
        //WebElement element = ExpectedWaitCondition.ToBeClickable(By.xpath(ReceiptToggleButtonXpath), _wait);
        WebElement element = ExpectedWaitCondition.ToBeClickable(By.cssSelector(ReceiptToggleButtonCss), _wait);
        return element;
    }

    // This method returns the webelement of place bet button
    public WebElement PlaceABet()
    {
        return ExpectedWaitCondition.ToBeClickable(By.id(PlaceBetButton), _wait);
    }

    // This method returns the boolean of refresh button
    public Boolean GetRefreshButton(String previousBalance)
    {
        ExpectedWaitCondition.ToBeClickable(By.cssSelector(RefreshBalanceButton), _wait).click();
        return ExpectedWaitCondition.TextNotToBe(By.id(UserBalance), previousBalance, _wait);
    }

    // This method returns the ClearBetSlip button's webelement
    public WebElement GetClearBetSlip()
    {
        return ExpectedWaitCondition.ToBeClickable(By.cssSelector(ClearBetSlipCssSelect), _wait);
    }

    // This method returns the weblement of empty bet slip message
    public WebElement GetBetSlipEmptyMessage()
    {
        return ExpectedWaitCondition.ToBeVisible(By.cssSelector(EmptyBetSlipMessageCssSelect), _wait);
    }

    // This message returns the webelement of insufficient fund's message
    public WebElement GetInsufficientFundsMsg() {
        return ExpectedWaitCondition.ToBeVisible(By.cssSelector(InsufficientFundMsgCssSelect), _wait);
    }

    // This method assist to key in the bet amount
    public void InsertBetAmount(String betValue)
    {
        GetBetSlipContentArea().sendKeys(betValue);
    }

    // This method returns the webelement of showbet receipt's header
    public WebElement GetShowBetReceiptHeader()
    {
        return ExpectedWaitCondition.ToBeVisible(By.cssSelector(ShowBetReceiptHeader), _wait);
    }

    public void WaitToLogin() {
        ExpectedWaitCondition.ToBeInvisible(By.cssSelector(WaitToLoginCss),_wait);
    }
}

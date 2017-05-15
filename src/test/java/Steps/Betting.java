package Steps;

import BaseClasses.DriverBase;
import POM.HomePagePOM;
import Utilities.ValueCalculator;
import cucumber.api.java.After;
import org.junit.Assert;
import org.openqa.selenium.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;

/**
 * Created by MSH on 12/05/2017.
 */

public class Betting extends DriverBase {

    // Private fields
    private WebDriver _webDriver;
    private HomePagePOM _homePagePOM;
    private String _stakeValue;
    private String _returnValue;
    private String _preBetUserBalance;
    private DriverBase _driverBase;
    private String _browserType;
    private String _dataOdds;
    private final String MobileBrowser = "mobile";

    // Constructor with the dependency
    public Betting(DriverBase driverBase)
    {
        _driverBase = driverBase;
    }

    @Given("^I navigate to \"([^\"]*)\" with browser type \"([^\"]*)\"$")
    public void iNavigateTo(String url, String browserType) throws Throwable {

        _browserType = browserType;
        if (browserType.equals(MobileBrowser))
        {
            _webDriver = GetChromeMobileDriver();
            _homePagePOM = GetInstanceOfHomePageMobilePOM(_webDriver);
        }
        else
        {
            _webDriver = GetChromeDriver();
            _homePagePOM = GetInstanceOfHomePagePOM(_webDriver);
        }
        _webDriver.navigate().to(url);
    }

    @And("^I login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iLoginWithUsernameAndPassword(String username, String password) throws Throwable {
       _homePagePOM.GetLoginDropDown().click();
       _homePagePOM.GetUsernameField().sendKeys(username);
       _homePagePOM.GetPasswordField().sendKeys(password);
       _homePagePOM.GetLoginButton().click();
       _homePagePOM.WaitToLogin();
       //_homePagePOM.GetAlert().click();
    }

    @And("^I navigate to any \"([^\"]*)\" event$")
    public void iNavigateToAnySports_typeEvent(String eventType) throws Throwable {
        _homePagePOM.GetSportTypeMenuButton(eventType).click();
    }

    @When("^I add the first active selection to the betslip$")
    public void iAddTheFirstActiveSelectionToTheBetslip() throws Throwable {
        WebElement element = _homePagePOM.GetFirstActiveBetButton();
        _dataOdds = element.getText();
        element.click();
        _homePagePOM.GetBetSlipCount();
    }

    @And("^Place a \"([^\"]*)\" £ bet and assert the following$")
    public void placeABet_valueBetAndAssertTheFollowing(String betValue) throws Throwable {
        _homePagePOM.InsertBetAmount(betValue);
        _stakeValue = betValue;
        _returnValue = ValueCalculator.CalculateReturnValue(_dataOdds, betValue);
        _preBetUserBalance = _homePagePOM.GetBalance().getText();
        _homePagePOM.PlaceABet().click();
    }

    @Then("^Verify the return value on the bet receipt$")
    public void verify_the_return_value_on_TheBetReceipt() throws Throwable {
        _homePagePOM.GetBetPlacedConfirmation();
        _homePagePOM.GetReceiptToggleButton().click();
        String actualRtnValue = _homePagePOM.GetToReturnValue();
        Assert.assertEquals(actualRtnValue, _returnValue);
    }

    @And("^Verify stake value on the bet receipt$")
    public void totalStakeValueMustBeTotal_stakeOnTheBetReceipt() throws Throwable {
        Assert.assertEquals(_stakeValue, _homePagePOM.GetStakeValue());
    }

    @And("^User balance must be 0.05£ less than what it was$")
    public void userBalanceIsUpdatedToUser_balance() throws Throwable
    {
        _homePagePOM.GetRefreshButton(_preBetUserBalance);
        _preBetUserBalance = _preBetUserBalance.substring(1);
        _homePagePOM.GetShowBetReceiptHeader();
        BigDecimal expectedBalance = BigDecimal.valueOf(Double.valueOf(_preBetUserBalance)).subtract(BigDecimal.valueOf(Double.valueOf(_stakeValue)));
        BigDecimal actualBalance = BigDecimal.valueOf(Double.valueOf(_homePagePOM.GetBalance().getText().substring(1)));
        Assert.assertEquals(expectedBalance, actualBalance);
    }

    @And("^I logged off$")
    public void iLoggedOff() throws Throwable {
        _homePagePOM.GetAccountTabButton().click();
        _homePagePOM.GetLogoutButton().click();
    }

    @Then("^I can clear the betslip$")
    public void iCanClearTheBetslip() throws Throwable {
        _homePagePOM.GetClearBetSlip().click();
    }

    @When("^I added the first active selection to the betslip$")
    public void iAddedTheFirstActiveSelectionToTheBetslip() throws Throwable {
        WebElement element = _homePagePOM.GetFirstActiveBetButton();
        _dataOdds = element.getText();
        element.click();
        _homePagePOM.GetBetSlipCount();
        if (_browserType.equals(MobileBrowser))
        {
            _homePagePOM.GetBetSlipMobileButton().click();
        }
    }

    @And("^Verify that \"([^\"]*)\" appears in bet slip section$")
    public void verifyThatAppearsInBetSlipSection(String message) throws Throwable {

        Assert.assertEquals(message, _homePagePOM.GetBetSlipEmptyMessage().getText());
    }

    @After
    public void TearDown()
    {
        _webDriver.quit();
    }

    @Then("^the warning message \"([^\"]*)\" will be displayed$")
    public void theWarningMessageWillBeDisplayed(String message) throws Throwable {
        Assert.assertEquals(message, _homePagePOM.GetInsufficientFundsMsg().getText());
    }
}
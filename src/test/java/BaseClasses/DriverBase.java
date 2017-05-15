package BaseClasses;

import POM.HomePageMobilePOM;
import POM.HomePagePOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MSH on 14/05/2017.
 */
public class DriverBase {

    public HashMap<String, WebDriver> webDriverCollection;

    public static WebDriver webDriver;

    public WebDriver GetChromeDriver()
    {
        System.setProperty("webdriver.chrome.driver", "e:\\projects\\Browsers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    public WebDriver GetChromeMobileDriver()
    {
        System.setProperty("webdriver.chrome.driver", "e:\\projects\\Browsers\\chromedriver.exe");
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);
        Map<String, Object> mobileEmulation = new HashMap<String, Object>();

        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        Map<String, Object> chromeOptions = new HashMap<String, Object>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return new ChromeDriver(capabilities);
    }

    public void SetWebDriver(String driverType)
    {
        System.setProperty("webdriver.chrome.driver", "e:\\projects\\Browsers\\chromedriver.exe");
        if (driverType.equals("normal"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            webDriver = new ChromeDriver(options);
        }
        else {
            Map<String, Object> deviceMetrics = new HashMap<String, Object>();
            deviceMetrics.put("width", 360);
            deviceMetrics.put("height", 640);
            deviceMetrics.put("pixelRatio", 3.0);
            Map<String, Object> mobileEmulation = new HashMap<String, Object>();

            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

            Map<String, Object> chromeOptions = new HashMap<String, Object>();
            chromeOptions.put("mobileEmulation", mobileEmulation);
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            webDriver = new ChromeDriver(capabilities);
        }
    }

    public HomePageMobilePOM GetInstanceOfHomePageMobilePOM(WebDriver webdriver)
    {
        return new HomePageMobilePOM(webdriver);
    }
    public HomePagePOM GetInstanceOfHomePagePOM(WebDriver webdriver)
    {
        return new HomePagePOM(webdriver);
    }

}

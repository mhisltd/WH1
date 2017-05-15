package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

/**
 * Created by MSH on 14/05/2017.
 * This class is used as a helper class to map the Numeric key pad for the mobile version.
 * And make those buttons available for use in the automation process to key in values.
 */
public class NumPadMapper {

    private WebDriver _webDriver;

    // Constructor
    public NumPadMapper(WebDriver webDriver)
    {
        _webDriver = webDriver;
    }

    // Method maps the numeric pad keys and return the collection as HashMap
    public HashMap<String, WebElement> GetNumberKeys(List<WebElement> numericKeys)
    {
        // I've used hash map to use key for the representation of the number and have kept its respective WebElement
        // as the value in this collection.
        HashMap<String, WebElement> numericKeyPadMap = new HashMap<String, WebElement>();
        for (int i=0; i< numericKeys.stream().count(); i++)
        {
            numericKeyPadMap.put(numericKeys.get(i).getText(), numericKeys.get(i));
        }

        return numericKeyPadMap;
    }
}

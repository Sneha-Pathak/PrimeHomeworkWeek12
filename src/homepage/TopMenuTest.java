package homepage;

import basetest.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TopMenuTest extends BaseTest
{
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp()
    {
        openBrowser(baseUrl);
    }

    @After
    public void tearDown()
    {
        closeBrowser();
    }

    //element.getText().equals(menu)

    public void selectMenu(String menu)
    {
        List<WebElement> elementList = driver.findElements(By.xpath("//ul[@class='top-menu notmobile']/child::li"));
        for (WebElement element: elementList)
        {
            if(element.getText().equals(menu))
            {
                element.click();
                break;
            }
        }
    }

    @Test
    public void verifyPageNavigation() throws InterruptedException
    {
        Thread.sleep(5000);
        selectMenu("Apparel");
        Thread.sleep(5000);
        //Assert
        String expectedMessage = "Apparel";
        String actualMessage = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertEquals("Electronics is not display",expectedMessage,actualMessage);
    }
}

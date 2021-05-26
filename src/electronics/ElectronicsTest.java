package electronics;

import basetest.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElectronicsTest extends BaseTest
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

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException
    {
        Thread.sleep(5000);
        mouseHoverToElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[2]"));
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']/li[2]/ul/li[2]/a"));

        //Assert
        String expectedCellphone = "Cell phones";
        String actualCellphone = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertEquals("Sort by Z to A is not selected", expectedCellphone, actualCellphone);
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException
    {
        verifyUserShouldNavigateToCellPhonesPageSuccessfully();

        //Click on list view
        clickOnElement(By.xpath("//div[@class='product-viewmode']/a[@class='viewmode-icon list']"));

        //click on nokia lumia 1020
        Thread.sleep(3000);
        clickOnElement(By.xpath("//div[@data-productid='20']/div[2]/h2/a"));

        //Assert
        String expectedNokia = "Nokia Lumia 1020";
        String actualNokia = getTextFromElement(By.xpath("//div[@class='product-name']/h1"));
        Assert.assertEquals("Nokia not selected...",expectedNokia,actualNokia);

        //Assert
        String expectedNokiaPrice = "$349.00";
        String actualNokiaPrice = getTextFromElement(By.xpath("//span[@id='price-value-20']"));
        Assert.assertEquals("Price not match..",expectedNokiaPrice,actualNokiaPrice);

        //Add to cart
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));

        //Assert
        String expectedMessage = "The product has been added to your shopping cart";
        String actualMessage = getTextFromElement(By.xpath("//div[@id='bar-notification']/div/p"));
        Assert.assertEquals("Not added to shopping cart....", expectedMessage, actualMessage);

        clickOnElement(By.xpath("//span[@class='close']"));
        mouseHoverToElement(By.xpath("//li[@id='topcartlink']/a"));
        clickOnElement(By.xpath("//div[@id='flyout-cart']/div[1]/div[4]/button"));

        //Assert
        String expectedMessage4 = "Shopping cart";
        String actualMessage4 = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertEquals("Shopping cart page is not there....", expectedMessage4, actualMessage4);

        //Qty
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value='1']")).clear();
        sendTextToElement(By.xpath("//input[@value='1']"), "2");
        Thread.sleep(5000);
        clickOnElement(By.xpath("//div[@class='common-buttons']/button[1]"));
        Thread.sleep(3000);

//        //Assert
//        Thread.sleep(3000);
//        String expectedQty = "2";
//        String actualQty = getTextFromElement(By.xpath("//td[@class='quantity']/input"));
//        Assert.assertEquals("Qty not updated....", expectedQty, actualQty);

        //Assert
        String expectedPrice = "$698.00";
        String actualPrice = getTextFromElement(By.xpath("//tr[@class='order-total']/td[2]/span"));
        Assert.assertEquals("Price not updated....", expectedPrice, actualPrice);

        Thread.sleep(5000);
        clickOnElement(By.id("termsofservice"));
        Thread.sleep(5000);
        clickOnElement(By.id("checkout"));

        //Assert
        Thread.sleep(3000);
        String expected = "Welcome, Please Sign In!";
        String actual = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertEquals("Not on sign in page....", expected, actual);

        //Click on Register
        clickOnElement(By.xpath("//div[@class='buttons']/button[2]"));

        //Assert
        Thread.sleep(3000);
        String expectedRegister = "Register";
        String actualRegister = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertEquals("Not Register Page...",expectedRegister,actualRegister);

        //Filling details
        Thread.sleep(5000);
        clickOnElement(By.id("gender-male"));
        sendTextToElement(By.xpath("//input[@id='FirstName']"),"John");
        sendTextToElement(By.xpath("//input[@id='LastName']"),"Popally");
        Thread.sleep(2000);
        selectByValueFromDropDown(By.name("DateOfBirthDay"),"26");
        Thread.sleep(5000);
        selectByValueFromDropDown(By.name("DateOfBirthMonth"),"6");
        selectByValueFromDropDown(By.name("DateOfBirthYear"),"1985");
        sendTextToElement(By.id("Email"),"wohn@gmail.com");
        sendTextToElement(By.id("Password"),"Abcd123");
        sendTextToElement(By.id("ConfirmPassword"),"Abcd123");
        clickOnElement(By.id("register-button"));

        //Assert
        String expectedRegCompleted = "Your registration completed";
        String actualRegCompleted = getTextFromElement(By.xpath("//div[text()='Your registration completed']"));
        Assert.assertEquals("User is not Register Successfully..",expectedRegCompleted,actualRegCompleted);

        //Click on continue
        clickOnElement(By.xpath("//div[@class='buttons']/a"));

        //Assert
        String expectedCart = "Shopping cart";
        String actualCart = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertEquals("Shopping cart page is not there....", expectedCart, actualCart);

        Thread.sleep(5000);
        clickOnElement(By.id("termsofservice"));
        Thread.sleep(5000);
        clickOnElement(By.id("checkout"));

        Thread.sleep(3000);
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "233");
        sendTextToElement(By.id("BillingNewAddress_City"), "Harrow");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "13 Rapton Avenue");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "HA6 4JK");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07123456788");
        Thread.sleep(5000);
        clickOnElement(By.xpath("//div[@id='billing-buttons-container']/button[@class='button-1 new-address-next-step-button']"));

        //Shipping Method
        Thread.sleep(5000);
        clickOnElement(By.id("shippingoption_2"));
        clickOnElement(By.xpath("//div[@id='shipping-method-buttons-container']/button"));

        //Payment Method
        Thread.sleep(5000);
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//div[@id='payment-method-buttons-container']/button"));

        //Payment detail
        Thread.sleep(5000);
        selectByValueFromDropDown(By.id("CreditCardType"), "visa");
        sendTextToElement(By.id("CardholderName"), "John Popally");
        sendTextToElement(By.id("CardNumber"), "4716878960318117");
        Thread.sleep(3000);
        selectByValueFromDropDown(By.id("ExpireMonth"), "11");
        Thread.sleep(3000);
        selectByValueFromDropDown(By.id("ExpireYear"), "2021");
        sendTextToElement(By.id("CardCode"), "853");
        clickOnElement(By.xpath("//div[@id='payment-info-buttons-container']/button"));

        //Assert
        Thread.sleep(5000);
        String expectedPayment = "Credit Card";
        String actualPayment = getTextFromElement(By.xpath("//li[@class='payment-method']/span[@class='value']"));
        Assert.assertEquals("Wrong Information filled....", expectedPayment, actualPayment);

        //Assert
        Thread.sleep(3000);
        String expectedShipping = "2nd Day Air";
        String actualShipping = getTextFromElement(By.xpath("//li[@class='shipping-method']/span[@class='value']"));
        Assert.assertEquals("Wrong Information filled....", expectedShipping, actualShipping);

        //Assert
        Thread.sleep(3000);
        String expectedTotal = "$698.00";
        String actualTotal = getTextFromElement(By.xpath("//tr[@class='order-total']/td[2]/span[1]/strong"));
        Assert.assertEquals("Total not updated....", expectedTotal, actualTotal);

        //Confirm order
        clickOnElement(By.xpath("//div[@id='confirm-order-buttons-container']/button"));

        //Assert
        Thread.sleep(3000);
        String expectedThankYou = "Thank you";
        String actualThankYou = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertEquals("Thank you is not there....", expectedThankYou, actualThankYou);

        //Assert
        Thread.sleep(3000);
        String expectedSuccess = "Your order has been successfully processed!";
        String actualSuccess = getTextFromElement(By.xpath("//div[@class='page-body checkout-data']/div/div/strong"));
        Assert.assertEquals("Thank you is not there....", expectedSuccess, actualSuccess);

        //Click on continue
        clickOnElement(By.xpath("//div[@class='page-body checkout-data']/div/div[3]/button"));

        //Assert
        Thread.sleep(3000);
        String expectedWelcome = "Welcome to our store";
        String actualWelcome = getTextFromElement(By.xpath("//div[@class='topic-block-title']/h2"));
        Assert.assertEquals("Thank you is not there....", expectedWelcome, actualWelcome);

        //Click on logout
        clickOnElement(By.xpath("//div[@class='header-links']/ul/li[2]/a"));

        //Assert
        String expectedLink = "https://demo.nopcommerce.com/";
        String actualLink = driver.getCurrentUrl();
        Assert.assertEquals("Url not mathched...",expectedLink,actualLink);
    }
}

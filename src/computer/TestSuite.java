package computer;

import basetest.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class TestSuite extends BaseTest {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

     @After
     public void tearDown()
     {
         closeBrowser();
     }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() throws InterruptedException {
        Thread.sleep(5000);
        mouseHoverToElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]"));
        Thread.sleep(5000);
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));

        Thread.sleep(5000);
        selectByValueFromDropDown(By.id("products-orderby"), "6");

        //Assert
        String expectedMessage = "Lenovo IdeaCentre 600 All-in-One PC";
        String actualMessage = getTextFromElement(By.xpath("//h2[@class='product-title']/a[text()='Lenovo IdeaCentre 600 All-in-One PC']"));
        Assert.assertEquals("Sort by Z to A is not selected", expectedMessage, actualMessage);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        Thread.sleep(5000);
        mouseHoverToElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]"));
        Thread.sleep(5000);
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));

        Thread.sleep(5000);
        selectByValueFromDropDown(By.id("products-orderby"), "5");

        Thread.sleep(5000);
        clickOnElement(By.xpath("//div[@data-productid='1']/div[2]/div[3]/div[2]/button[1]"));

        //Assert
        String expectedMessage1 = "Build your own computer";
        String actualMessage1 = getTextFromElement(By.xpath("//div[@data-productid='1']/div[1]/div[2]/div[1]/h1"));
        Assert.assertEquals("Build your own computer page is not there....", expectedMessage1, actualMessage1);

        selectByValueFromDropDown(By.id("product_attribute_1"), "1");
        selectByValueFromDropDown(By.id("product_attribute_2"), "5");

        //radio buttons
        clickOnElement(By.id("product_attribute_3_7"));
        clickOnElement(By.id("product_attribute_4_9"));

        //checkbox
        Thread.sleep(2000);
        clickOnElement(By.id("product_attribute_5_12"));

        Thread.sleep(5000);
        //Assert
        String expectedMessage2 = "$1,475.00";
        String actualMessage2 = getTextFromElement(By.id("price-value-1"));
        Assert.assertEquals("Wrong price match....", expectedMessage2, actualMessage2);

        //Add to cart
        clickOnElement(By.id("add-to-cart-button-1"));

        //Assert
        String expectedMessage3 = "The product has been added to your shopping cart";
        String actualMessage3 = getTextFromElement(By.xpath("//div[@id='bar-notification']/div/p"));
        Assert.assertEquals("Not added to shopping cart....", expectedMessage3, actualMessage3);

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

        //Assert
        String expectedMessage5 = "$2,950.00";
        String actualMessage5 = getTextFromElement(By.xpath("//td[@class='cart-total-right']/span[@class='value-summary']/strong"));
        Assert.assertEquals("Qty not updated....", expectedMessage5, actualMessage5);

        Thread.sleep(5000);
        clickOnElement(By.id("termsofservice"));
        Thread.sleep(5000);
        clickOnElement(By.id("checkout"));

        //Assert
        String expected = "Welcome, Please Sign In!";
        String actual = getTextFromElement(By.xpath("//div[@class='page-title']/h1"));
        Assert.assertEquals("Not on sign in page....", expected, actual);

        //Checkout as a guest
        Thread.sleep(5000);
        clickOnElement(By.xpath("//div[@class='buttons']/button[@class='button-1 checkout-as-guest-button']"));

        //Billing Address
        Thread.sleep(5000);
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Mivaan");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Pandya");
        sendTextToElement(By.id("BillingNewAddress_Email"), "Pandya@gmail.com");
        Thread.sleep(3000);
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"), "233");
        sendTextToElement(By.id("BillingNewAddress_City"), "Wembley");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "30 Eton Avenue");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "HA9 0DB");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07829483519");
        Thread.sleep(5000);
        clickOnElement(By.xpath("//div[@id='billing-buttons-container']/button[@class='button-1 new-address-next-step-button']"));

        //Shipping Method
        Thread.sleep(5000);
        clickOnElement(By.id("shippingoption_1"));
        clickOnElement(By.xpath("//div[@id='shipping-method-buttons-container']/button"));

        //Payment Method
        Thread.sleep(5000);
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//div[@id='payment-method-buttons-container']/button"));

        //Payment detail
        Thread.sleep(5000);
        selectByValueFromDropDown(By.id("CreditCardType"), "MasterCard");
        sendTextToElement(By.id("CardholderName"), "Mivaan Pandya");
        sendTextToElement(By.id("CardNumber"), "5227735054577520");
        Thread.sleep(3000);
        selectByValueFromDropDown(By.id("ExpireMonth"), "11");
        Thread.sleep(3000);
        selectByValueFromDropDown(By.id("ExpireYear"), "2022");
        sendTextToElement(By.id("CardCode"), "722");
        clickOnElement(By.xpath("//div[@id='payment-info-buttons-container']/button"));

        //Assert
        Thread.sleep(5000);
        String expectedPayment = "Credit Card";
        String actualPayment = getTextFromElement(By.xpath("//li[@class='payment-method']/span[@class='value']"));
        Assert.assertEquals("Wrong Information filled....", expectedPayment, actualPayment);

        //Assert
        Thread.sleep(3000);
        String expectedShipping = "Next Day Air";
        String actualShipping = getTextFromElement(By.xpath("//li[@class='shipping-method']/span[@class='value']"));
        Assert.assertEquals("Wrong Information filled....", expectedShipping, actualShipping);

        //Assert
        Thread.sleep(3000);
        String expectedTotal = "$2,950.00";
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
    }
}

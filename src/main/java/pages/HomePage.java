package pages;

import com.automation.framework.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class HomePage extends BasePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public HomePage checkIfTitleDisplay(String locator) {
        Assert.assertTrue(getElement(locator).isDisplayed());
        return this;
    }

    public HomePage clickTshirtBtn(String locator) {
        clickOn(locator);
        return this;
    }

    public HomePage clickOnAddToCart(String hoverOverLocator, String addToCartLocator) {
        WebElement ele = getElement(hoverOverLocator);
        System.out.println(ele);
        getAction().moveToElement(ele).perform();
        clickOn(addToCartLocator);
        return this;
    }

    public HomePage clickOnProceedToCheckoutBtn(String locator) {
        clickOn(locator);
        return this;
    }

    public HomePage getCartTotalPrice(String locator) {
        String totalPrice = getElement(locator).getText();
        double price = Double.parseDouble(totalPrice.split("\\$")[1]);
        System.out.println(price);
        Assert.assertTrue((price <= 20.00), "price is less than 20");
        return this;
    }

    public HomePage checkIfShippingCostNoMoreThan7(String locator) {
        String shippingCost = getElement(locator).getText();
        double deliveryFee = Double.parseDouble(shippingCost.split("\\$")[1]);
        Assert.assertTrue(deliveryFee <= 7.00, "Shipping cost is not more than $7");
        return this;
    }


    public HomePage selectTermsAndCondition(String locator) {
        clickOn(locator);
        return this;
    }

    public HomePage clickBankWirePaymentOption(String locator) {
        clickOn(locator);
        return this;
    }


    public HomePage clickConfirmOrder(String locator) {
        clickOn(locator);
        return this;
    }

    public HomePage checkIfOrderConfirmationMsgDisplay(String locator) {
        Assert.assertTrue(getElement(locator).isDisplayed(), "Order Confirmation Msf displayed");
        return this;
    }

    public HomePage fetchBankwireInfo() {
        String amount = getElement("homepage_txtAmount").getText();
        String nameOfOwner = getElement("homepage_txtNameOfAccOwner").getText();
        String bankName = getElement("homepage_txtBankName").getText();
        System.out.println("Bank info :" + amount + " :" + nameOfOwner + ":" + bankName);
        return this;
    }


}

package com.schwab.ui.login.step_def;

import com.schwab.ui.login.pages.LoginPages;
import com.schwab.ui.login.utilities.BrowserUtils;
import com.schwab.ui.login.utilities.ConfigurationReader;
import com.schwab.ui.login.utilities.Driver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Login_Step_Def {

  LoginPages loginPages=new LoginPages();

    @Given("user is on the schwab.com")
    public void user_is_on_the_schwab_com() {
     Driver.getDriver().get(ConfigurationReader.getProperty("url"));


    }

    @Then("user {string} logs in wrong password and wrong loginID")
    public void user_logs_in_wrong_password_and_wrong_loginID(String signinOptions) {
                                                         //biz yazdik
String errorMessage="The Login ID or Password is invalid.";
WebElement iframe = Driver.getDriver().findElement(By.cssSelector("#LoginComponentForm"));

switch (signinOptions) {
    case "Account Summary":

        Driver.getDriver().manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        Driver.getDriver().switchTo().frame(iframe);
        WebElement selectElement = Driver.getDriver().findElement(By.cssSelector("#StartIn"));
        Select dropDownList = new Select(selectElement);
        List<WebElement> listOptions = dropDownList.getOptions();
        System.out.println("list size-->" + listOptions.size());
        if (listOptions.size() == 13) {
            Assert.assertEquals(listOptions.size(), 13);
        } else {
            System.out.println("list size is not equal to 13");
        }

        for (WebElement options : listOptions) {
            System.out.println(options.getText());
        }
        BrowserUtils.waitForVisibility(loginPages.login, 10);
        loginPages.login.sendKeys("test");
        loginPages.Password.sendKeys("1234");
        loginPages.loginButton.click();


        Assert.assertTrue(loginPages.errorCredentinalMessage.getText().contains(errorMessage));
//                Assert.assertEquals(errorMessage,login_pages.errorCredentialMessage.getText());
        break;

    case "Positions":
        Driver.getDriver().manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
        Driver.getDriver().switchTo().frame(iframe);
        Select positions=new Select(Driver.getDriver().findElement(By.cssSelector("#StartIn")));
        positions.selectByVisibleText("Positions");
        BrowserUtils.waitForVisibility(loginPages.login,10);
        loginPages.login.sendKeys("test1");
        loginPages.Password.sendKeys("987");
        loginPages.loginButton.click();
        Assert.assertEquals(errorMessage,loginPages.errorCredentinalMessage.getText());
        break;

    case "Order Status":
        Driver.getDriver().manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        Driver.getDriver().switchTo().frame(iframe);
        Select orderStatus=new Select(Driver.getDriver().findElement(By.cssSelector("#StartIn")));
        orderStatus.selectByVisibleText("Order Status");
        BrowserUtils.waitForVisibility(loginPages.login,10);
        loginPages.login.sendKeys("test2");
        loginPages.Password.sendKeys("900");
        loginPages.loginButton.click();
        Assert.assertEquals(errorMessage,loginPages.errorCredentinalMessage.getText());
        break;
}





    }



}

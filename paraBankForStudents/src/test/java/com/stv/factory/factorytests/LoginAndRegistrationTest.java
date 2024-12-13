package com.stv.factory.factorytests;

import com.stv.factory.factorypages.LoginPage;
import com.stv.factory.factorypages.RegistrationPage;
import com.stv.framework.core.drivers.MyDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginAndRegistrationTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = MyDriver.getDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://parabank.parasoft.com/parabank/register.htm");
        driver.manage().window().maximize();
    }

    @Test(description = "Test login functionality")
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("MrBeast")
                .enterPassword("proximaC")
                .clickLoginButton();
        Assert.assertEquals(driver.getTitle(), "ParaBank | Accounts Overview", "Login was not successful.");
    }

    @Test(description = "Verify successful registration functionality")
    public void testSuccessfulRegistration() {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // String uniqueUsername = "User" + System.currentTimeMillis(); for unique name
        // System.out.println("Attempting registration with username: " + uniqueUsername);

        System.out.println("Attempting registration with username:" + "MrBeast");

        registrationPage.fillRegistrationForm(
                "Aliaksandr",
                "Kartavitski",
                "Lusiu g 21",
                "Vilnius",
                "No state",
                "04886",
                "64873576",
                "123-44-6789",
                "MrBeast",
                "proximaC"
        ).clickRegisterButton();

        String successMessage = registrationPage.getSuccessMessage();
        System.out.println("Success Message: " + successMessage);

        Assert.assertTrue(
                successMessage.contains("Your account was created successfully"),
                "Registration was not successful. Expected success message not found."
        );
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

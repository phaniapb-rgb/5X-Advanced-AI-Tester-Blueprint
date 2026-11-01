package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public final class LoginTest extends BaseTest {
    @Test(groups = "smoke", description = "Checks the observed Wingify login page and its password masking")
    @Description("Verifies the real login title, accessible form controls, and password visibility toggle.")
    public void loginPageAndPasswordMaskingAreAvailable() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.waitUntilDisplayed();

        Assert.assertTrue(loginPage.isDisplayed(), "Expected the Wingify login page and its form controls.");
        Assert.assertTrue(loginPage.isPasswordMasked(), "Password should be masked by default.");

        loginPage.enterPassword("not-a-real-password");
        loginPage.togglePasswordVisibility();
        Assert.assertEquals(loginPage.getPasswordInputType(), "text",
                "The visibility control should reveal the password field.");

        loginPage.togglePasswordVisibility();
        Assert.assertTrue(loginPage.isPasswordMasked(), "The visibility control should mask the password again.");
    }
}

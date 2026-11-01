package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utilities.WaitUtility;

public final class LoginPage extends BasePage {
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator signInButton;
    private final Locator passwordVisibilityButton;

    public LoginPage(Page page) {
        super(page);
        emailInput = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Email address").setExact(true));
        passwordInput = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Password").setExact(true));
        signInButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Sign in").setExact(true));
        passwordVisibilityButton = page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Toggle password visibility").setExact(true));
    }

    public boolean isDisplayed() {
        return page.title().equals("Login - Wingify")
                && emailInput.isVisible()
                && passwordInput.isVisible()
                && signInButton.isVisible();
    }

    public void waitUntilDisplayed() {
        WaitUtility.waitUntilVisible(emailInput);
        WaitUtility.waitUntilVisible(passwordInput);
        WaitUtility.waitUntilVisible(signInButton);
    }

    public boolean isPasswordMasked() {
        return "password".equals(passwordInput.getAttribute("type"));
    }

    public void enterPassword(String password) {
        passwordInput.fill(password);
    }

    public void login(String username, String password) {
        emailInput.fill(username);
        passwordInput.fill(password);
        signInButton.click();
    }

    public void togglePasswordVisibility() {
        passwordVisibilityButton.click();
    }

    public String getPasswordInputType() {
        return passwordInput.getAttribute("type");
    }
}

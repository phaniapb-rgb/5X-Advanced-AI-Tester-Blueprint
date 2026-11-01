package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public final class ApplicationNavigationPage extends BasePage {
    public ApplicationNavigationPage(Page page) {
        super(page);
    }

    public void openVerifiedLink(String accessibleName) {
        Locator link = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(accessibleName).setExact(true));
        link.click();
    }
}

package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utilities.WaitUtility;

public abstract class BasePage {
    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    public boolean isHeadingVisible(String expectedHeading) {
        var heading = page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName(expectedHeading).setExact(true));
        WaitUtility.waitUntilVisible(heading);
        return heading.isVisible();
    }
}

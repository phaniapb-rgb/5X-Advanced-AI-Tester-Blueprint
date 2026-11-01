package pages;

import com.microsoft.playwright.Page;

public final class DashboardPage extends BasePage {
    public DashboardPage(Page page) {
        super(page);
    }

    public boolean isDashboardDisplayed(String verifiedHeading) {
        return isHeadingVisible(verifiedHeading);
    }
}

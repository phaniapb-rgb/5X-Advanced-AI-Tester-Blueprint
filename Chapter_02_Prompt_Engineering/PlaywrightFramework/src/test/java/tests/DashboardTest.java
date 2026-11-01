package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;

public final class DashboardTest extends BaseTest {
    @Test(groups = "authenticated", description = "Checks a dashboard heading supplied from verified application requirements")
    public void dashboardDisplaysConfiguredHeading() {
        authenticate();
        String verifiedHeading = config.getRequired("dashboard.heading");
        Assert.assertTrue(new DashboardPage(page).isDashboardDisplayed(verifiedHeading),
                "Expected dashboard heading: " + verifiedHeading);
    }
}

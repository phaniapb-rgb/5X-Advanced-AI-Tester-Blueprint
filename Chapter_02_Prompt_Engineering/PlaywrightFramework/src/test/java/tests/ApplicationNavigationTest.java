package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ApplicationNavigationPage;
import pages.DashboardPage;
import utilities.ConfigReader;

public final class ApplicationNavigationTest extends BaseTest {
    @DataProvider(name = "verifiedNavigation")
    public Object[][] verifiedNavigation() {
        String[] steps = ConfigReader.load().getRequired("application.navigation.steps").split(",", -1);
        Object[][] data = new Object[steps.length][2];
        for (int index = 0; index < steps.length; index++) {
            String[] step = steps[index].trim().split("\\|", 2);
            if (step.length != 2 || step[0].isBlank() || step[1].isBlank()) {
                throw new IllegalStateException(
                        "Each application.navigation.steps entry must use the format link name|page heading");
            }
            data[index][0] = step[0].trim();
            data[index][1] = step[1].trim();
        }
        return data;
    }

    @Test(dataProvider = "verifiedNavigation", dataProviderClass = ApplicationNavigationTest.class,
            groups = "authenticated", description = "Navigates only to application links explicitly configured from verified requirements")
    public void verifiedNavigationLinkOpensExpectedPage(String linkName, String expectedHeading) {
        authenticate();
        new ApplicationNavigationPage(page).openVerifiedLink(linkName);
        Assert.assertTrue(new DashboardPage(page).isDashboardDisplayed(expectedHeading),
                "Expected heading after opening '" + linkName + "': " + expectedHeading);
    }
}

package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.DashboardPage;
import pages.LoginPage;
import utilities.BrowserFactory;
import utilities.ConfigReader;
import utilities.ScreenshotUtility;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public abstract class BaseTest {
    protected static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
    protected static ConfigReader config;

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeSuite(alwaysRun = true)
    public void loadConfiguration() {
        config = ConfigReader.load();
        writeAllureEnvironment();
    }

    @BeforeClass(alwaysRun = true)
    public void launchBrowser() {
        String browserName = config.get("browser");
        boolean headless = config.getBoolean("headless");
        LOGGER.info("Launching {} in {} mode", browserName, headless ? "headless" : "headed");
        playwright = Playwright.create();
        browser = BrowserFactory.launch(playwright, browserName, headless);
        context = browser.newContext();
    }

    @BeforeMethod(alwaysRun = true)
    public void navigateToApplication() {
        page = context.newPage();
        page.setDefaultTimeout(Long.parseLong(config.get("timeout.ms")));
        String url = config.getRequired("base.url");
        LOGGER.info("Navigating to {}", url);
        page.navigate(url);
    }

    @AfterMethod(alwaysRun = true)
    public void captureFailureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE && page != null && !page.isClosed()) {
            try {
                byte[] screenshot = ScreenshotUtility.capture(page, result.getName());
                Allure.addAttachment("Failure screenshot", "image/png",
                        new ByteArrayInputStream(screenshot), ".png");
                LOGGER.error("Captured failure screenshot for {}", result.getName(), result.getThrowable());
            } catch (IOException | com.microsoft.playwright.PlaywrightException exception) {
                LOGGER.error("Unable to capture failure screenshot for {}", result.getName(), exception);
            }
        }
        if (page != null && !page.isClosed()) {
            page.close();
        }
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
        LOGGER.info("Browser resources closed");
    }

    protected void authenticate() {
        String username = config.getRequired("username");
        String password = config.getRequired("password");
        String dashboardHeading = config.getRequired("dashboard.heading");

        LOGGER.info("Submitting configured test-account login");
        LoginPage loginPage = new LoginPage(page);
        loginPage.waitUntilDisplayed();
        loginPage.login(username, password);
        Assert.assertTrue(new DashboardPage(page).isDashboardDisplayed(dashboardHeading),
                "Expected dashboard heading after login: " + dashboardHeading);
    }

    private void writeAllureEnvironment() {
        String resultsDirectory = System.getProperty("allure.results.directory", "target/allure-results");
        Path environmentFile = Path.of(resultsDirectory, "environment.properties");
        Properties environment = new Properties();
        environment.setProperty("Browser", config.get("browser"));
        environment.setProperty("Headed", Boolean.toString(!config.getBoolean("headless")));
        environment.setProperty("Java", System.getProperty("java.version"));
        environment.setProperty("Operating System", System.getProperty("os.name"));

        try {
            Files.createDirectories(environmentFile.getParent());
            try (OutputStream output = Files.newOutputStream(environmentFile)) {
                environment.store(output, "Playwright execution environment");
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to write Allure environment details to " + environmentFile,
                    exception);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void finishSuite() {
        LOGGER.info("Test suite execution finished");
    }
}

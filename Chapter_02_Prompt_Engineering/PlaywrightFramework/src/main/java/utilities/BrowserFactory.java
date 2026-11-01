package utilities;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Playwright;

import java.util.Locale;

public final class BrowserFactory {
    private BrowserFactory() {
    }

    public static Browser launch(Playwright playwright, String browserName, boolean headless) {
        BrowserTypeOptions options = new BrowserTypeOptions(headless);
        return switch (browserName.toLowerCase(Locale.ROOT)) {
            case "chromium", "chrome" -> playwright.chromium().launch(options.toLaunchOptions());
            case "firefox" -> playwright.firefox().launch(options.toLaunchOptions());
            case "webkit" -> playwright.webkit().launch(options.toLaunchOptions());
            default -> throw new IllegalArgumentException(
                    "Unsupported browser '" + browserName + "'. Choose chromium, firefox, or webkit.");
        };
    }

    private record BrowserTypeOptions(boolean headless) {
        com.microsoft.playwright.BrowserType.LaunchOptions toLaunchOptions() {
            return new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(headless);
        }
    }
}

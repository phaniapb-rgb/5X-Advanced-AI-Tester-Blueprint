package utilities;

import com.microsoft.playwright.Page;
import constants.FrameworkConstants;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtility {
    private static final DateTimeFormatter FILE_TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    private ScreenshotUtility() {
    }

    public static byte[] capture(Page page, String testName) throws java.io.IOException {
        Path directory = Path.of(FrameworkConstants.SCREENSHOT_DIRECTORY);
        Files.createDirectories(directory);
        String safeName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");
        Path screenshot = directory.resolve(safeName + "-" + LocalDateTime.now().format(FILE_TIMESTAMP) + ".png");
        byte[] image = page.screenshot(new Page.ScreenshotOptions().setPath(screenshot).setFullPage(true));
        return image;
    }
}

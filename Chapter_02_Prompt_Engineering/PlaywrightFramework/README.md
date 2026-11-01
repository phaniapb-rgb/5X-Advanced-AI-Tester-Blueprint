# Playwright Java Framework

A Java 17+, Maven, Playwright, and TestNG framework for the Wingify login page. Locators in `LoginPage` were selected from the observed page accessibility tree. Dashboard and navigation tests require verified requirements before they can be run.

## Requirements

- JDK 17 or newer
- Playwright browser binaries installed for the chosen browser

## Configuration

`src/test/resources/config.properties` contains non-secret defaults. Every setting can be overridden with an environment variable prefixed `PLAYWRIGHT_`, with dots replaced by underscores:

| Property | Environment variable | Default |
|---|---|---|
| `base.url` | `PLAYWRIGHT_BASE_URL` | `https://app.vwo.com/#/login` |
| `browser` | `PLAYWRIGHT_BROWSER` | `chromium` |
| `headless` | `PLAYWRIGHT_HEADLESS` | `false` (headed) |
| `timeout.ms` | `PLAYWRIGHT_TIMEOUT_MS` | `15000` |

Set test credentials locally as `PLAYWRIGHT_USERNAME` and `PLAYWRIGHT_PASSWORD`; do not commit credentials or use a personal password. The app's observed login field requests an email address, so configure an authorized test account's email (not an assumed username). Authenticated checks should only be run against an authorized test account/environment.

For authenticated dashboard/navigation tests, first confirm the expected heading and links from the application, then set:

- `PLAYWRIGHT_DASHBOARD_HEADING`
- `PLAYWRIGHT_APPLICATION_NAVIGATION_STEPS`, as comma-separated `link accessible name|expected page heading` entries

These settings are intentionally not populated with guessed application functionality.

## Install and run

Install the Java browser binaries once:

```powershell
.\mvnw.cmd '-Dexec.mainClass=com.microsoft.playwright.CLI' '-Dexec.args=install chromium' exec:java
```

Run the safe headed login-page smoke test:

```powershell
.\mvnw.cmd test
```

The default TestNG suite checks the observed page title, email/password controls, and password visibility toggle; it does not submit credentials. Authenticated tests are separated into `testng-authenticated.xml` and require `PLAYWRIGHT_USERNAME`, `PLAYWRIGHT_PASSWORD`, `PLAYWRIGHT_DASHBOARD_HEADING`, and verified navigation steps. Run them only against an authorized test environment:

```powershell
.\mvnw.cmd '-Dsurefire.suiteXmlFiles=src/test/resources/testng-authenticated.xml' test
```

To select another supported browser, set `PLAYWRIGHT_BROWSER` to `firefox` or `webkit` and install the corresponding browser binary. Set `PLAYWRIGHT_HEADLESS=true` only when a visible browser is not desired.

For a one-session PowerShell run, enter the authorized test email and password when prompted; the password is not saved to a file or Windows environment settings:

```powershell
$env:PLAYWRIGHT_USERNAME = Read-Host "Test account email"
$securePassword = Read-Host "Test-only password" -AsSecureString
$passwordPointer = [Runtime.InteropServices.Marshal]::SecureStringToBSTR($securePassword)
try {
    $env:PLAYWRIGHT_PASSWORD = [Runtime.InteropServices.Marshal]::PtrToStringBSTR($passwordPointer)
    $env:PLAYWRIGHT_DASHBOARD_HEADING = Read-Host "Verified dashboard heading"
    $env:PLAYWRIGHT_APPLICATION_NAVIGATION_STEPS = Read-Host "Verified navigation entries (link|heading,...)"
    .\mvnw.cmd '-Dsurefire.suiteXmlFiles=src/test/resources/testng-authenticated.xml' test
}
finally {
    [Runtime.InteropServices.Marshal]::ZeroFreeBSTR($passwordPointer)
    Remove-Item Env:PLAYWRIGHT_PASSWORD -ErrorAction SilentlyContinue
}
```

The authenticated suite needs real, verified heading/link values; it intentionally fails fast if they are missing rather than guessing application functionality. To make variables available to the VS Code test runner instead, add the same `PLAYWRIGHT_*` names under Windows **User environment variables**, then restart VS Code. Use a dedicated test-only account because Windows environment variables are not encrypted.

## Reports and diagnostics

- TestNG results: `target/surefire-reports`
- Allure results: `target/allure-results`
- Failure screenshots: `screenshots`
- Logs: `logs/framework.log`

The framework uses accessible roles/names for login controls, per-test browser pages, explicit locator waits, and failure screenshots. No fixed sleeps or hardcoded credentials are used.

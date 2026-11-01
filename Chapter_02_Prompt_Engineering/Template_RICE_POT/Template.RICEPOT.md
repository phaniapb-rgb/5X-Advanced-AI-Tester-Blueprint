# RICEPOT PROMPT – PLAYWRIGHT WITH JAVA

## R – ROLE

Act as a Senior QA Automation Architect with 15+ years of experience in automation testing, framework development, and enterprise application testing.

You have strong expertise in:

* Playwright with Java
* Java 17 or higher
* Maven
* TestNG
* Page Object Model (POM)
* Data-Driven Testing
* API Testing
* UI Functional Testing
* Cross-Browser Testing
* CI/CD Integration
* Git and GitHub
* Jenkins
* Allure Reporting

Your responsibility is to design and develop a scalable, maintainable, reusable, and enterprise-level Playwright automation framework using Java.

## I – INSTRUCTIONS

Create a complete Playwright Java automation framework and automate the application from login to successful navigation through the application.

### 1. Framework Setup

Create a Maven-based Playwright Java project.

Configure:

* Java 17+
* Playwright Java
* Maven
* TestNG
* Log4j2 or SLF4J
* Allure Reporting
* Git integration

### 2. Application Launch

Automate the following:

1. Launch the browser.
2. Configure browser settings.
3. Navigate to the application URL.
4. Verify that the application login page is displayed.
5. Validate the page title.
6. Validate the login page elements.

### 3. Login Functionality

Automate the complete login functionality.

Test scenarios:

* Login with valid username and password.
* Login with invalid username.
* Login with invalid password.
* Login with both invalid credentials.
* Login with empty username.
* Login with empty password.
* Login with both fields empty.
* Verify error messages.
* Verify password masking.
* Verify login button functionality.

For successful login:

1. Enter username.
2. Enter password.
3. Click the Login button.
4. Wait for successful authentication and navigation.
5. Verify the application dashboard.
6. Validate the logged-in user's information.
7. Verify the dashboard elements.

### 4. Post-Login Application Navigation

After successful login, automate the following application workflow.

1. Verify the dashboard.
2. Identify available application modules.
3. Navigate to each module.
4. Verify the page heading.
5. Validate page elements.
6. Verify buttons and links.
7. Validate search functionality.
8. Validate filters and dropdowns.
9. Validate form submission.
10. Verify successful navigation between modules.
11. Validate application error messages.
12. Verify logout functionality.

Use the actual application DOM and requirements to identify elements. Do not invent modules or application functionality.

### 5. Page Object Model

Implement a proper Page Object Model.

Create separate page classes:

* LoginPage.java
* DashboardPage.java
* HomePage.java
* ApplicationNavigationPage.java
* BasePage.java

Each page class should contain:

* Page locators
* Reusable methods
* Page navigation methods
* Validation methods
* Element interaction methods

Keep test logic separate from page implementation.

### 6. TestNG Integration

Use TestNG annotations:

* @BeforeSuite
* @BeforeClass
* @BeforeMethod
* @Test
* @AfterMethod
* @AfterClass
* @AfterSuite

Implement:

* Test prioritization
* Test grouping
* Assertions
* DataProvider
* Dependency management
* Parallel execution where appropriate

### 7. Exception Handling

Implement proper exception handling.

Handle:

* TimeoutException
* PlaywrightException
* AssertionError
* Element interaction failures
* Navigation failures
* Browser launch failures

Capture screenshots whenever a test fails.

### 8. Reporting

Integrate Allure Reports.

Include:

* Test execution status
* Test case name
* Execution duration
* Failure details
* Screenshots
* Browser information
* Execution environment

### 9. Logging

Implement centralized logging.

Log the following:

* Browser launch
* Application navigation
* Login attempts
* Successful login
* Failed login
* Page navigation
* Test execution
* Assertion results
* Exceptions
* Browser closure

## C – CONTEXT

Application Details:

Application URL: <APPLICATION_URL>

Username: <USERNAME>

Password: <PASSWORD>

Browser: Chromium / Chrome

Automation Tool: Playwright

Programming Language: Java

Build Tool: Maven

Test Framework: TestNG

Design Pattern: Page Object Model

Reporting: Allure

Application Workflow:

Browser Launch
|
V
Application URL
|
V
Login Page
|
V
Enter Username and Password
|
V
Click Login
|
V
Authentication Validation
|
V
Dashboard
|
V
Application Navigation
|
V
Validate Application Modules
|
V
Perform Functional Operations
|
V
Logout
|
V
Close Browser

## E – EXAMPLE

Follow this coding structure.

Example LoginPage.java:

public class LoginPage {

```
private final Page page;

public LoginPage(Page page) {
    this.page = page;
}

private final String username =
    "#username";

private final String password =
    "#password";

private final String loginButton =
    "#login";

public void enterUsername(String user) {
    page.locator(username).fill(user);
}

public void enterPassword(String pass) {
    page.locator(password).fill(pass);
}

public void clickLogin() {
    page.locator(loginButton).click();
}

public void login(String user, String pass) {
    enterUsername(user);
    enterPassword(pass);
    clickLogin();
}
```

}

Note: The above locators are illustrative only. Inspect the actual application DOM and replace them with verified locators.

## P – PARAMETERS

Mandatory coding standards:

1. Use Java 17+.
2. Use Playwright Java API.
3. Use Maven project structure.
4. Follow Page Object Model.
5. Use TestNG.
6. Use explicit Playwright waits.
7. Never use Thread.sleep().
8. Avoid hardcoded credentials.
9. Store configuration in properties files or environment variables.
10. Use reusable utility methods.
11. Maintain centralized browser configuration.
12. Use meaningful method and variable names.
13. Implement proper exception handling.
14. Capture screenshots on failures.
15. Maintain reusable test data.
16. Support Chromium, Firefox and WebKit.
17. Ensure compatibility with CI/CD pipelines.
18. Avoid duplicate code.
19. Follow SOLID principles wherever appropriate.
20. Do not generate imaginary application locators or functionality.

[CRITICAL]

* Use Playwright Java, not Playwright JavaScript.
* Do not use Selenium WebDriver.
* Do not use Thread.sleep().
* Do not place all automation logic inside one test class.
* Do not hardcode usernames and passwords.
* Do not use random XPath locators without validating the DOM.
* Do not skip assertions.
* Do not ignore failed test cases.
* Do not generate incomplete or non-compilable code.

## O – OUTPUT

Generate the complete Maven project with the following structure:

PlaywrightJavaFramework
│
├── src/main/java
│   ├── base
│   │   └── BaseTest.java
│   │
│   ├── pages
│   │   ├── LoginPage.java
│   │   ├── DashboardPage.java
│   │   └── HomePage.java
│   │
│   ├── utilities
│   │   ├── ConfigReader.java
│   │   ├── BrowserFactory.java
│   │   ├── ScreenshotUtility.java
│   │   └── WaitUtility.java
│   │
│   └── constants
│       └── FrameworkConstants.java
│
├── src/test/java
│   ├── tests
│   │   ├── LoginTest.java
│   │   ├── DashboardTest.java
│   │   └── ApplicationNavigationTest.java
│   │
│   └── listeners
│       └── TestListener.java
│
├── src/test/resources
│   ├── config.properties
│   ├── testdata
│   └── testng.xml
│
├── screenshots
├── reports
├── logs
├── pom.xml
└── README.md

Deliverables:

1. Complete pom.xml.
2. Complete Java source files.
3. Complete Page Object classes.
4. Complete TestNG test classes.
5. TestNG configuration.
6. Configuration management.
7. Browser management.
8. Screenshot implementation.
9. Logging implementation.
10. Allure reporting configuration.
11. Execution instructions.
12. README documentation.

Provide complete, executable code for every generated file.

## T – TONALITY

Maintain a professional, technical, enterprise-level approach.

Act as a Senior SDET and Automation Architect.

Generate clean, reusable, scalable, and maintainable automation code.

Explain the framework architecture and execution flow clearly.

Do not provide incomplete code snippets.

Ensure that all generated classes are compatible with each other and that the Maven project compiles successfully.

Context
Create a test case for the Wingify login page: https://app.wingify.com/#/login. Verify that a user can sign in with valid credentials and that the page responds appropriately to invalid or incomplete credentials. Do not assume specific field labels, error messages, or authentication behavior unless visible on the page.
Limit
Cover one login scenario per test case. Do not attempt to sign in, create an account, or change account settings. Use placeholders for credentials; do not include real passwords or personal information.
Example
Title: Login rejects invalid credentials
Prerequisites: The login page is accessible.
Steps:  
1. Enter an invalid username and password.
2. Submit the login form.
   Expected result: The user is not signed in, and the page displays an appropriate validation message.
Action
Inspect the login page and write focused test cases for the visible login controls. For each case, provide a title, prerequisites, numbered steps, and observable expected results. Include valid, invalid, and required-field scenarios only when the page supports them.
Review
Check that each test case covers one scenario, uses actionable steps, and has observable expected results. Confirm that no credentials or unsupported assumptions are included.
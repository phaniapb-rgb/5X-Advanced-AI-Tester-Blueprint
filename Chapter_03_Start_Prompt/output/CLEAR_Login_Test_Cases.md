# Wingify Login Page Test Cases

## Purpose

This document contains focused test cases for the Wingify login page at https://app.wingify.com/#/login using the instructions in the CLEAR prompt.

Important notes:
- Do not assume specific field labels, error text, or authentication behavior unless visible on the page.
- Use placeholders for credentials and do not include real passwords or personal information.
- Each test case covers one scenario only.
- Only include valid, invalid, and required-field scenarios when the page supports them.

---

## Test Case 1: Valid login with known-good credentials

- Title: Valid login with known-good credentials
- Prerequisites:
  - The login page is accessible.
  - A valid account exists for the test user.
  - The username/email and password fields are visible and accept input.
- Steps:
  1. Open the Wingify login page.
  2. Enter the valid username/email in the username/email field.
  3. Enter the valid password in the password field.
  4. Submit the login form.
- Expected result:
  - The user is signed in successfully.
  - The page transitions away from the login screen or shows the authenticated state.
  - The login form is no longer the active visible state.

---

## Test Case 2: Login rejects invalid credentials

- Title: Login rejects invalid credentials
- Prerequisites:
  - The login page is accessible.
  - A user account exists, but the credentials provided are not valid.
- Steps:
  1. Open the Wingify login page.
  2. Enter an invalid username/email in the username/email field.
  3. Enter an invalid password in the password field.
  4. Submit the login form.
- Expected result:
  - The user is not signed in.
  - The page remains on the login screen or displays an appropriate validation or error state.
  - No authenticated dashboard or account state is shown.

---

## Test Case 3: Login rejects empty username/email field

- Title: Login rejects empty username/email field
- Prerequisites:
  - The login page is accessible.
  - The page supports an email/username field and a submit action.
- Steps:
  1. Open the Wingify login page.
  2. Leave the username/email field blank.
  3. Enter a valid password in the password field.
  4. Submit the login form.
- Expected result:
  - The form is not submitted successfully.
  - The page shows an appropriate validation state or message for the missing username/email.
  - The user remains signed out.

---

## Test Case 4: Login rejects empty password field

- Title: Login rejects empty password field
- Prerequisites:
  - The login page is accessible.
  - The page supports a password field and a submit action.
- Steps:
  1. Open the Wingify login page.
  2. Enter a valid username/email in the username/email field.
  3. Leave the password field blank.
  4. Submit the login form.
- Expected result:
  - The form is not submitted successfully.
  - The page shows an appropriate validation state or message for the missing password.
  - The user remains signed out.

---

## Test Case 5: Login rejects blank username/email and password fields

- Title: Login rejects blank username/email and password fields
- Prerequisites:
  - The login page is accessible.
- Steps:
  1. Open the Wingify login page.
  2. Leave the username/email field blank.
  3. Leave the password field blank.
  4. Submit the login form.
- Expected result:
  - The login request is not accepted.
  - The page should indicate that required information is missing.
  - No sign-in occurs.

---

## Test Case 6: Login handles invalid username/email format when format validation is visible

- Title: Login handles invalid username/email format when format validation is visible
- Prerequisites:
  - The login page exposes an email/username field that appears to accept text input.
  - The page visibly validates format or rejects malformed entry.
- Steps:
  1. Open the Wingify login page.
  2. Enter a malformed username/email value (for example, a value not accepted by the visible validation rules).
  3. Enter a valid password value.
  4. Submit the login form.
- Expected result:
  - The system either blocks submission or shows the appropriate visible validation message.
  - The user is not signed in.
  - No authenticated state is shown.

---

## Test Case 7: Login does not sign in when the page shows an error state after failed submission

- Title: Login remains signed out after failed submission
- Prerequisites:
  - The login page is accessible.
  - A failed login attempt can be triggered using invalid or incomplete input.
- Steps:
  1. Open the Wingify login page.
  2. Enter invalid or incomplete credentials.
  3. Submit the form.
  4. Observe the page after submission.
- Expected result:
  - The page displays an appropriate error or validation state.
  - The user remains signed out.
  - The page does not transition to an authenticated application state.

---

## Review checklist

Before finalizing the test cases, confirm the following:
- Each case covers exactly one scenario.
- The steps are actionable and numbered.
- The expected result is observable and does not assume hidden behavior.
- No real credentials or personal data are included.
- Any field labels, messages, or validation rules are only included when visible on the page.

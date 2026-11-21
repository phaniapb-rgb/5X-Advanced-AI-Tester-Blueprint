# Login Module Functional Test Plan

- Test Plan ID: QA-LOGIN-TP-01
- Title: Functional Test Plan for VWO Login Page

## 1. Objective

Validate that the login module for [https://app.vwo.com/](https://app.vwo.com/) behaves according to the approved requirements:

- Registered users can log in with valid credentials.
- Invalid credentials must not grant access.
- Email and password are mandatory.
- Successful login opens the authenticated dashboard.

This plan verifies core login behavior, protects against unauthorized access, and confirms the expected user flow from login to dashboard access.

## 2. In Scope

- Login page rendering and field availability.
- Valid login for registered users.
- Invalid login attempts using incorrect credentials.
- Empty email and/or password validation behavior.
- Navigation to the authenticated dashboard after successful login.
- Basic user flow and error handling for the login module.
- Functional verification on supported browser/test environment applicable to this exercise.

## 3. Out of Scope

- Password reset or forgotten-password flows.
- Account registration or signup flows.
- MFA or SSO flows.
- Session timeout and inactivity behavior.
- Exact UI copy, branding, or marketing-specific content.
- Security testing beyond functional login validation.
- Accessibility, performance, or localization testing unless explicitly added by the project.
- Password complexity rules, password expiration, or lockout thresholds, because these are not specified in the approved requirements.

Note: Any behavior not explicitly described in the requirements is marked as “Requires clarification.”

## 4. Test Approach

This is a functional test plan focused on validating expected behavior for the login module. The approach will include:

- Positive testing for successful login with valid credentials.
- Negative testing for invalid, missing, and malformed inputs.
- Boundary and edge-case checks where the requirements support them.
- Verification that successful login results in access to the authenticated dashboard.
- Validation that system behavior is consistent and no access is granted when the requirements are not satisfied.

Recommended approach:

- Execute smoke tests on the main login journey.
- Run documented scenarios across representative test data and supported browsers.
- Review the result of each test case against the requirement it covers.
- Record any unspecified behavior under “Requires clarification.”

## 5. Test Scenarios

| ID | Scenario | Type | Preconditions | Steps | Expected Result |
|---|---|---|---|---|---|
| TC-01 | Successful login with valid registered credentials | Positive | User account exists and is active; valid email and password are known | 1. Open login page. 2. Enter valid email. 3. Enter valid password. 4. Click login | User is authenticated and redirected to the authenticated dashboard |
| TC-02 | Invalid email and valid password | Negative | User account exists; wrong email | 1. Enter an unregistered email. 2. Enter valid password. 3. Click login | Access is denied; the user remains on the login page or is shown an error state; dashboard is not accessible |
| TC-03 | Valid email and invalid password | Negative | User account exists; wrong password | 1. Enter valid email. 2. Enter incorrect password. 3. Click login | Access is denied; dashboard is not accessible |
| TC-04 | Empty email and valid password | Negative | Login page accessible | 1. Leave email blank. 2. Enter valid password. 3. Click login | Login is blocked; user is not authenticated |
| TC-05 | Valid email and empty password | Negative | Login page accessible | 1. Enter valid email. 2. Leave password blank. 3. Click login | Login is blocked; user is not authenticated |
| TC-06 | Both email and password empty | Negative | Login page accessible | 1. Leave email blank. 2. Leave password blank. 3. Click login | Login is blocked; user is not authenticated |
| TC-07 | Email field required validation | Negative | Login page accessible | 1. Trigger submission with empty email field and valid/empty password scenario as applicable | System prevents submission when email is missing; requirement of mandatory email is satisfied |
| TC-08 | Password field required validation | Negative | Login page accessible | 1. Trigger submission with valid email and empty password | System prevents submission when password is missing; requirement of mandatory password is satisfied |
| TC-09 | Access dashboard after successful login | Positive | User account valid; login page accessible | 1. Log in with valid credentials. 2. Verify dashboard is displayed | Authenticated dashboard loads successfully after login |
| TC-10 | Attempt direct access to dashboard without login | Negative | User is not authenticated | 1. Navigate directly to the authenticated dashboard URL | Access is denied or user is redirected to login; no dashboard data is visible |
| TC-11 | Login with whitespace-only email value | Boundary/Negative | Login page accessible | 1. Enter whitespace in email field and valid password. 2. Click login | Submission is blocked or rejected; no access granted |
| TC-12 | Login with whitespace-only password value | Boundary/Negative | Login page accessible | 1. Enter valid email. 2. Enter whitespace in password field. 3. Click login | Submission is blocked or rejected; no access granted |
| TC-13 | Login with maximum-length valid credential values | Boundary/Positive | User account exists with supported maximum valid input length | 1. Enter valid credentials at upper supported input limits. 2. Submit | Login succeeds if input is valid and supported by the application |
| TC-14 | Login using unregistered credentials | Negative | No user exists for the entered credentials | 1. Enter a non-existent email and any password or valid format. 2. Submit | Access is denied; no login occurs |
| TC-15 | Repeated failed login attempts | Negative | User account exists; login page accessible | 1. Submit multiple failed attempts with wrong credentials | Access remains denied; no unauthorized access occurs |
| TC-16 | Login after prior failure | Positive | User account exists; previous attempt failed | 1. Attempt login with invalid credentials. 2. Retry with valid credentials | Valid credentials succeed after an earlier failed attempt |

### Additional Notes on Scenario Coverage

- Positive scenarios confirm the primary requirement: valid users can log in.
- Negative scenarios confirm that invalid credentials, mandatory field violations, and unauthorized access are denied.
- Boundary scenarios are included only where supported by the approved requirements and reasonable testing logic.
- Any password rule or exact validation message is marked as “Requires clarification.”

## 6. Environment and Test Data

### 6.1 Test Environment

Recommended test environment:

- Browser(s): latest stable versions of one or more supported browsers for the application
- Operating System: supported desktop environment
- Network: stable internet access to [https://app.vwo.com/](https://app.vwo.com/)
- Test execution tool: functional automation or manual QA execution depending on the project setup
- Authentication flow: clean browser session before each test case

### 6.2 Test Data

Recommended test data set:

| Data Type | Example | Purpose |
|---|---|---|
| Valid registered user | email: registered.user@example.com; password: known valid password | Valid login scenario |
| Invalid email | wrong.user@example.com | Negative login validation |
| Invalid password | incorrectPassword123 | Negative login validation |
| Empty email | blank | Mandatory email check |
| Empty password | blank | Mandatory password check |
| Unregistered account | new.user@example.com | Access denial verification |
| Whitespace values | "   " | Input trimming/boundary handling |

Recommendations:

- Use a dedicated QA test account rather than a production user account.
- Ensure test data is isolated and not reused across active test cycles without cleanup.
- Store known valid credentials securely in the project’s approved secret/test-data management process.

Note: Password complexity rules, lockout behaviors, and accepted character formats are “Requires clarification.”

## 7. Entry Criteria

Recommended entry criteria:

- Login page is accessible and stable in the target environment.
- Test data for valid and invalid credentials is available and approved.
- Browser session is fresh or test setup is reset before execution.
- Required environment dependencies are active.
- The feature under test is ready for functional validation.

## 8. Exit Criteria

Recommended exit criteria:

- All planned core scenarios have been executed.
- Results are recorded and reviewed.
- No unexpected failures remain open for the login module in the current test cycle.
- Required login behaviors match the approved requirements.
- Any open issues or clarifications are documented.

## 9. Deliverables

- Test plan document.
- Scenario matrix covering positive, negative, and boundary cases.
- Test execution results log.
- Defect log for any failed or unexpected behaviors.
- Open questions / clarification list.

## 10. Risks and Open Questions

### Risks

| Risk | Description | Impact |
|---|---|---|
| Ambiguous validation behavior | Exact validation messages, UI behavior, and password rules are not specified | Medium |
| Environment instability | Browser or network issues may cause false negatives | Medium |
| Test data mismatch | Incorrect credentials or stale accounts can create invalid results | Medium |
| Unauthorized access issues | A broken login flow may allow access outside approved behavior | High |

### Open Questions / Clarifications Required

| Topic | Status |
|---|---|
| Exact error messages for invalid credentials | Requires clarification |
| Exact validation behavior for empty fields | Requires clarification |
| Password rules and restrictions | Requires clarification |
| Session timeout behavior | Requires clarification |
| Account lockout or repeated-failure policy | Requires clarification |
| Browser support matrix | Requires clarification |

## 11. Summary

This test plan covers the essential functional behavior of the login module at [https://app.vwo.com/](https://app.vwo.com/). It verifies valid login, invalid credential rejection, required-field validation, and successful access to the authenticated dashboard. It also captures boundary scenarios and explicitly marks any unspecified behavior as “Requires clarification” to avoid inventing requirements that are not approved.

The plan is intended to be reviewed and edited by the QA team before execution.

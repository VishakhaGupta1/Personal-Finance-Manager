# SauceDemo Selenium Automation Assignment

## Project Overview
This project automates key workflows on the SauceDemo website using Java, Selenium WebDriver, and JUnit. It follows the Page Object Model (POM) structure for maintainability and clarity.

## Prerequisites 
- Java JDK 8 or above
- Maven
- Google Chrome browser

## Setup Instructions
1. Clone or download this repository.
2. Open a terminal in the project root directory.
3. Run `mvn clean install` to download dependencies and build the project.
4. To execute tests, run `mvn test`.

## Project Structure
- `src/main/java/pages`: Page Object classes for each web page
- `src/test/java/tests`: Test classes for assignment scenarios
- `src/main/java/utils`: Utility classes (e.g., WebDriver setup)

## Assignment Tasks Automated
1. Login validation (valid/invalid credentials)
2. Add items to cart from inventory page
3. Add item to cart from product details page
4. Remove item from cart
5. Complete checkout workflow
6. Logout functionality

## Observations & Assumptions
- Chrome browser is used for automation.
- WebDriverManager is used for automatic driver management.
- All locators are based on current SauceDemo site structure.


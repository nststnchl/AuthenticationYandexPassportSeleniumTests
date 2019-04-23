# AuthenticationYandexPassportSeleniumTests
Selenium tests for authentication on a Yandex Password platform

To run all the tests you need to perform next steps:
1. Make sure you have chromedriver.exe on your computer and set path to in file `src/test/resources/config.properties` in variable `chrome.driver.path`
2. Launch all the tests from the root of the directory by running `mvn clean test`

There are three tests:
1. Successful login test (User can login successfully using valid credentials (email and password))
2. Unregistered email test (User gets a reject if email he entered is not registered)
3. Fields population test (Partially checking some of fields/buttons from a starter page for presence, editability and clickableness)

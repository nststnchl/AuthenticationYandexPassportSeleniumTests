# AuthenticationYandexPassportSeleniumTests
Selenium tests for authentication on a Yandex Password platform

To run all the tests you need to perform next steps:
1. Make sure you have chromedriver.exe on your computer and set path to in file `src/test/resources/config.properties` in variable `chrome.driver.path`
2. Run `mvn clean instal` to get a tar
3. Launch all the tests by `mvn clean test`
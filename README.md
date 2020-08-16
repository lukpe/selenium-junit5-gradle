# selenium-junit5-gradle
Selenium framework utilizing Junit5, built with Gradle

## requirements
* Java 11
* Gradle 6.6 or later
* Chrome and/or Firefox

## how to run
* `git clone https://github.com/lukpe/selenium-junit5-gradle.git`
* `gradlew clean test`
* Page `url`, `browser` and `timeout` are set in [test.properties](src/main/resources/test.properties)

## highlights
* [JUnit5](https://junit.org/junit5/)
* [Page Factory](https://github.com/SeleniumHQ/selenium/wiki/PageFactory)
* [webdrivermanager](https://github.com/bonigarcia/webdrivermanager)
* screenshots on failure -> `./screenshots` folder
* configuration file -> `./test.properties`

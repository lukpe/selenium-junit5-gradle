# selenium-junit5-gradle
Selenium framework utilizing Junit5, built with Gradle

## requirements
* Java 11
* Gradle 6.6 or later
* Chrome and/or Firefox

## how to run
[test.properties]: src/main/resources/test.properties
* `git clone https://github.com/lukpe/selenium-junit5-gradle.git`
* `gradlew clean test -Dbrowser={browser}`
* Page `url`, `browser` and `timeout` are set in [test.properties]

| optional parameters |
|---------------------|
|`-Dbrowser=firefox`|
|`-Dbrowser=chrome`|

* If no parameter: `browser` taken from [test.properties] 

## highlights
* [JUnit5](https://junit.org/junit5/)
* [Page Factory](https://github.com/SeleniumHQ/selenium/wiki/PageFactory)
* [webdrivermanager](https://github.com/bonigarcia/webdrivermanager)
* [Apache Log4j 2](https://logging.apache.org/log4j/2.x/) logs -> `./build/logs`
* screenshots on test fail -> `./build/screenshots`
* configuration file -> `./src/main/resources/test.properties`

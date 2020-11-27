package selenium.tests

import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver
import selenium.App

@Disabled
class AppTest: BaseTest() {
    @Test
    fun testAppHasAGreeting() {
        val classUnderTest = App()
        Assertions.assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }

    @Test fun goGoogle(){
        driver.get("http://www.google.com.br/")
        val query: WebElement = driver.findElement(By.name("q"))
        query.sendKeys("Caelum")
        query.submit()
    }

    @Test fun goBing(){
        driver.get("http://www.bing.com/")
        val query: WebElement = driver.findElement(By.name("q"))
        query.sendKeys("Caelum")
        query.submit()
    }
}

package selenium

import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver

open class BaseTest {
    companion object {
        lateinit var driver: WebDriver

        @JvmStatic @BeforeAll
        fun hiAll(){
            System.setProperty("webdriver.chrome.driver",
                    this::class.java.classLoader!!.getResource("chromedriver")!!.path)
            System.setProperty("webdriver.gecko.driver",
                    this::class.java.classLoader!!.getResource("geckodriver")!!.path)
        }
    }

    @BeforeEach fun hiEach(){ driver = FirefoxDriver() }

    @AfterEach fun byeEach(){ driver.quit() }
}

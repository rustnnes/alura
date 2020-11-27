package selenium.tests

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver
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

    @BeforeEach fun hiEach() {
        driver = FirefoxDriver()
        driver.get("http://localhost:8080/apenas-teste/limpa");
    }

    @AfterEach fun byeEach(){ driver.quit() }
}

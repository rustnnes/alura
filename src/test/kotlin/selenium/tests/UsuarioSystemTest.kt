package selenium

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

class UsuarioSystemTest {
    private lateinit var driver: WebDriver

    @BeforeEach
    fun hiEach() {
        driver = FirefoxDriver()
    }

    @AfterEach
    fun encerra() {
        driver.close()
    }

    @Test
    fun deveAdicionarUmUsuario() {
        driver.get("http://localhost:8080/usuarios/new");

        val nome = driver.findElement(By.name("usuario.nome"))
        val email = driver.findElement(By.name("usuario.email"))

        nome.sendKeys("Ronaldo Luiz de Albuquerque");
        email.sendKeys("ronaldo2009@terra.com.br");

        nome.submit()

        assertTrue(driver.getPageSource() .contains("Ronaldo Luiz de Albuquerque"));
        assertTrue(driver.getPageSource() .contains("ronaldo2009@terra.com.br"));
    }
}
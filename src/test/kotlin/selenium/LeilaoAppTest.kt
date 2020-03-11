package selenium

import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class LeilaoAppTest:BaseTest() {
    @Test fun goGoogle(){
        driver.get("http://localhost:8080/usuarios/new")
        val nome: WebElement = driver.findElement(By.name("usuario.nome"))
        val email: WebElement = driver.findElement(By.name("usuario.email"))
        nome.sendKeys("Ronaldo")
        email.sendKeys("ronaldo@ronaldo.com")
        driver.findElement(By.id("btnSalvar")).submit()
    }
}

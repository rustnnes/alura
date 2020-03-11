package selenium.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver


class UsuariosPage(private val driver: WebDriver) {
    fun visita() {
        driver.get("http://localhost:8080/usuarios")
    }

    fun novo(): NovoUsuarioPage {
        driver.findElement(By.linkText("Novo Usuário")).click()
        return NovoUsuarioPage(driver)
    }

    fun existeNaListagem(nome: String?, email: String?): Boolean {
        return driver.pageSource.contains(nome!!) &&
               driver.pageSource.contains(email!!)
    }

}
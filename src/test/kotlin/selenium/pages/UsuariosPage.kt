package selenium.pages

import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver


class UsuariosPage(driver: WebDriver) : BasePage(driver) {
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

    fun altera(posicao: Int): AlteraUsuarioPage? {
        driver.findElements(By.linkText("editar"))[posicao - 1].click()
        return AlteraUsuarioPage(driver)
    }

    fun deletaUsuarioNaPosicao(posicao: Int) {
        driver.findElements(By.tagName("button"))[posicao - 1].click()
        val alert: Alert = driver.switchTo().alert()
        alert.accept()
    }
}
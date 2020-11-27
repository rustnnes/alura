package selenium.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class NovoUsuarioPage(driver: WebDriver) : BasePage(driver) {

    fun cadastra(nome: String?, email: String?) {
        val txtNome = driver.findElement(By.name("usuario.nome"))
        val txtEmail = driver.findElement(By.name("usuario.email"))
        txtNome.sendKeys(nome)
        txtEmail.sendKeys(email)
        txtNome.submit()
    }

    fun validacaoDeNomeObrigatorio(): Boolean {
        return driver.pageSource.contains("Nome obrigatorio!")
    }

}
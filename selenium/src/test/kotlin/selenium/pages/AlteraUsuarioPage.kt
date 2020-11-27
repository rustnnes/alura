package selenium.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver


class AlteraUsuarioPage(driver: WebDriver) : BasePage(driver) {
    fun para(nome: String?, email: String?): UsuariosPage {
        val txtNome = driver.findElement(By.name("usuario.nome"))
        val txtEmail = driver.findElement(By.name("usuario.email"))
        txtNome.clear()
        txtEmail.clear()
        txtNome.sendKeys(nome)
        txtEmail.sendKeys(email)
        txtNome.submit()
        return UsuariosPage(driver)
    }

}
package selenium.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Select


class NovoLeilaoPage(driver: WebDriver) : BasePage(driver) {

    fun preenche(nome: String?, valor: Double, usuario: String?, usado: Boolean) {
        val txtNome = driver.findElement(By.name("leilao.nome"))
        val txtValor = driver.findElement(By.name("leilao.valorInicial"))
        txtNome.sendKeys(nome)
        txtValor.sendKeys(valor.toString())
        val combo = driver.findElement(By.name("leilao.usuario.id"))
        val cbUsuario = Select(combo)
        cbUsuario.selectByVisibleText(usuario)
        if (usado) {
            val ckUsado = driver.findElement(By.name("leilao.usado"))
            ckUsado.click()
        }
        txtNome.submit()
    }

    fun validacaoDeProdutoApareceu(): Boolean {
        return driver.pageSource.contains("Nome obrigatorio!")
    }

    fun validacaoDeValorApareceu(): Boolean {
        return driver.pageSource
                .contains("Valor inicial deve ser maior que zero!")
    }
}
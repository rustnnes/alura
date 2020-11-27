package selenium.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver


class LeiloesPage(driver: WebDriver) : BasePage(driver) {
    fun visita() {
        driver.get("http://localhost:8080/leiloes")
    }

    fun novo(): NovoLeilaoPage {
        driver.findElement(By.linkText("Novo Leilao")).click()
        return NovoLeilaoPage(driver)
    }

    fun existe(produto: String?, valor: Double, usuario: String?,
               usado: Boolean): Boolean {
        return driver.pageSource.contains(produto!!) &&
                driver.pageSource.contains(valor.toString()) &&
                driver.pageSource.contains(if (usado) "Sim" else "Não")
    }

    fun detalhes(posicao: Int): DetalhesDoLeilaoPage? {
        val elementos = driver.findElements(By.linkText("Exibir"))
        elementos[posicao - 1].click()
        return DetalhesDoLeilaoPage(driver)
    }

}
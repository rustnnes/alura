package selenium.pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait


class DetalhesDoLeilaoPage(driver: WebDriver) : BasePage(driver) {

    fun lance(usuario: String?, valor: Double) {
        val txtValor = driver.findElement(By.name("lance.valor"))
        val combo = driver.findElement(By.name("lance.usuario.id"))
        val cbUsuario = Select(combo)
        cbUsuario.selectByVisibleText(usuario)
        txtValor.sendKeys(valor.toString())
        driver.findElement(By.id("btnDarLance")).click()
    }

    fun existeLance(usuario: String?, valor: Double): Boolean {
        val element =  driver.findElement(By.id("lancesDados"))
        val temUsuario = WebDriverWait(driver, 10)
                .until(ExpectedConditions
                        .textToBePresentInElement(element, usuario))
        return if (temUsuario) driver.pageSource.contains(valor.toString()) else false
    }
}
package selenium.tests

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import selenium.pages.DetalhesDoLeilaoPage
import selenium.pages.LeiloesPage
import selenium.pages.NovoLeilaoPage
import selenium.pages.UsuariosPage


@Disabled
class LeilaoAppTest: BaseTest() {

    private lateinit var leiloesPage: LeiloesPage

    @Disabled
    @Test fun addUser(){
        driver.get("http://localhost:8080/usuarios/new")
        val nome: WebElement = driver.findElement(By.name("usuario.nome"))
        val email: WebElement = driver.findElement(By.name("usuario.email"))
        nome.sendKeys("Ronaldo")
        email.sendKeys("ronaldo@ronaldo.com")
        driver.findElement(By.id("btnSalvar")).submit()
    }

    @BeforeEach fun inicializa() {
        val usuarios = UsuariosPage(driver)
        usuarios.visita()
        usuarios.novo().cadastra("Paulo Henrique", "paulo@henrique.com")
        usuarios.novo().cadastra("José Alberto", "jose@alberto.com")

        leiloesPage = LeiloesPage(driver)
        leiloesPage.visita()
        leiloesPage.novo().preenche("Geladeira", 100.0, "Paulo Henrique", false)
    }

    @Test
    fun deveCadastrarUmLeilao() {
        val novoLeilao: NovoLeilaoPage = leiloesPage.novo()
        novoLeilao.preenche("Geladeira", 123.0, "Paulo Henrique", true)
        assertTrue(leiloesPage.existe("Geladeira", 123.0, "Paulo Henrique", true))
    }

    @Test
    fun deveFazerUmLance() {
        val lances: DetalhesDoLeilaoPage? = leiloesPage.detalhes(1)
        lances?.lance("José Alberto", 150.0)
        assertTrue(lances?.existeLance("José Alberto", 150.0)!!)
    }
}

package selenium.tests

import org.junit.jupiter.api.BeforeEach
import selenium.builder.CriadorDeCenarios

import selenium.pages.DetalhesDoLeilaoPage

class LanceAppTest: BaseTest() {
    private lateinit var lances: DetalhesDoLeilaoPage
    @BeforeEach
    fun criaCenario() {
        lances = DetalhesDoLeilaoPage(driver)
        CriadorDeCenarios(driver)
                .umUsuario("Paulo Henrique", "paulo@henrique.com")
                .umUsuario("José Alberto", "jose@alberto.com")
                .umLeilao("Paulo Henrique", "Geladeira", 100.0, false)
    }
}
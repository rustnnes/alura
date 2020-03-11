package selenium.tests

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import selenium.pages.UsuariosPage

class UsuarioSystemTest: BaseTest() {
    private lateinit var usuariosPage: UsuariosPage

    @Test
    fun deveAdicionarUmUsuario() {
        usuariosPage = UsuariosPage(driver)
        usuariosPage.visita()
        usuariosPage.novo()
                .cadastra("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br")

        assertTrue(usuariosPage.existeNaListagem("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"));
    }
}
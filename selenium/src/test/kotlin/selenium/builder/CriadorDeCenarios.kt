package selenium.builder

import org.openqa.selenium.WebDriver
import selenium.pages.LeiloesPage
import selenium.pages.UsuariosPage


class CriadorDeCenarios(private val driver: WebDriver) {
    fun umUsuario(nome: String?, email: String?): CriadorDeCenarios {
        val usuarios = UsuariosPage(driver)
        usuarios.visita()
        usuarios.novo().cadastra(nome, email)
        return this
    }

    fun umLeilao(usuario: String?,
                 produto: String?,
                 valor: Double,
                 usado: Boolean): CriadorDeCenarios {
        val leiloes = LeiloesPage(driver)
        leiloes.visita()
        leiloes.novo().preenche(produto, valor, usuario, usado)
        return this
    }
}
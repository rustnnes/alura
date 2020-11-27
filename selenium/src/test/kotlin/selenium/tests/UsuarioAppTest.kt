package selenium.tests

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import selenium.pages.NovoUsuarioPage
import selenium.pages.UsuariosPage


class UsuarioAppTest: BaseTest() {
    private lateinit var usuariosPage: UsuariosPage

    @Test
    fun deveAdicionarUmUsuario() {
        usuariosPage = UsuariosPage(driver)
        usuariosPage.visita()
        usuariosPage.novo()
                .cadastra("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br")

        assertTrue(usuariosPage.existeNaListagem("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"));
    }

    @Test
    fun naoDeveAdicionarUmUsuarioSemNome() {
        val form: NovoUsuarioPage = usuariosPage.novo()
        form.cadastra("", "ronaldo2009@terra.com.br")
        assertTrue(form.validacaoDeNomeObrigatorio())
    }

    @Test
    fun deveAlterarUmUsuario() {
        usuariosPage.novo()
                .cadastra("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br")
        usuariosPage.altera(1)?.para("José da Silva", "jose@silva.com")
        assertFalse(usuariosPage.existeNaListagem(
                "Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"))
        assertTrue(usuariosPage.existeNaListagem("José da Silva", "jose@silva.com"))
    }

    @Test
    fun deveDeletarUmUsuario() {
        usuariosPage.novo().cadastra("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br")
        assertTrue(usuariosPage.existeNaListagem("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"))
        usuariosPage.deletaUsuarioNaPosicao(1)
        assertFalse(usuariosPage.existeNaListagem("Ronaldo Luiz de Albuquerque", "ronaldo2009@terra.com.br"))
    }
}
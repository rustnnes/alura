/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package br.com.caelum.leilao.app;

import br.com.caelum.leilao.main.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}

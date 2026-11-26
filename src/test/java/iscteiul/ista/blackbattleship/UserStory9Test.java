package iscteiul.ista.blackbattleship;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Story;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Page Test JUnit 5 correspondente a {@code UserStoryTest9} da suite gravada.
 * Valida o nome preenchido na pré-visualização, tal como o assert do ficheiro SIDE.
 */
public class UserStory9Test {
    /** Sessão de navegador do teste atual. */
    private WebDriver driver;

    /**
     * Cria o Chrome antes de cada teste. A opção {@code -Dheadless=true} permite
     * executar sem janela; por omissão a execução é visível para acompanhamento.
     */
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1440,1000", "--lang=en");
        if (Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    /**
     * Guarda o ecrã final no Allure e fecha todas as janelas da sessão, incluindo
     * quando uma interação ou uma assertion falha.
     */
    @AfterEach
    public void tearDown() {
        if (driver == null) {
            return;
        }
        try {
            Allure.addAttachment("URL final", "text/plain", driver.getCurrentUrl());
            Allure.addAttachment("Ecrã final", "image/png", new ByteArrayInputStream(
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)), ".png");
        } catch (WebDriverException error) {
            Allure.addAttachment("Captura indisponível", error.toString());
        } finally {
            driver.quit();
        }
    }


    /** Preenche o nome do torneio e verifica o texto na pré-visualização. */
    @Test
    @Story("US09 — Identificar um torneio")
    @DisplayName("US09 — Mostrar NomeDoTorneio na pré-visualização")
    public void testUserStory9() {
        UserStory9 page = new UserStory9(driver);
        page.openTournamentPage();
        page.acceptCookiesIfPresent();
        page.enterTournamentName("NomeDoTorneio");

        assertEquals("NomeDoTorneio", page.getPreviewName(),
                "A pré-visualização deve mostrar o nome introduzido");
    }
}

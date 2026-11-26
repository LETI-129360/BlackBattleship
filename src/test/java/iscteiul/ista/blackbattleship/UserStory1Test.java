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
 * Page Test JUnit 5 correspondente a {@code UserStoryTest1} da suite gravada.
 * Usa apenas operações da Page Object; mantém a assertion fora da página.
 */
public class UserStory1Test {
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


    /** Reproduz a entrada como convidado e verifica o nickname visível na sala. */
    @Test
    @Story("US01 — Jogar como convidado")
    @DisplayName("US01 — Entrar como convidado com o nickname Lab5Jogador")
    public void testUserStory1() {
        UserStory1 page = new UserStory1(driver);
        page.openHomePage();
        page.acceptCookiesIfPresent();
        page.selectBattleship();
        page.playAgainstRobot();
        page.enterNickname("Lab5Jogador");
        page.continueAsGuest();

        assertEquals("Lab5Jogador", page.getDisplayedNickname(),
                "O nome do convidado deve aparecer na sala de jogo");
    }
}

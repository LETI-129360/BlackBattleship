package iscteiul.ista.blackbattleship;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Page Object do cenário {@code UserStoryTest10} de {@code TestSuite_129360.side}.
 * Encapsula a entrada na criação de torneios e a leitura do jogo selecionado.
 */
public class UserStory10 {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final By COOKIE_CONSENT =
            By.xpath("//button[normalize-space(.)='Consent']");

    private static final By CREATE_TOURNAMENT = By.cssSelector(".position-relative > .front");
    private static final By SELECTED_GAME = By.cssSelector(".choice > span:nth-child(3)");

    /**
     * Inicializa as operações do cenário na sessão fornecida.
     *
     * @param driver sessão WebDriver do teste
     */
    public UserStory10(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /** Abre a página de Batalha Naval, ponto de partida da gravação. */
    @Step("Abrir a página de Batalha Naval")
    public void openBattleshipPage() {
        driver.get("https://papergames.io/en/battleship");
    }

    /** Abre a configuração de torneios a partir do jogo. */
    @Step("Abrir a opção de criar torneio")
    public void openTournamentCreation() {
        pauseForHumanPace();
        wait.until(ExpectedConditions.elementToBeClickable(CREATE_TOURNAMENT)).click();
    }

    /**
     * Aguarda até 15 segundos pelo nome do jogo selecionado no formulário.
     *
     * @return texto da opção de jogo apresentada
     */
    public String getSelectedGame() {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(SELECTED_GAME)).getText().trim();
    }

    /**
     * Aceita o aviso de cookies quando está disponível. A ausência do aviso é
     * permitida; uma falha ao clicar num aviso encontrado continua a ser um erro.
     */
    public void acceptCookiesIfPresent() {
        WebElement consent;
        try {
            consent = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(COOKIE_CONSENT));
        } catch (TimeoutException absentConsent) {
            return;
        }
        pauseForHumanPace();
        consent.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(COOKIE_CONSENT));
    }

    /** Reproduz o comando gravado {@code window.scrollTo(0,0)}. */
    public void scrollToTop() {
        pauseForHumanPace();
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
    }

    /**
     * Introduz uma pausa curta para acompanhar visualmente as ações na GUI.
     * A propriedade {@code ui.pause.ms} define os milissegundos (700 por omissão);
     * zero desativa a pausa sem eliminar as esperas pelos elementos da página.
     *
     * @throws IllegalStateException se a execução for interrompida durante a pausa
     */
    private void pauseForHumanPace() {
        long pauseMillis = Long.getLong("ui.pause.ms", 700L);
        if (pauseMillis <= 0) {
            return;
        }
        try {
            Thread.sleep(pauseMillis);
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Execução interrompida durante a pausa da GUI", interrupted);
        }
    }
}

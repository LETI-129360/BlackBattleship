package iscteiul.ista.blackbattleship;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Page Object do cenário {@code UserStoryTest1} de {@code TestSuite_129360.side}.
 * Encapsula os seletores e as operações para entrar como convidado contra o robot.
 */
public class UserStory1 {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final By COOKIE_CONSENT =
            By.xpath("//button[normalize-space(.)='Consent']");

    private static final By BATTLESHIP = By.cssSelector("div:nth-of-type(1) > a .img-fluid");
    private static final By PLAY_VS_ROBOT =
            By.cssSelector("app-juicy-button:nth-of-type(2) .front > span");
    private static final By NICKNAME = By.cssSelector(".position-relative > .text-center");
    private static final By CONTINUE = By.cssSelector(".align-items-center > .btn");
    private static final By PLAYER_NAME = By.cssSelector(".text-end > .text-truncate");

    /**
     * Inicializa as operações do cenário na sessão fornecida.
     *
     * @param driver sessão WebDriver do teste
     */
    public UserStory1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /** Abre a página inicial usada na gravação. */
    @Step("Abrir a página inicial do papergames")
    public void openHomePage() {
        driver.get("https://papergames.io/en");
    }

    /** Seleciona Batalha Naval na lista de jogos. */
    @Step("Selecionar Batalha Naval")
    public void selectBattleship() {
        pauseForHumanPace();
        wait.until(ExpectedConditions.elementToBeClickable(BATTLESHIP)).click();
    }

    /** Seleciona a opção de jogar contra o robot. */
    @Step("Escolher Play vs robot")
    public void playAgainstRobot() {
        pauseForHumanPace();
        wait.until(ExpectedConditions.elementToBeClickable(PLAY_VS_ROBOT)).click();
    }

    /**
     * Preenche a identificação do convidado.
     *
     * @param nickname nickname a apresentar na sala
     */
    @Step("Introduzir o nickname {nickname}")
    public void enterNickname(String nickname) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(NICKNAME));
        pauseForHumanPace();
        field.clear();
        field.sendKeys(nickname);
    }

    /** Confirma a identificação e entra na sala de jogo. */
    @Step("Continuar como convidado")
    public void continueAsGuest() {
        pauseForHumanPace();
        wait.until(ExpectedConditions.elementToBeClickable(CONTINUE)).click();
    }

    /**
     * Aguarda pelo nome visível na sala, usando os 15 segundos da gravação.
     *
     * @return nickname apresentado na interface
     */
    public String getDisplayedNickname() {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(PLAYER_NAME)).getText().trim();
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

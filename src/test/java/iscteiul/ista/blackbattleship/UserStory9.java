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
 * Page Object do cenário {@code UserStoryTest9} de {@code TestSuite_129360.side}.
 * Encapsula a edição do nome do torneio e a leitura da pré-visualização.
 * Este cenário configura o formulário sem publicar um torneio.
 */
public class UserStory9 {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final By COOKIE_CONSENT =
            By.xpath("//button[normalize-space(.)='Consent']");

    private static final By TOURNAMENT_NAME = By.id("tf-name");
    private static final By NAME_PREVIEW = By.cssSelector(
            "app-tournament-banner > div > div:nth-of-type(1) > div > div:nth-of-type(2)");

    /**
     * Inicializa as operações do cenário na sessão fornecida.
     *
     * @param driver sessão WebDriver do teste
     */
    public UserStory9(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /** Abre a configuração de um torneio de Batalha Naval. */
    @Step("Abrir a criação de torneios de Batalha Naval")
    public void openTournamentPage() {
        driver.get("https://papergames.io/en/t/create-tournament?game=Battleships");
    }

    /**
     * Substitui o nome do torneio no campo identificado na gravação.
     *
     * @param name nome a atribuir ao torneio
     */
    @Step("Preencher o nome do torneio: {name}")
    public void enterTournamentName(String name) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(TOURNAMENT_NAME));
        pauseForHumanPace();
        field.clear();
        field.sendKeys(name);
    }

    /**
     * Lê o nome na pré-visualização com o limite de 150 segundos gravado no SIDE.
     *
     * @return nome apresentado no resumo do torneio
     */
    public String getPreviewName() {
        return new WebDriverWait(driver, Duration.ofSeconds(150))
                .until(ExpectedConditions.visibilityOfElementLocated(NAME_PREVIEW)).getText().trim();
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

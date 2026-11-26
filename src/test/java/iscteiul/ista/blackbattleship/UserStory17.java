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
import java.util.Set;

/**
 * Page Object do cenário {@code UserStoryTest17} de {@code TestSuite_129360.side}.
 * Encapsula a ligação Learn more de RANKA e a leitura do título da explicação.
 */
public class UserStory17 {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final By COOKIE_CONSENT =
            By.xpath("//button[normalize-space(.)='Consent']");

    // Alternativa CSS gravada para Learn more, limitada ao painel RANKA.
    private static final By LEARN_MORE = By.cssSelector(".area-ranka a");
    private static final By RANKA_TITLE = By.cssSelector("[data-fontsize=\"48\"]");

    /**
     * Inicializa as operações do cenário na sessão fornecida.
     *
     * @param driver sessão WebDriver do teste
     */
    public UserStory17(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    /** Abre a página de Batalha Naval com o painel RANKA. */
    @Step("Abrir Batalha Naval para consultar RANKA")
    public void openBattleshipPage() {
        driver.get("https://papergames.io/en/battleship");
    }

    /**
     * Abre a explicação de RANKA. Se a ligação criar outra janela, muda o contexto
     * WebDriver para essa janela antes de continuar as operações da página.
     */
    @Step("Abrir Learn more de RANKA")
    public void openLearnMore() {
        Set<String> previousWindows = driver.getWindowHandles();
        pauseForHumanPace();
        wait.until(ExpectedConditions.elementToBeClickable(LEARN_MORE)).click();
        wait.until(browser -> {
            for (String handle : browser.getWindowHandles()) {
                if (!previousWindows.contains(handle)) {
                    browser.switchTo().window(handle);
                    return true;
                }
            }
            return browser.getCurrentUrl().contains("/blog/ranka");
        });
        wait.until(ExpectedConditions.urlContains("/blog/ranka"));
    }

    /**
     * Aguarda até 15 segundos pelo título, explicitando o limite omitido no SIDE.
     *
     * @return título apresentado na página de explicação
     */
    public String getExplanationTitle() {
        return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(RANKA_TITLE)).getText().trim();
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

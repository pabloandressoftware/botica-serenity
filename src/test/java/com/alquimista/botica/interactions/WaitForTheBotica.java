package com.alquimista.botica.interactions;

import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * La botica simula la respuesta de un servidor mostrando un caldero de carga (#capa-cargando)
 * que se oculta con el atributo hidden o desaparece porque la página navega a otra.
 * Serenity no trae una interacción para esperar exactamente eso, así que se crea una propia:
 * espera hasta que la página esté cargada y el caldero no exista o esté oculto.
 */
public class WaitForTheBotica {

    private static final Duration MAX_WAIT = Duration.ofSeconds(15);
    private static final String PROCESSING_FINISHED =
            "var capa = document.getElementById('capa-cargando');"
                    + "return document.readyState === 'complete' && (capa === null || capa.hidden);";

    public static Performable toFinishProcessing() {
        return Interaction.where("{0} espera a que la botica termine de procesar", actor -> {
            WebDriverWait wait = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), MAX_WAIT);
            wait.ignoring(WebDriverException.class)   // la página puede estar navegando mientras se consulta
                .until(driver -> Boolean.TRUE.equals(((JavascriptExecutor) driver).executeScript(PROCESSING_FINISHED)));
        });
    }

    private WaitForTheBotica() {
    }
}

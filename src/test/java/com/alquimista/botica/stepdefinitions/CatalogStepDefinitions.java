package com.alquimista.botica.stepdefinitions;

import com.alquimista.botica.questions.TheCatalog;
import com.alquimista.botica.tasks.SearchCatalog;
import com.alquimista.botica.ui.CatalogPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CatalogStepDefinitions {

    @Cuando("{actor} busca productos que contengan {string}")
    public void searchesByName(Actor actor, String text) {
        actor.attemptsTo(SearchCatalog.byName(text));
    }

    @Y("busca productos que contengan {string}")
    public void alsoSearchesByName(String text) {
        searchesByName(theActorInTheSpotlight(), text);
    }

    @Cuando("{actor} filtra el catálogo por la categoría {string}")
    public void filtersByCategory(Actor actor, String category) {
        actor.attemptsTo(SearchCatalog.byCategory(category));
    }

    @Entonces("{actor} debería ver solo los productos {string}")
    public void shouldSeeOnlyTheProducts(Actor actor, String products) {
        List<String> expected = Arrays.stream(products.split(",")).map(String::trim).toList();
        actor.attemptsTo(
                Ensure.thatTheListOf(TheCatalog.visibleProducts()).containsExactlyElementsFrom(expected)
        );
    }

    @Entonces("{actor} debería ver el aviso {string}")
    public void shouldSeeTheNoResultsNotice(Actor actor, String notice) {
        actor.attemptsTo(
                Ensure.that(CatalogPage.NO_RESULTS).isDisplayed(),
                Ensure.that(CatalogPage.NO_RESULTS).text().isEqualTo(notice)
        );
    }

    @Y("no debería ver ningún producto en el catálogo")
    public void shouldSeeNoProducts() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.thatTheListOf(TheCatalog.visibleProducts()).isEmpty()
        );
    }
}

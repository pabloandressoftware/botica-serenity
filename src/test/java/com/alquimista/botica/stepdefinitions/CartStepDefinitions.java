package com.alquimista.botica.stepdefinitions;

import com.alquimista.botica.tasks.AddToCart;
import com.alquimista.botica.tasks.ManageCart;
import com.alquimista.botica.ui.CartPage;
import com.alquimista.botica.ui.CatalogPage;
import com.alquimista.botica.ui.Header;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CartStepDefinitions {

    @Dado("que {actor} ya tiene {int} unidades de {string} en el carrito")
    public void alreadyHasInTheCart(Actor actor, int quantity, String product) {
        actor.attemptsTo(
                AddToCart.alreadyHaving(quantity, product),
                Ensure.that(Header.CART_COUNTER).text().isEqualTo(String.valueOf(quantity))
        );
    }

    @Cuando("{actor} agrega {int} unidades de {string} al carrito")
    public void addsToTheCart(Actor actor, int quantity, String product) {
        actor.attemptsTo(AddToCart.units(quantity, product));
    }

    @Cuando("{actor} elimina {string} de su carrito")
    public void removesFromTheCart(Actor actor, String product) {
        actor.attemptsTo(ManageCart.remove(product));
    }

    @Cuando("{actor} cambia la cantidad de {string} a {int} unidades")
    public void changesTheQuantity(Actor actor, String product, int quantity) {
        actor.attemptsTo(ManageCart.changeQuantity(product, quantity));
    }

    @Entonces("{actor} debería ver el mensaje del catálogo {string}")
    public void shouldSeeTheCatalogMessage(Actor actor, String message) {
        actor.attemptsTo(
                Ensure.that(CatalogPage.MESSAGE).isDisplayed(),
                Ensure.that(CatalogPage.MESSAGE).text().isEqualTo(message)
        );
    }

    @Entonces("{actor} debería ver el mensaje del carrito {string}")
    public void shouldSeeTheCartMessage(Actor actor, String message) {
        actor.attemptsTo(
                Ensure.that(CartPage.MESSAGE).isDisplayed(),
                Ensure.that(CartPage.MESSAGE).text().isEqualTo(message)
        );
    }

    @Y("el contador del carrito debería mostrar {int} unidades")
    public void theCartCounterShouldShow(int units) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(Header.CART_COUNTER).text().isEqualTo(String.valueOf(units))
        );
    }

    @Entonces("{actor} debería ver el producto {string} marcado como agotado")
    public void shouldSeeTheProductSoldOut(Actor actor, String product) {
        actor.attemptsTo(
                Ensure.that(CatalogPage.PRODUCT_CARD_OF.of(product)).attribute("class").contains("agotado"),
                Ensure.that(CatalogPage.ADD_BUTTON_OF.of(product)).text().isEqualTo("Agotado")
        );
    }

    @Y("no debería poder agregar {string} al carrito")
    public void cannotAddToTheCart(String product) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CatalogPage.ADD_BUTTON_OF.of(product)).isDisabled(),
                Ensure.that(CatalogPage.QUANTITY_OF.of(product)).isDisabled()
        );
    }

    @Y("debería ver que su carrito está vacío")
    public void shouldSeeAnEmptyCart() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CartPage.EMPTY_CART).isDisplayed(),
                Ensure.that(Header.CART_COUNTER).text().isEqualTo("0")
        );
    }
}

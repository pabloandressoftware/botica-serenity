package com.alquimista.botica.stepdefinitions;

import com.alquimista.botica.models.ShippingData;
import com.alquimista.botica.tasks.Checkout;
import com.alquimista.botica.ui.CheckoutPage;
import com.alquimista.botica.ui.ConfirmationPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CheckoutStepDefinitions {

    @Cuando("{actor} confirma el pedido con envío {string} y datos de envío válidos")
    public void confirmsTheOrder(Actor actor, String shippingMethod) {
        actor.attemptsTo(Checkout.withShipping(ShippingData.valid(), shippingMethod));
    }

    @Cuando("{actor} intenta confirmar el pedido con el campo {string} con valor {string}")
    public void triesToConfirmWithInvalidField(Actor actor, String field, String value) {
        actor.attemptsTo(Checkout.withShipping(ShippingData.valid().with(field, value), "normal"));
    }

    @Entonces("{actor} debería ver la confirmación de su pedido")
    public void shouldSeeTheConfirmation(Actor actor) {
        actor.attemptsTo(
                Ensure.that(ConfirmationPage.TITLE).text().isEqualTo("¡Pedido confirmado!"),
                Ensure.that(ConfirmationPage.ORDER_NUMBER).text().matches("BOT-\\d{4}")
        );
    }

    @Y("el pedido debería registrar un envío de {string} y un total pagado de {string}")
    public void theOrderShouldRegister(String shipping, String total) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(ConfirmationPage.SHIPPING).text().isEqualTo(shipping),
                Ensure.that(ConfirmationPage.TOTAL_PAID).text().isEqualTo(total)
        );
    }

    @Entonces("{actor} debería ver el aviso de pago {string}")
    public void shouldSeeTheCheckoutNotice(Actor actor, String notice) {
        actor.attemptsTo(
                Ensure.that(CheckoutPage.FORM_MESSAGE).isDisplayed(),
                Ensure.that(CheckoutPage.FORM_MESSAGE).text().isEqualTo(notice)
        );
    }

    @Y("el campo {string} debería indicar el error {string}")
    public void theFieldShouldShowTheError(String field, String error) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CheckoutPage.FIELD_ERROR.of(field)).text().isEqualTo(error)
        );
    }
}

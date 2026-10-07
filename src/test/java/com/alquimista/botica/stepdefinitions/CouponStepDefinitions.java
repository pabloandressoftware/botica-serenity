package com.alquimista.botica.stepdefinitions;

import com.alquimista.botica.tasks.ApplyCoupon;
import com.alquimista.botica.ui.CartPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CouponStepDefinitions {

    @Cuando("{actor} aplica el cupón {string}")
    public void appliesTheCoupon(Actor actor, String code) {
        actor.attemptsTo(ApplyCoupon.withCode(code));
    }

    @Y("aplicó el cupón {string}")
    public void hasAppliedTheCoupon(String code) {
        appliesTheCoupon(theActorInTheSpotlight(), code);
    }

    @Entonces("{actor} debería ver el mensaje de cupón {string}")
    public void shouldSeeTheCouponMessage(Actor actor, String message) {
        actor.attemptsTo(
                Ensure.that(CartPage.COUPON_MESSAGE).isDisplayed(),
                Ensure.that(CartPage.COUPON_MESSAGE).text().isEqualTo(message)
        );
    }

    @Y("el carrito debería mostrar un descuento de {string} y un total parcial de {string}")
    public void theCartShouldShowTheDiscount(String discount, String partialTotal) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CartPage.DISCOUNT).text().isEqualTo(discount),
                Ensure.that(CartPage.PARTIAL_TOTAL).text().isEqualTo(partialTotal)
        );
    }

    @Y("el carrito no debería mostrar ningún descuento")
    public void theCartShouldShowNoDiscount() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(CartPage.DISCOUNT_ROW).isNotDisplayed()
        );
    }
}

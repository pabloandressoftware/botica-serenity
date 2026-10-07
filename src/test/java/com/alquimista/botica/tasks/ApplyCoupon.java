package com.alquimista.botica.tasks;

import com.alquimista.botica.interactions.WaitForTheBotica;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.alquimista.botica.ui.CartPage.APPLY_COUPON_BUTTON;
import static com.alquimista.botica.ui.CartPage.COUPON_FIELD;

public class ApplyCoupon {

    /** Palabra que en el Gherkin indica que la compra se hace sin cupón. */
    public static final String NO_COUPON = "ninguno";

    public static Performable withCode(String code) {
        return Task.where("{0} aplica el cupón '" + code + "'",
                actor -> {
                    if (NO_COUPON.equalsIgnoreCase(code)) {
                        return;
                    }
                    actor.attemptsTo(
                            NavigateTo.theCart(),
                            Enter.theValue(code).into(COUPON_FIELD),
                            Click.on(APPLY_COUPON_BUTTON),
                            WaitForTheBotica.toFinishProcessing()
                    );
                });
    }

    private ApplyCoupon() {
    }
}

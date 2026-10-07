package com.alquimista.botica.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.alquimista.botica.ui.CartPage.QUANTITY_OF;
import static com.alquimista.botica.ui.CartPage.REMOVE_BUTTON_OF;
import static com.alquimista.botica.ui.CartPage.UPDATE_BUTTON_OF;

public class ManageCart {

    public static Performable remove(String product) {
        return Task.where("{0} elimina " + product + " de su carrito",
                NavigateTo.theCart(),
                Click.on(REMOVE_BUTTON_OF.of(product))
        );
    }

    public static Performable changeQuantity(String product, int quantity) {
        return Task.where("{0} cambia la cantidad de " + product + " a " + quantity,
                NavigateTo.theCart(),
                Clear.field(QUANTITY_OF.of(product)),
                Enter.theValue(String.valueOf(quantity)).into(QUANTITY_OF.of(product)),
                Click.on(UPDATE_BUTTON_OF.of(product))
        );
    }

    private ManageCart() {
    }
}

package com.alquimista.botica.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.alquimista.botica.ui.CatalogPage.ADD_BUTTON_OF;
import static com.alquimista.botica.ui.CatalogPage.QUANTITY_OF;

public class AddToCart {

    /** Agrega desde el catálogo la cantidad indicada de un producto. */
    public static Performable units(int quantity, String product) {
        return Task.where("{0} agrega " + quantity + " unidades de " + product + " al carrito",
                Clear.field(QUANTITY_OF.of(product)),
                Enter.theValue(String.valueOf(quantity)).into(QUANTITY_OF.of(product)),
                Click.on(ADD_BUTTON_OF.of(product))
        );
    }

    /** Prepara el carrito: si la cantidad es 0 no agrega nada. */
    public static Performable alreadyHaving(int quantity, String product) {
        return Task.where("{0} tiene " + quantity + " unidades de " + product + " en el carrito",
                actor -> {
                    if (quantity > 0) {
                        actor.attemptsTo(units(quantity, product));
                    }
                });
    }

    private AddToCart() {
    }
}

package com.alquimista.botica.tasks;

import com.alquimista.botica.models.ShippingData;
import com.alquimista.botica.interactions.WaitForTheBotica;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.alquimista.botica.ui.CartPage.CHECKOUT_BUTTON;
import static com.alquimista.botica.ui.CheckoutPage.ADDRESS;
import static com.alquimista.botica.ui.CheckoutPage.CITY;
import static com.alquimista.botica.ui.CheckoutPage.CONFIRM_BUTTON;
import static com.alquimista.botica.ui.CheckoutPage.EMAIL;
import static com.alquimista.botica.ui.CheckoutPage.FULL_NAME;
import static com.alquimista.botica.ui.CheckoutPage.PHONE;
import static com.alquimista.botica.ui.CheckoutPage.SHIPPING_OPTION;

public class Checkout {

    /** Va del carrito al pago, diligencia los datos de envío, elige el método y confirma. */
    public static Performable withShipping(ShippingData data, String shippingMethod) {
        return Task.where("{0} confirma el pedido con envío " + shippingMethod,
                NavigateTo.theCart(),
                Click.on(CHECKOUT_BUTTON),
                Enter.theValue(data.fullName()).into(FULL_NAME),
                Enter.theValue(data.email()).into(EMAIL),
                Enter.theValue(data.address()).into(ADDRESS),
                Enter.theValue(data.city()).into(CITY),
                Enter.theValue(data.phone()).into(PHONE),
                Click.on(SHIPPING_OPTION.of(shippingMethod)),
                Click.on(CONFIRM_BUTTON),
                WaitForTheBotica.toFinishProcessing()
        );
    }

    private Checkout() {
    }
}

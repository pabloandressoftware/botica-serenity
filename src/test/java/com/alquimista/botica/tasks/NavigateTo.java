package com.alquimista.botica.tasks;

import com.alquimista.botica.ui.Header;
import com.alquimista.botica.ui.LoginPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateTo {

    public static Performable theLoginPage() {
        return Task.where("{0} abre la página de ingreso de la botica",
                Open.relativeUrl(LoginPage.PATH)
        );
    }

    /** Escribe la dirección de una página directamente en el navegador (sin pasar por el menú). */
    public static Performable thePage(String path) {
        return Task.where("{0} entra directamente a " + path,
                Open.relativeUrl("/" + path)
        );
    }

    public static Performable theCart() {
        return Task.where("{0} abre su carrito",
                Click.on(Header.CART_LINK)
        );
    }

    private NavigateTo() {
    }
}

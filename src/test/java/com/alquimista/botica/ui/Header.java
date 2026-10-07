package com.alquimista.botica.ui;

import net.serenitybdd.screenplay.targets.Target;

public class Header {

    public static final Target GREETING_USER = Target.the("usuario del saludo").locatedBy(".saludo strong");
    public static final Target CART_COUNTER = Target.the("contador del carrito").locatedBy("[data-testid='contador-carrito']");
    public static final Target CART_LINK = Target.the("enlace al carrito").locatedBy("#enlace-carrito");
    public static final Target LOGOUT_BUTTON = Target.the("botón cerrar sesión").locatedBy("[name='cerrar-sesion']");

    private Header() {
    }
}

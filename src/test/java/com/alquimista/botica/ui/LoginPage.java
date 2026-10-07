package com.alquimista.botica.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {

    public static final String PATH = "/index.html";

    public static final Target USERNAME = Target.the("campo usuario").locatedBy("#usuario");
    public static final Target PASSWORD = Target.the("campo contraseña").locatedBy("[name='clave']");
    public static final Target LOGIN_BUTTON = Target.the("botón ingresar").locatedBy("#form-login button[type='submit']");
    public static final Target ERROR_MESSAGE = Target.the("mensaje de error de ingreso").locatedBy("[data-testid='error-login']");
    public static final Target TITLE = Target.the("título de la página de ingreso").locatedBy("//main[contains(@class,'tarjeta-login')]/h1");

    private LoginPage() {
    }
}

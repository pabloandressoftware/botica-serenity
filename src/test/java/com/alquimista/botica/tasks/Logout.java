package com.alquimista.botica.tasks;

import com.alquimista.botica.ui.Header;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class Logout {

    public static Performable now() {
        return Task.where("{0} cierra su sesión",
                Click.on(Header.LOGOUT_BUTTON)
        );
    }

    private Logout() {
    }
}

package com.alquimista.botica.tasks;

import com.alquimista.botica.interactions.WaitForTheBotica;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.alquimista.botica.ui.LoginPage.LOGIN_BUTTON;
import static com.alquimista.botica.ui.LoginPage.PASSWORD;
import static com.alquimista.botica.ui.LoginPage.USERNAME;

public class Login {

    public static final String PRACTICE_USER = "alquimista";
    public static final String PRACTICE_PASSWORD = "pocion123";

    public static Performable withCredentials(String user, String password) {
        return Task.where("{0} inicia sesión como '" + user + "'",
                Enter.theValue(user).into(USERNAME),
                Enter.theValue(password).into(PASSWORD),
                Click.on(LOGIN_BUTTON),
                WaitForTheBotica.toFinishProcessing()
        );
    }

    public static Performable asThePracticeUser() {
        return Task.where("{0} inicia sesión con el usuario de práctica",
                NavigateTo.theLoginPage(),
                withCredentials(PRACTICE_USER, PRACTICE_PASSWORD)
        );
    }

    private Login() {
    }
}

package com.alquimista.botica.stepdefinitions;

import com.alquimista.botica.questions.TheCurrentPage;
import com.alquimista.botica.tasks.Login;
import com.alquimista.botica.tasks.Logout;
import com.alquimista.botica.tasks.NavigateTo;
import com.alquimista.botica.ui.CatalogPage;
import com.alquimista.botica.ui.Header;
import com.alquimista.botica.ui.LoginPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {

    @Dado("que {actor} está en la página de ingreso de la botica")
    public void isOnTheLoginPage(Actor actor) {
        actor.attemptsTo(NavigateTo.theLoginPage());
    }

    @Dado("que {actor} inició sesión en la botica")
    public void isLoggedIn(Actor actor) {
        actor.attemptsTo(
                Login.asThePracticeUser(),
                Ensure.that(CatalogPage.TITLE).isDisplayed()
        );
    }

    @Dado("que {actor} no ha iniciado sesión en la botica")
    public void isNotLoggedIn(Actor actor) {
        actor.attemptsTo(
                NavigateTo.theLoginPage(),
                Ensure.that(LoginPage.LOGIN_BUTTON).isDisplayed()
        );
    }

    @Cuando("{actor} inicia sesión con el usuario {string} y la contraseña {string}")
    public void logsIn(Actor actor, String user, String password) {
        actor.attemptsTo(Login.withCredentials(user, password));
    }

    @Cuando("{actor} intenta entrar directamente a la página {string}")
    public void opensPageDirectly(Actor actor, String path) {
        actor.attemptsTo(NavigateTo.thePage(path));
    }

    @Cuando("{actor} cierra su sesión")
    public void logsOut(Actor actor) {
        actor.attemptsTo(Logout.now());
    }

    @Entonces("{actor} debería ver el catálogo de la botica")
    public void shouldSeeTheCatalog(Actor actor) {
        actor.attemptsTo(
                Ensure.that(CatalogPage.TITLE).text().isEqualTo("Catálogo"),
                Ensure.that(TheCurrentPage.url()).contains("catalogo.html")
        );
    }

    @Y("debería ver el saludo para el usuario {string}")
    public void shouldSeeTheGreeting(String user) {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(Header.GREETING_USER).text().isEqualTo(user)
        );
    }

    @Entonces("{actor} debería ver el error de ingreso {string}")
    public void shouldSeeTheLoginError(Actor actor, String message) {
        actor.attemptsTo(
                Ensure.that(LoginPage.ERROR_MESSAGE).isDisplayed(),
                Ensure.that(LoginPage.ERROR_MESSAGE).text().isEqualTo(message)
        );
    }

    @Y("debería seguir en la página de ingreso")
    public void shouldStayOnTheLoginPage() {
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(LoginPage.LOGIN_BUTTON).isDisplayed(),
                Ensure.that(TheCurrentPage.url()).doesNotContain("catalogo.html")
        );
    }

    @Entonces("{actor} debería ser redirigido a la página de ingreso")
    public void shouldBeRedirectedToLogin(Actor actor) {
        actor.attemptsTo(
                Ensure.that(LoginPage.LOGIN_BUTTON).isDisplayed(),
                Ensure.that(TheCurrentPage.url()).endsWith("index.html")
        );
    }

    @Y("no debería poder volver al catálogo sin iniciar sesión")
    public void cannotGoBackToTheCatalog() {
        Actor actor = theActorInTheSpotlight();
        actor.attemptsTo(NavigateTo.thePage("catalogo.html"));
        shouldBeRedirectedToLogin(actor);
    }
}

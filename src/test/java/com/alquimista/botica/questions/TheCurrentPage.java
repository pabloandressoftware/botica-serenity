package com.alquimista.botica.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class TheCurrentPage {

    public static Question<String> url() {
        return Question.about("la dirección de la página actual")
                .answeredBy(actor -> BrowseTheWeb.as(actor).getDriver().getCurrentUrl());
    }

    private TheCurrentPage() {
    }
}

package com.alquimista.botica.tasks;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import static com.alquimista.botica.ui.CatalogPage.CATEGORY;
import static com.alquimista.botica.ui.CatalogPage.SEARCH_BOX;

public class SearchCatalog {

    public static Performable byName(String text) {
        return Task.where("{0} busca productos que contengan '" + text + "'",
                Enter.theValue(text).into(SEARCH_BOX)
        );
    }

    public static Performable byCategory(String category) {
        return Task.where("{0} filtra el catálogo por la categoría " + category,
                SelectFromOptions.byVisibleText(category).from(CATEGORY)
        );
    }

    private SearchCatalog() {
    }
}

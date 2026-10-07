package com.alquimista.botica.questions;

import com.alquimista.botica.ui.CatalogPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

public class TheCatalog {

    public static Question<List<String>> visibleProducts() {
        return Question.about("los productos visibles en el catálogo")
                .answeredBy(actor -> Text.ofEach(CatalogPage.PRODUCT_NAMES).answeredBy(actor)
                        .stream().map(String::trim).toList());
    }

    private TheCatalog() {
    }
}

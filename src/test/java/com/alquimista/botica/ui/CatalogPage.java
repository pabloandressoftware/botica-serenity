package com.alquimista.botica.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CatalogPage {

    public static final String PATH = "/catalogo.html";

    public static final Target TITLE = Target.the("título del catálogo").locatedBy("main.contenido h1");
    public static final Target SEARCH_BOX = Target.the("buscador por nombre").locatedBy("#buscar");
    public static final Target CATEGORY = Target.the("filtro de categoría").locatedBy("[name='categoria']");
    public static final Target PRODUCT_NAMES = Target.the("nombres de los productos").locatedBy(".grilla-productos .nombre-producto");
    public static final Target MESSAGE = Target.the("mensaje del catálogo").locatedBy("[data-testid='mensaje-catalogo']");
    public static final Target NO_RESULTS = Target.the("aviso sin resultados").locatedBy(".sin-resultados");

    // Localizadores dinámicos: la tarjeta se ubica por el nombre visible del producto
    public static final Target QUANTITY_OF = Target.the("cantidad de {0}")
            .locatedBy("//article[h3[normalize-space()='{0}']]//input[contains(@class,'cantidad')]");
    public static final Target ADD_BUTTON_OF = Target.the("botón agregar de {0}")
            .locatedBy("//article[h3[normalize-space()='{0}']]//button[contains(@class,'btn-agregar')]");
    public static final Target PRODUCT_CARD_OF = Target.the("tarjeta de {0}")
            .locatedBy("//article[contains(@class,'tarjeta-producto') and h3[normalize-space()='{0}']]");

    private CatalogPage() {
    }
}

package com.alquimista.botica.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {

    public static final String PATH = "/carrito.html";

    public static final Target MESSAGE = Target.the("mensaje del carrito").locatedBy("[data-testid='mensaje-carrito']");
    public static final Target EMPTY_CART = Target.the("aviso de carrito vacío").locatedBy("[data-testid='carrito-vacio']");
    public static final Target COUPON_FIELD = Target.the("campo cupón").locatedBy("[name='cupon']");
    public static final Target APPLY_COUPON_BUTTON = Target.the("botón aplicar cupón").locatedBy("#btn-aplicar-cupon");
    public static final Target COUPON_MESSAGE = Target.the("mensaje del cupón").locatedBy("#mensaje-cupon");
    public static final Target DISCOUNT_ROW = Target.the("fila de descuento").locatedBy(".panel-resumen .fila-descuento");
    public static final Target DISCOUNT = Target.the("valor del descuento").locatedBy("[data-testid='descuento']");
    public static final Target PARTIAL_TOTAL = Target.the("total parcial").locatedBy(".total-parcial");
    public static final Target CHECKOUT_BUTTON = Target.the("botón ir a pagar").locatedBy(".btn-pagar");

    public static final Target QUANTITY_OF = Target.the("cantidad de {0} en el carrito")
            .locatedBy("css:input.cantidad-carrito[aria-label='Cantidad de {0}']");
    public static final Target UPDATE_BUTTON_OF = Target.the("botón actualizar de {0}")
            .locatedBy("//tr[.//input[@aria-label='Cantidad de {0}']]//button[contains(@class,'btn-actualizar')]");
    public static final Target REMOVE_BUTTON_OF = Target.the("botón eliminar {0}")
            .locatedBy("css:button.btn-eliminar[aria-label='Eliminar {0}']");

    private CartPage() {
    }
}

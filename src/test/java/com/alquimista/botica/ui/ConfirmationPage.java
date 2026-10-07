package com.alquimista.botica.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ConfirmationPage {

    public static final Target TITLE = Target.the("título de confirmación").locatedBy(".tarjeta-confirmacion h1");
    public static final Target ORDER_NUMBER = Target.the("número de pedido").locatedBy("[data-testid='numero-pedido']");
    public static final Target SHIPPING = Target.the("valor del envío pagado").locatedBy("#conf-envio");
    public static final Target TOTAL_PAID = Target.the("total pagado").locatedBy("#conf-total");

    private ConfirmationPage() {
    }
}

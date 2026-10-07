package com.alquimista.botica.ui;

import net.serenitybdd.screenplay.targets.Target;

/** Capa "caldero" que la botica muestra mientras simula una espera del servidor. */
public class LoadingOverlay {

    public static final Target SPINNER = Target.the("indicador de carga").locatedBy("#capa-cargando");

    private LoadingOverlay() {
    }
}

package com.alquimista.botica.models;

/** Datos del formulario de envío del checkout. */
public record ShippingData(String fullName, String email, String address, String city, String phone) {

    public static ShippingData valid() {
        return new ShippingData("Merlín Ambrosius", "merlin@camelot.co", "Calle 5 # 38-25", "Cali", "3001234567");
    }

    /** Devuelve una copia con un campo del formulario cambiado (nombre, correo, direccion, ciudad o telefono). */
    public ShippingData with(String field, String value) {
        return switch (field) {
            case "nombre" -> new ShippingData(value, email, address, city, phone);
            case "correo" -> new ShippingData(fullName, value, address, city, phone);
            case "direccion" -> new ShippingData(fullName, email, value, city, phone);
            case "ciudad" -> new ShippingData(fullName, email, address, value, phone);
            case "telefono" -> new ShippingData(fullName, email, address, city, value);
            default -> throw new IllegalArgumentException("Campo de envío desconocido: " + field);
        };
    }
}

# language: es
@botica @cupones
Característica: HU-04 Cupones de descuento
  Como cliente de La Botica del Alquimista
  Quiero aplicar un cupón de descuento a mi compra
  Para pagar menos cuando cumplo las condiciones de la promoción

  Reglas: LUNA10 da 10% sin mínimo; ELIXIR20 da 20% con una compra mínima de $100.000;
  solo se permite un cupón por compra.

  Antecedentes:
    Dado que Merlín inició sesión en la botica

  @HU-04 @GH-12 @positivo
  Esquema del escenario: Aplicar el cupón <cupon> a una compra que cumple sus condiciones
    Dado que Merlín ya tiene <cantidad> unidades de "<producto>" en el carrito
    Cuando Merlín aplica el cupón "<cupon>"
    Entonces Merlín debería ver el mensaje de cupón "Cupón <cupon> aplicado correctamente"
    Y el carrito debería mostrar un descuento de "<descuento>" y un total parcial de "<total>"

    Ejemplos:
      | cupon    | producto              | cantidad | descuento | total   |
      | LUNA10   | Elixir de Vitalidad   | 2        | -$8.400   | $75.600 |
      | ELIXIR20 | Amuleto de Protección | 3        | -$21.000  | $84.000 |

  @HU-04 @GH-13 @negativo
  Esquema del escenario: Rechazo de un cupón (<caso>)
    Dado que Merlín ya tiene 2 unidades de "Elixir de Vitalidad" en el carrito
    Cuando Merlín aplica el cupón "<cupon>"
    Entonces Merlín debería ver el mensaje de cupón "<mensaje>"
    Y el carrito no debería mostrar ningún descuento

    Ejemplos:
      | caso                     | cupon    | mensaje                                           |
      | código inexistente       | MAGIA50  | Cupón no válido                                   |
      | no alcanza compra mínima | ELIXIR20 | Este cupón requiere una compra mínima de $100.000 |
      | código vacío             |          | Ingresa un código de cupón                        |

  @HU-04 @GH-14 @negativo
  Escenario: El cupón se retira si la compra deja de cumplir el mínimo
    Dado que Merlín ya tiene 3 unidades de "Amuleto de Protección" en el carrito
    Y aplicó el cupón "ELIXIR20"
    Cuando Merlín cambia la cantidad de "Amuleto de Protección" a 2 unidades
    Entonces Merlín debería ver el mensaje del carrito "El cupón ELIXIR20 fue retirado porque tu compra ya no alcanza el mínimo de $100.000."
    Y el carrito no debería mostrar ningún descuento

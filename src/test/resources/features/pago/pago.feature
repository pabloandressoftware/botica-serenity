# language: es
@botica @pago
Característica: HU-05 Pago y confirmación del pedido
  Como cliente de La Botica del Alquimista
  Quiero ingresar mis datos de envío y confirmar el pago
  Para recibir mis productos en casa

  Reglas: IVA del 19% sobre el subtotal; envío normal $8.000 y gratis cuando la compra
  (ya con descuento) llega a $150.000; envío express $15.000 siempre.

  Antecedentes:
    Dado que Merlín inició sesión en la botica

  @HU-05 @GH-15 @smoke @positivo
  Esquema del escenario: Confirmar un pedido con envío <envio> (<caso>)
    Dado que Merlín ya tiene <cantidad> unidades de "<producto>" en el carrito
    Y aplicó el cupón "<cupon>"
    Cuando Merlín confirma el pedido con envío "<envio>" y datos de envío válidos
    Entonces Merlín debería ver la confirmación de su pedido
    Y el pedido debería registrar un envío de "<valor_envio>" y un total pagado de "<total>"

    Ejemplos:
      | caso                                  | producto              | cantidad | cupon    | envio   | valor_envio | total    |
      | envío normal cobrado                  | Elixir de Vitalidad   | 2        | ninguno  | normal  | $8.000      | $107.960 |
      | envío express                         | Elixir de Vitalidad   | 2        | ninguno  | express | $15.000     | $114.960 |
      | envío normal gratis desde $150.000    | Amuleto de Protección | 5        | ninguno  | normal  | Gratis      | $208.250 |
      | cupón que mantiene el envío gratis    | Amuleto de Protección | 5        | LUNA10   | normal  | Gratis      | $190.750 |
      | cupón que hace perder el envío gratis | Amuleto de Protección | 5        | ELIXIR20 | normal  | $8.000      | $181.250 |

  @HU-05 @GH-16 @negativo
  Esquema del escenario: El pedido no se confirma con datos de envío inválidos (<campo> <caso>)
    Dado que Merlín ya tiene 1 unidades de "Polvo de Luna" en el carrito
    Cuando Merlín intenta confirmar el pedido con el campo "<campo>" con valor "<valor>"
    Entonces Merlín debería ver el aviso de pago "Revisa los campos marcados"
    Y el campo "<campo>" debería indicar el error "<error>"

    Ejemplos:
      | campo     | caso          | valor          | error                             |
      | nombre    | vacío         |                | Este campo es obligatorio         |
      | correo    | sin dominio   | merlin.correo  | Ingresa un correo válido          |
      | telefono  | con 7 dígitos | 3001234        | El teléfono debe tener 10 dígitos |
      | direccion | vacía         |                | Este campo es obligatorio         |

# language: es
@botica @carrito
Característica: HU-03 Gestión del carrito de compras
  Como cliente de La Botica del Alquimista
  Quiero agregar y quitar productos de mi carrito respetando el stock y los límites de compra
  Para preparar mi pedido antes de pagarlo

  Reglas: máximo 5 unidades por producto, nunca más de las disponibles en stock,
  y los productos agotados no se pueden agregar.

  Antecedentes:
    Dado que Merlín inició sesión en la botica

  @HU-03 @GH-8 @smoke @positivo
  Escenario: Agregar un producto disponible al carrito
    Cuando Merlín agrega 3 unidades de "Raíz de Mandrágora" al carrito
    Entonces Merlín debería ver el mensaje del catálogo "Se agregó Raíz de Mandrágora al carrito"
    Y el contador del carrito debería mostrar 3 unidades

  @HU-03 @GH-9 @negativo
  Esquema del escenario: Rechazo de cantidades no permitidas (<regla>)
    Dado que Merlín ya tiene <en_carrito> unidades de "<producto>" en el carrito
    Cuando Merlín agrega <cantidad> unidades de "<producto>" al carrito
    Entonces Merlín debería ver el mensaje del catálogo "<mensaje>"
    Y el contador del carrito debería mostrar <en_carrito> unidades

    Ejemplos:
      | regla                          | producto            | en_carrito | cantidad | mensaje                                             |
      | cantidad mayor a 5             | Polvo de Luna       | 0          | 6        | La cantidad debe estar entre 1 y 5                  |
      | cantidad cero                  | Polvo de Luna       | 0          | 0        | La cantidad debe estar entre 1 y 5                  |
      | supera 5 unidades por producto | Raíz de Mandrágora  | 4          | 2        | Solo puedes llevar hasta 5 unidades de este producto |
      | supera el stock disponible     | Pluma de Fénix      | 0          | 2        | No hay suficiente stock disponible                  |

  @HU-03 @GH-10 @negativo
  Escenario: Un producto agotado no se puede agregar al carrito
    Entonces Merlín debería ver el producto "Colgante Lunar" marcado como agotado
    Y no debería poder agregar "Colgante Lunar" al carrito

  @HU-03 @GH-11 @positivo
  Escenario: Eliminar el único producto del carrito lo deja vacío
    Dado que Merlín ya tiene 2 unidades de "Elixir de Vitalidad" en el carrito
    Cuando Merlín elimina "Elixir de Vitalidad" de su carrito
    Entonces Merlín debería ver el mensaje del carrito "Se eliminó Elixir de Vitalidad del carrito"
    Y debería ver que su carrito está vacío

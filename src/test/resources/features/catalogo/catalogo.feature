# language: es
@botica @catalogo
Característica: HU-02 Consulta del catálogo de productos
  Como cliente de La Botica del Alquimista
  Quiero buscar productos por nombre y filtrarlos por categoría
  Para encontrar rápidamente las pociones, ingredientes y amuletos que necesito

  Antecedentes:
    Dado que Merlín inició sesión en la botica

  @HU-02 @GH-5 @positivo
  Esquema del escenario: Búsqueda de productos por nombre "<texto>"
    Cuando Merlín busca productos que contengan "<texto>"
    Entonces Merlín debería ver solo los productos <productos>

    Ejemplos:
      | texto  | productos                                                                  |
      | luna   | "Polvo de Luna, Colgante Lunar"                                            |
      | POCIÓN | "Poción de Sueño Profundo, Poción de Invisibilidad"                        |
      | fénix  | "Pluma de Fénix"                                                           |

  @HU-02 @GH-6 @positivo
  Esquema del escenario: Filtro del catálogo por la categoría <categoria>
    Cuando Merlín filtra el catálogo por la categoría "<categoria>"
    Entonces Merlín debería ver solo los productos <productos>

    Ejemplos:
      | categoria    | productos                                                                  |
      | Pociones     | "Poción de Sueño Profundo, Elixir de Vitalidad, Poción de Invisibilidad"  |
      | Ingredientes | "Raíz de Mandrágora, Polvo de Luna, Pluma de Fénix"                        |
      | Amuletos     | "Amuleto de Protección, Anillo de la Suerte, Colgante Lunar"               |

  @HU-02 @GH-7 @negativo
  Escenario: Búsqueda sin coincidencias en la categoría seleccionada
    Cuando Merlín filtra el catálogo por la categoría "Amuletos"
    Y busca productos que contengan "poción"
    Entonces Merlín debería ver el aviso "No se encontraron productos."
    Y no debería ver ningún producto en el catálogo

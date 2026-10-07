# botica-serenity — Automatización de pruebas Front-End con Serenity BDD

Taller **Automatización de Pruebas Front-End con Serenity BDD** (corte 2).
Autor: Pablo Guzmán Alarcón — A00399523

Aplicación bajo prueba: **La Botica del Alquimista**, en https://boticaalquimista.onrender.com
(usuario de práctica `alquimista` / `pocion123`).

**16 escenarios** (8 positivos y 8 negativos), que suman **37 ejecuciones** contando los `Ejemplos`. Cada escenario está vinculado a su Issue.

## Stack

| | |
|---|---|
| Lenguaje / build | Java 21 · Gradle (Groovy DSL) con wrapper |
| Framework | Serenity BDD **5.3.11** + Cucumber 7 (Gherkin en español) |
| Patrón | **Screenplay** en estilo fluido (`Task.where`, `Question.about`, `Ensure`) |
| Runner | Suite **JUnit 5** (`@Suite` + `@IncludeEngines("cucumber")`) con `SerenityReporterParallel` |
| Navegador | Chrome headless (Selenium Manager descarga el driver) |

## Estructura

```
src/test/java/com/alquimista/botica/
├── runners/CucumberTestSuite.java    → suite JUnit 5 que ejecuta Cucumber con el reporter de Serenity
├── hooks/ScreenplayHooks.java        → OnlineCast (un navegador por actor) y parámetro {actor}
├── stepdefinitions/                  → traducen cada paso Gherkin a llamadas del actor (sin selectores)
├── tasks/                            → metas de negocio: Login, SearchCatalog, AddToCart, ManageCart, ApplyCoupon, Checkout...
├── interactions/WaitForTheBotica     → única interacción propia: espera a que termine el "caldero" de carga
├── questions/                        → TheCatalog.visibleProducts(), TheCurrentPage.url()
├── ui/                               → Targets por página (LoginPage, CatalogPage, CartPage, CheckoutPage...)
└── models/ShippingData.java          → datos de envío (record)
src/test/resources/
├── features/<historia>/*.feature     → Gherkin, una carpeta por historia de usuario
├── serenity.conf                     → Serenity + WebDriver + URL base por entorno
└── junit-platform.properties
```

Flujo: **Feature → Step Definition → Actor → Task / Question → Target**.
Por ejemplo, `Cuando Merlín aplica el cupón "LUNA10"` → `CouponStepDefinitions.appliesTheCoupon` →
`actor.attemptsTo(ApplyCoupon.withCode("LUNA10"))` → `Enter.theValue(...).into(CartPage.COUPON_FIELD)`, `Click.on(CartPage.APPLY_COUPON_BUTTON)`.

## Historias de usuario (requisitos)

| Requisito | Historia | Reglas cubiertas |
|---|---|---|
| **HU-01** | Inicio y cierre de sesión | credenciales válidas e inválidas, páginas protegidas sin sesión, cierre de sesión |
| **HU-02** | Consulta del catálogo | búsqueda por nombre (sin distinguir mayúsculas), filtro por categoría, sin resultados |
| **HU-03** | Gestión del carrito | cantidad entre 1 y 5, máximo 5 por producto, stock disponible, producto agotado, eliminar |
| **HU-04** | Cupones de descuento | LUNA10 (10 %), ELIXIR20 (20 % con mínimo $100.000), cupón inválido o vacío, retiro automático del cupón |
| **HU-05** | Pago y confirmación | IVA 19 %, envío normal $8.000 / gratis desde $150.000 (con descuento), express $15.000, validación de datos de envío |

## Tags

| Tag | Significado |
|---|---|
| `@botica` | Toda la suite (lo que corre el pipeline por defecto) |
| `@autenticacion` `@catalogo` `@carrito` `@cupones` `@pago` | Funcionalidad (feature) |
| `@HU-0n` | **Requisito** (historia de usuario) al que pertenece el escenario |
| `@GH-n` | **Issue #n** de GitHub que representa el caso de prueba |
| `@smoke` / `@positivo` / `@negativo` | Tipo de prueba |

## Matriz de trazabilidad (requisito ↔ issue ↔ escenario)

| Requisito | Issue | Tag | Escenario | Tipo | Gherkin |
|---|---|---|---|---|---|
| `HU-01` | [#1](https://github.com/pabloandressoftware/botica-serenity/issues/1) | `@GH-1` | Inicio de sesión con credenciales válidas | Positivo | Escenario |
| `HU-01` | [#2](https://github.com/pabloandressoftware/botica-serenity/issues/2) | `@GH-2` | Inicio de sesión rechazado por credenciales incorrectas (<caso>) | Negativo | Esquema del escenario |
| `HU-01` | [#3](https://github.com/pabloandressoftware/botica-serenity/issues/3) | `@GH-3` | Acceso a la página de <pagina> sin haber iniciado sesión | Negativo | Esquema del escenario |
| `HU-01` | [#4](https://github.com/pabloandressoftware/botica-serenity/issues/4) | `@GH-4` | Cierre de sesión | Positivo | Escenario |
| `HU-02` | [#5](https://github.com/pabloandressoftware/botica-serenity/issues/5) | `@GH-5` | Búsqueda de productos por nombre "<texto>" | Positivo | Esquema del escenario |
| `HU-02` | [#6](https://github.com/pabloandressoftware/botica-serenity/issues/6) | `@GH-6` | Filtro del catálogo por la categoría <categoria> | Positivo | Esquema del escenario |
| `HU-02` | [#7](https://github.com/pabloandressoftware/botica-serenity/issues/7) | `@GH-7` | Búsqueda sin coincidencias en la categoría seleccionada | Negativo | Escenario |
| `HU-03` | [#8](https://github.com/pabloandressoftware/botica-serenity/issues/8) | `@GH-8` | Agregar un producto disponible al carrito | Positivo | Escenario |
| `HU-03` | [#9](https://github.com/pabloandressoftware/botica-serenity/issues/9) | `@GH-9` | Rechazo de cantidades no permitidas (<regla>) | Negativo | Esquema del escenario |
| `HU-03` | [#10](https://github.com/pabloandressoftware/botica-serenity/issues/10) | `@GH-10` | Un producto agotado no se puede agregar al carrito | Negativo | Escenario |
| `HU-03` | [#11](https://github.com/pabloandressoftware/botica-serenity/issues/11) | `@GH-11` | Eliminar el único producto del carrito lo deja vacío | Positivo | Escenario |
| `HU-04` | [#12](https://github.com/pabloandressoftware/botica-serenity/issues/12) | `@GH-12` | Aplicar el cupón <cupon> a una compra que cumple sus condiciones | Positivo | Esquema del escenario |
| `HU-04` | [#13](https://github.com/pabloandressoftware/botica-serenity/issues/13) | `@GH-13` | Rechazo de un cupón (<caso>) | Negativo | Esquema del escenario |
| `HU-04` | [#14](https://github.com/pabloandressoftware/botica-serenity/issues/14) | `@GH-14` | El cupón se retira si la compra deja de cumplir el mínimo | Negativo | Escenario |
| `HU-05` | [#15](https://github.com/pabloandressoftware/botica-serenity/issues/15) | `@GH-15` | Confirmar un pedido con envío <envio> (<caso>) | Positivo | Esquema del escenario |
| `HU-05` | [#16](https://github.com/pabloandressoftware/botica-serenity/issues/16) | `@GH-16` | El pedido no se confirma con datos de envío inválidos (<campo> <caso>) | Negativo | Esquema del escenario |

## Ejecución

```bash
./gradlew clean test                                          # toda la suite + reporte
./gradlew clean test -Dcucumber.filter.tags="@GH-15"          # un caso de prueba (issue #15)
./gradlew clean test -Dcucumber.filter.tags="@HU-04"          # un requisito
./gradlew clean test -Dcucumber.filter.tags="@HU-05 and @negativo"
```

El reporte de Serenity (documentación viva) queda en `target/site/serenity/index.html`, y el JSON de Cucumber en `target/cucumber-reports/cucumber.json`.

### Desde GitHub Actions / el TestCase (Issue)
1. Ve a *Actions* → **CI** → *Run workflow* y escribe en `testTag` el tag del issue, por ejemplo `@GH-12`. Si lo dejas vacío, corre toda la suite. Un `push` a `main` también ejecuta toda la suite.
2. El pipeline ejecuta Serenity y publica dos artefactos: `serenity-report` (el reporte HTML) y `test-results`. Luego, con `actions/github-script`, lee `cucumber.json` y, por cada escenario con `@GH-n`, comenta en el Issue #n **PASSED/FAILED** con el enlace a la ejecución. También pone el label `automated-passed` o `automated-failed` (+ `bug`).

## Decisiones de diseño

- **Localizadores** (`ui/`), en el orden de preferencia de la guía: `id` → `name` / `data-testid` → CSS → XPath relativo.
  - Los productos se ubican por su nombre visible con *Targets* parametrizados, por ejemplo
    `//article[h3[normalize-space()='{0}']]//button[contains(@class,'btn-agregar')]` con `ADD_BUTTON_OF.of("Polvo de Luna")`.
  - En los Targets parametrizados que son CSS con corchetes se usa el prefijo `css:`, porque si no Serenity los interpreta como XPath.
- **Esperas:** las interacciones sobre `Target` ya esperan solas. La única espera explícita es `WaitForTheBotica`: la app muestra un caldero de carga durante 0,3 a 1,6 s simulando un servidor. Se espera a que ese caldero esté oculto o a que la página haya navegado. `WaitUntil ... isNotVisible()` resultó lento y poco fiable con este overlay. Sin `Thread.sleep`.
- **Independencia:** la app guarda todo en el `localStorage` del navegador y `OnlineCast` abre un navegador nuevo por escenario. Por eso cada escenario parte de datos limpios, a diferencia de la API compartida del taller de Karate.
- **Requisitos:** el enunciado menciona historias de usuario adjuntas que no estaban disponibles. Las HU-01 a HU-05 se derivaron del comportamiento y de los mensajes de la aplicación.

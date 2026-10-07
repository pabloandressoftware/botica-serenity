# language: es
@botica @autenticacion
Característica: HU-01 Inicio y cierre de sesión en la botica
  Como cliente de La Botica del Alquimista
  Quiero ingresar con mi usuario y contraseña
  Para que solo yo pueda ver el catálogo y hacer pedidos

  @HU-01 @GH-1 @smoke @positivo
  Escenario: Inicio de sesión con credenciales válidas
    Dado que Merlín está en la página de ingreso de la botica
    Cuando Merlín inicia sesión con el usuario "alquimista" y la contraseña "pocion123"
    Entonces Merlín debería ver el catálogo de la botica
    Y debería ver el saludo para el usuario "alquimista"

  @HU-01 @GH-2 @negativo
  Esquema del escenario: Inicio de sesión rechazado por credenciales incorrectas (<caso>)
    Dado que Merlín está en la página de ingreso de la botica
    Cuando Merlín inicia sesión con el usuario "<usuario>" y la contraseña "<clave>"
    Entonces Merlín debería ver el error de ingreso "Usuario o contraseña incorrectos"
    Y debería seguir en la página de ingreso

    Ejemplos:
      | caso                  | usuario    | clave      |
      | contraseña incorrecta | alquimista | pocion999  |
      | usuario inexistente   | hechicero  | pocion123  |
      | campos vacíos         |            |            |

  @HU-01 @GH-3 @negativo
  Esquema del escenario: Acceso a la página de <pagina> sin haber iniciado sesión
    Dado que Merlín no ha iniciado sesión en la botica
    Cuando Merlín intenta entrar directamente a la página "<ruta>"
    Entonces Merlín debería ser redirigido a la página de ingreso

    Ejemplos:
      | pagina   | ruta           |
      | catálogo | catalogo.html  |
      | carrito  | carrito.html   |
      | pago     | checkout.html  |

  @HU-01 @GH-4 @positivo
  Escenario: Cierre de sesión
    Dado que Merlín inició sesión en la botica
    Cuando Merlín cierra su sesión
    Entonces Merlín debería ser redirigido a la página de ingreso
    Y no debería poder volver al catálogo sin iniciar sesión

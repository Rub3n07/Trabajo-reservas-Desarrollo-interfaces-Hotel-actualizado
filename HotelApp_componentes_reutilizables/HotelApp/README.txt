HOTELAPP - REUTILIZACIÓN DE COMPONENTES

CAMBIO PRINCIPAL
- HabitacionCard.java es un único componente reutilizable.
- HotelController crea las tarjetas mediante crearTarjeta(Element).
- En Inicio se muestran las tres primeras habitaciones como destacadas.
- En Reservas se muestran todas las habitaciones usando la misma clase HabitacionCard.
- El botón "Ver detalles" utiliza la misma acción para mostrar los datos de la habitación.
- Se conserva la tabla, la búsqueda por número y el XML original.

JAVA / JAVAFX
La configuración de VS Code utiliza JavaFX 27. Si tu SDK está en otra carpeta,
cambia la ruta en .vscode/launch.json y .vscode/settings.json.

ABRIR
Abre la carpeta HotelApp (la carpeta que contiene .vscode, src y este README)
en VS Code. Ejecuta "Ejecutar HotelApp (JavaFX 27)" desde Run and Debug.

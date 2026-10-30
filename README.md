# HotelApp - Gestión de habitaciones

HotelApp es una aplicación de escritorio desarrollada en Java y JavaFX que permite consultar y gestionar la información de las habitaciones de un hotel.

El objetivo del proyecto es crear una aplicación sencilla y visual en la que se pueda consultar el estado de las habitaciones, buscar habitaciones por número y acceder a sus detalles.

## Funcionalidades

- Visualización de las habitaciones del hotel.
- Apartado de inicio con habitaciones destacadas.
- Listado de habitaciones en el apartado de reservas.
- Búsqueda por número de habitación.
- Consulta de precios, tipos y disponibilidad.
- Visualización de los detalles de cada habitación.
- Reutilización de tarjetas en diferentes pantallas.

## Tecnologías utilizadas

- **Java:** desarrollo de la lógica de la aplicación.
- **JavaFX 27:** creación de la interfaz gráfica.
- **FXML:** organización de las pantallas.
- **CSS:** personalización del diseño y los colores.
- **XML:** almacenamiento de los datos de las habitaciones.
- **Visual Studio Code:** entorno de desarrollo.

## Estructura del proyecto

- `Main.java`: inicia la aplicación.
- `HotelController.java`: controla las funciones y la interfaz.
- `HabitacionCard.java`: crea las tarjetas reutilizables de las habitaciones.
- `vista.fxml`: contiene la estructura de la interfaz.
- `estilos.css`: define los estilos visuales.
- `reservas.xml`: almacena los datos del hotel.

## Reutilización de componentes

Una de las partes importantes del proyecto es la clase `HabitacionCard.java`, que permite utilizar las mismas tarjetas de habitaciones en diferentes apartados de la aplicación.

De esta forma se evita repetir código y es más fácil modificar el diseño o la información que aparece en las tarjetas.

## Instalación y ejecución

Para ejecutar el proyecto es necesario tener instalado Java, JavaFX 27 y Visual Studio Code con las extensiones de Java.

También es necesario configurar correctamente la ruta de JavaFX para que la aplicación pueda iniciarse.

## Objetivo del proyecto

Este proyecto me ha permitido practicar la programación en Java, trabajar con interfaces gráficas y aprender a organizar mejor el código mediante controladores y componentes reutilizables.

También me ha servido para conocer mejor el funcionamiento de JavaFX y la utilización de archivos XML para almacenar información.

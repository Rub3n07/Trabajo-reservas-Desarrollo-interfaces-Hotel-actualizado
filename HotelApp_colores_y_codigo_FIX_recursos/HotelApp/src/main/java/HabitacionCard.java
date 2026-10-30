import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Tarjeta visual compartida por las pantallas de inicio y reservas.
 * Los datos y la acción se reciben desde la vista que crea la tarjeta.
 */
public class HabitacionCard extends VBox {

    private static final double ANCHO_TARJETA = 245;

    public HabitacionCard(String numero, String tipo, String precio,
                          String estado, Runnable accionDetalles) {
        getStyleClass().add("tarjeta-habitacion");
        setSpacing(9);
        setPadding(new Insets(12));
        setPrefWidth(ANCHO_TARJETA);
        setMaxWidth(ANCHO_TARJETA);

        Label titulo = new Label("Habitación " + numero);
        titulo.getStyleClass().add("titulo-tarjeta");

        HBox contenido = new HBox(10);
        contenido.setAlignment(Pos.CENTER_LEFT);

        Label imagen = new Label("HABITACIÓN");
        imagen.getStyleClass().add("imagen-habitacion");
        imagen.setMinSize(112, 78);
        imagen.setPrefSize(112, 78);
        imagen.setAlignment(Pos.CENTER);
        imagen.setAccessibleText("Imagen ilustrativa de la habitación " + numero);

        VBox datos = new VBox(5);
        Label numeroLabel = new Label("N.º " + numero);
        Label tipoLabel = new Label(tipo);
        Label precioLabel = new Label(precio + " € / noche");
        precioLabel.getStyleClass().add("precio-habitacion");

        String estadoNormalizado = normalizarEstado(estado);
        Label estadoLabel = new Label(textoEstado(estadoNormalizado));
        estadoLabel.getStyleClass().addAll("estado-habitacion", claseEstado(estadoNormalizado));
        datos.getChildren().addAll(numeroLabel, tipoLabel, precioLabel, estadoLabel);
        contenido.getChildren().addAll(imagen, datos);

        Button detalles = new Button("Ver detalles");
        detalles.getStyleClass().add("principal");
        detalles.setMaxWidth(Double.MAX_VALUE);
        detalles.setOnAction(evento -> {
            if (accionDetalles != null) {
                accionDetalles.run();
            }
        });

        getChildren().addAll(titulo, contenido, detalles);
    }

    private static String normalizarEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            return "desconocido";
        }
        String valor = estado.trim().toLowerCase();
        return "libre".equals(valor) ? "disponible" : valor;
    }

    private static String textoEstado(String estado) {
        if ("disponible".equals(estado)) return "● Disponible";
        if ("mantenimiento".equals(estado)) return "● Mantenimiento";
        if ("desconocido".equals(estado)) return "● Sin información";
        return "● " + estado.substring(0, 1).toUpperCase() + estado.substring(1);
    }

    private static String claseEstado(String estado) {
        if ("disponible".equals(estado)) return "estado-disponible";
        if ("mantenimiento".equals(estado)) return "estado-mantenimiento";
        return "estado-ocupada";
    }
}

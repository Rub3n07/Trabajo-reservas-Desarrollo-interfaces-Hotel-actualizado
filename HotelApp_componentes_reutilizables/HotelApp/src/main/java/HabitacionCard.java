import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Tarjeta reutilizable de habitación.
 * La vista que la utiliza decide los datos y qué ocurre al pulsar "Ver detalles".
 */
public class HabitacionCard extends VBox {

    public HabitacionCard(String numero, String tipo, String precio,
                          String estado, Runnable accionDetalles) {
        getStyleClass().add("tarjeta-habitacion");
        setSpacing(9);
        setPadding(new Insets(12));
        setPrefWidth(245);
        setMaxWidth(245);

        HBox contenido = new HBox(10);
        contenido.setAlignment(Pos.CENTER_LEFT);

        Label imagen = new Label("HABITACIÓN");
        imagen.getStyleClass().add("imagen-habitacion");
        imagen.setMinSize(112, 78);
        imagen.setPrefSize(112, 78);
        imagen.setAlignment(Pos.CENTER);

        VBox datos = new VBox(5);
        Label precioLabel = new Label(precio + " € / noche");
        precioLabel.getStyleClass().add("precio-habitacion");
        Label estadoLabel = new Label(estadoVisible(estado));
        estadoLabel.getStyleClass().addAll("estado-habitacion", claseEstado(estado));
        datos.getChildren().addAll(
                new Label("N.º " + numero),
                new Label(tipo),
                precioLabel,
                estadoLabel
        );
        contenido.getChildren().addAll(imagen, datos);

        Label titulo = new Label("Habitación " + numero);
        titulo.getStyleClass().add("titulo-tarjeta");

        Button detalles = new Button("Ver detalles");
        detalles.getStyleClass().add("principal");
        detalles.setMaxWidth(Double.MAX_VALUE);
        detalles.setOnAction(e -> {
            if (accionDetalles != null) {
                accionDetalles.run();
            }
        });

        getChildren().addAll(titulo, contenido, detalles);
    }

    private String estadoVisible(String estado) {
        if ("libre".equalsIgnoreCase(estado) || "disponible".equalsIgnoreCase(estado)) {
            return "● Disponible";
        }
        if ("mantenimiento".equalsIgnoreCase(estado)) {
            return "● Mantenimiento";
        }
        return "● " + estado;
    }

    private String claseEstado(String estado) {
        if ("libre".equalsIgnoreCase(estado) || "disponible".equalsIgnoreCase(estado)) {
            return "estado-disponible";
        }
        if ("mantenimiento".equalsIgnoreCase(estado)) {
            return "estado-mantenimiento";
        }
        return "estado-ocupada";
    }
}

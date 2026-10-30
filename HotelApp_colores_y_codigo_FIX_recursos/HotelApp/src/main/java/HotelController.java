import java.io.InputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class HotelController {

    @FXML private Label lblHotel;
    @FXML private TableView<Element> tablaHabitaciones;
    @FXML private TableColumn<Element, String> colNumero;
    @FXML private TableColumn<Element, String> colTipo;
    @FXML private TableColumn<Element, String> colCapacidad;
    @FXML private TableColumn<Element, String> colPrecio;
    @FXML private TableColumn<Element, String> colEstado;
    @FXML private TextField txtNumero;
    @FXML private Label lblResultado;
    @FXML private FlowPane contenedorInicio;
    @FXML private FlowPane contenedorReservas;

    private NodeList habitaciones;

    @FXML
    private void initialize() {
        colNumero.setCellValueFactory(dato -> new SimpleStringProperty(dato.getValue().getAttribute("numero")));
        colTipo.setCellValueFactory(dato -> new SimpleStringProperty(texto(dato.getValue(), "tipo")));
        colCapacidad.setCellValueFactory(dato -> new SimpleStringProperty(texto(dato.getValue(), "capacidad")));
        colPrecio.setCellValueFactory(dato -> new SimpleStringProperty(texto(dato.getValue(), "precioNoche")));
        colEstado.setCellValueFactory(dato -> new SimpleStringProperty(estado(dato.getValue())));

        cargarHabitaciones();
    }

    private void cargarHabitaciones() {
        try (InputStream archivo = getClass().getResourceAsStream("/reservas.xml")) {
            if (archivo == null) {
                lblResultado.setText("No encontramos el archivo reservas.xml.");
                return;
            }

            Document documento = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(archivo);

            Element hotel = documento.getDocumentElement();
            lblHotel.setText(hotel.getElementsByTagName("nombre").item(0).getTextContent()
                    + " - " + hotel.getElementsByTagName("ciudad").item(0).getTextContent());

            habitaciones = documento.getElementsByTagName("habitacion");

            for (int i = 0; i < habitaciones.getLength(); i++) {
                Element habitacion = (Element) habitaciones.item(i);
                tablaHabitaciones.getItems().add(habitacion);

                // Una misma clase de tarjeta se reutiliza en las dos pantallas.
                if (i < 3) {
                    agregarTarjeta(contenedorInicio, habitacion);
                }
                agregarTarjeta(contenedorReservas, habitacion);
            }
        } catch (Exception e) {
            lblResultado.setText("No hemos podido leer reservas.xml: " + e.getMessage());
        }
    }

    private void agregarTarjeta(FlowPane destino, Element habitacion) {
        destino.getChildren().add(crearTarjeta(habitacion));
    }

    private HabitacionCard crearTarjeta(Element habitacion) {
        String numero = habitacion.getAttribute("numero");
        String tipo = texto(habitacion, "tipo");
        String precio = texto(habitacion, "precioNoche");
        String estado = estado(habitacion);

        return new HabitacionCard(numero, tipo, precio, estado, () -> {
            lblResultado.setText(datosHabitacion(habitacion));
            if (tablaHabitaciones != null) {
                tablaHabitaciones.getSelectionModel().select(habitacion);
                tablaHabitaciones.scrollTo(habitacion);
            }
        });
    }

    @FXML
    private void buscarHabitacion() {
        String numero = txtNumero.getText().trim();

        if (numero.isEmpty()) {
            lblResultado.setText("Escribe el número de la habitación.");
            return;
        }

        try {
            if (Integer.parseInt(numero) <= 0) {
                lblResultado.setText("Escribe un número mayor que cero.");
                return;
            }
        } catch (NumberFormatException e) {
            lblResultado.setText("Introduce solo un número de habitación.");
            return;
        }

        if (habitaciones == null) {
            lblResultado.setText("No hay datos cargados. Revisa reservas.xml.");
            return;
        }

        for (int i = 0; i < habitaciones.getLength(); i++) {
            Element habitacion = (Element) habitaciones.item(i);
            if (habitacion.getAttribute("numero").equals(numero)) {
                tablaHabitaciones.getSelectionModel().select(habitacion);
                tablaHabitaciones.scrollTo(habitacion);
                lblResultado.setText(datosHabitacion(habitacion));
                return;
            }
        }
        lblResultado.setText("La habitación " + numero + " no existe.");
    }

    private String texto(Element habitacion, String etiqueta) {
        return habitacion.getElementsByTagName(etiqueta).item(0).getTextContent();
    }

    private String estado(Element habitacion) {
        String valor = habitacion.getAttribute("estado").trim().toLowerCase();
        return "libre".equals(valor) ? "disponible" : valor;
    }

    private String datosHabitacion(Element habitacion) {
        return "Habitación " + habitacion.getAttribute("numero") + " - " + estado(habitacion)
                + "\nTipo: " + texto(habitacion, "tipo")
                + " | Capacidad: " + texto(habitacion, "capacidad")
                + "\nPrecio por noche: " + texto(habitacion, "precioNoche") + " €";
    }
}

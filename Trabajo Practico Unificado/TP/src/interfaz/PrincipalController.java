package interfaz;

/**
 @author Gastón Barrionuevo
         Hassan Mafud
         Agustin Semenkiw
         Milagros Bustos.
 @version Octubre 2020
 */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import negocio.*;
import negocio.GestorDeAgrupacion;


import java.io.File;

public class PrincipalController {
    public Label lblUbicacion;
    public ListView lvwResultados;
    public ComboBox cboDistrictos;
    public ComboBox cboSecciones;
    public ComboBox cboCircuitos;
    public GestorDeResultado gestorDeResultado;

    public void cambiarUbicacion(ActionEvent actionEvent) {

        DirectoryChooser dc =  new DirectoryChooser();

        dc.setTitle("Seleccione una ubicacion de los datos");
        dc.setInitialDirectory(new File(lblUbicacion.getText()));

        //el dc.showDialog retorna un objeto de la clase file
        File file = dc.showDialog(null);

        if (file!= null) {
            lblUbicacion.setText(file.getPath());
        }
    }

    public void cargarDatos(ActionEvent actionEvent) {
        ObservableList ol;

        //generamos una lista de agrupaciones
        GestorDeAgrupacion.leerAgrupaciones(lblUbicacion.getText());

        //generamos lista de districtos de el pais
        GestorDeRegion gestorDeRegion = new GestorDeRegion();
        gestorDeRegion.identifcarRegiones(lblUbicacion.getText());
        ol = FXCollections.observableArrayList(gestorDeRegion.mostrarDistrictos());
        cboDistrictos.setItems(ol);

        //Procesamos los totales por region
        gestorDeResultado = new GestorDeResultado();
        gestorDeResultado.calcularResultados(lblUbicacion.getText());

        //se le pone cod de region "00" porque es para el país
        ol = FXCollections.observableArrayList(gestorDeResultado.mostrarResultadosRegion("00"));
        lvwResultados.setItems(ol);

    }

    public void elegirDistricto(ActionEvent actionEvent) {
        ObservableList ol;

        //vamos a generar un listado con las secciones del districto seleccionado
        //el metodo "getValue()" nos devuelve un object que va a ser la districto seleccionado.
        Region districto = (Region) cboDistrictos.getValue();

        //aca le entregamos todas la secciones para ese districto
        ol = FXCollections.observableArrayList(districto.mostrarSubRegiones());
        cboSecciones.setItems(ol);

        //ahora mostramos resultados del Districto, para ello mandamos el codigo de districto que elegio el usuario
        //para ello invocamos al getCodigo del districto elgido.
        ol = FXCollections.observableArrayList(gestorDeResultado.mostrarResultadosRegion(districto.getCodigo()));
        lvwResultados.setItems(ol);

    }

    public void elegirSeccion(ActionEvent actionEvent) {

        ObservableList ol;

        //vamos a generar un listado con los circuitos de la seccion seleccionada
        //el metodo "getValue()" nos devuelve un object que va a ser la seccion seleccionada.

        if (cboSecciones.getValue() != null) {
        Region seccion = (Region) cboSecciones.getValue();

        //aca le entregamos todas la secciones para ese districto
        ol = FXCollections.observableArrayList(seccion.mostrarSubRegiones());
        cboCircuitos.setItems(ol);

        //ahora mostramos resultados de la seccion, se le envia el codigo de seccion elegida.
        ol = FXCollections.observableArrayList(gestorDeResultado.mostrarResultadosRegion(seccion.getCodigo()));
        lvwResultados.setItems(ol);
        }
        else {
            cboCircuitos.setItems(null);
        }

    }

    public void elegirCircuito(ActionEvent actionEvent) {
        ObservableList ol;

        if (cboCircuitos.getValue() != null) {
            Region circuito = (Region) cboCircuitos.getValue();

            //mostramos resultados del circuito
            ol = FXCollections.observableArrayList(gestorDeResultado.mostrarResultadosRegion(circuito.getCodigo()));
            lvwResultados.setItems(ol);
        }
        else {
            cboCircuitos.setItems(null);
        }

    }
}

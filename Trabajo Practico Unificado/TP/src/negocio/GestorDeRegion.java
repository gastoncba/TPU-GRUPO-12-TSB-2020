package negocio;

import soporte.TextFile;

import java.awt.desktop.PreferencesEvent;
import java.util.Collection;

public class GestorDeRegion {

    private TextFile archivoDeRegiones;
    private Region pais;

    public void identifcarRegiones(String path) {

        //aca lo que se hace es crear un instancia de TextFile tanto para el archivo de agrupaciones como para el mesas
        archivoDeRegiones = new TextFile(path + "\\descripcion_regiones.dsv");
        //
        // archivoDeMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");

        //nos tiene que devover un objeto de la clase region
        pais = archivoDeRegiones.identificarRegiones();

        //una vez que tengamos todas las agrupaciones en la hashMap lo que se hace es pedirle al lector de archivo
        //de mesas que sume el total por mesa
        //para lograr esto el lector de archivo de mesas tiene un metodo llamado sumarVotosPorAgrupacion()
        //archivoDeMesas.sumarVotosPorAgrupacion(agrupaciones);

    }


    public Collection mostrarDistrictos() {

        return pais.mostrarSubRegiones();
    }
}

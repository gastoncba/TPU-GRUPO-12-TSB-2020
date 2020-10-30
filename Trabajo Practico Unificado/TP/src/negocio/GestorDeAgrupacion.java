package negocio;

import soporte.TextFile;

import java.util.Collection;
import java.util.HashMap;

public class GestorDeAgrupacion {

    private static HashMap inicial;
    private HashMap conteo;

    public GestorDeAgrupacion() {
        conteo = new HashMap();

        for (Object o: inicial.values()) {
            Agrupacion a = (Agrupacion) o;
            conteo.put(a.getCodigo(), new Agrupacion(a.getCodigo(), a.getNombre()));

        }

    }


    public static void leerAgrupaciones(String path) {

        TextFile archivoDeAgrupaciones = new TextFile(path + "\\descripcion_postulaciones.dsv");
        inicial = archivoDeAgrupaciones.identificarAgrupaciones();

    }

    public Agrupacion getAgrupacion(String codAgr) {

        return (Agrupacion) conteo.get(codAgr);
    }

    public Collection mostrarResultados() {
        return conteo.values();
    }
}

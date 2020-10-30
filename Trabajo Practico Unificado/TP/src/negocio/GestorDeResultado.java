package negocio;

import soporte.TextFile;

import java.util.Collection;
import java.util.HashMap;

public class GestorDeResultado {

    private HashMap tabla;

    public GestorDeResultado() {
        tabla = new HashMap();
    }

    public void calcularResultados(String path) {

        TextFile archivoDeMesas = new TextFile(path + "\\mesas_totales_agrp_politica.dsv");
        archivoDeMesas.sumarVotosPorRegion(this);
    }

    public void incrementarVotos(String codRegion, String codAgrupacion, int votos) {

        int actual;
        //buscamos la region en tabla y la creamos si no existe
        if (tabla.get(codRegion) == null) {

            tabla.put(codRegion, new GestorDeAgrupacion());
        }

        GestorDeAgrupacion gestorDeAgrupacion = (GestorDeAgrupacion) tabla.get(codRegion);
        gestorDeAgrupacion.getAgrupacion(codAgrupacion).incrementarVotos(votos);

    }

    public Collection mostrarResultadosRegion(String codReg) {

        GestorDeAgrupacion a = (GestorDeAgrupacion) tabla.get(codReg);
        return a.mostrarResultados();
    }
}

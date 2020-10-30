package negocio;

import java.util.Collection;
import java.util.HashMap;

public class Region {
    private String codigo;
    private String nombre;
    private HashMap subRegiones;

    public Region(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.subRegiones = new HashMap();
    }

    public void agregarSubRegion(Region region) {

        subRegiones.put(region.codigo, region);
    }

    public Collection mostrarSubRegiones() {
        return subRegiones.values();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Region{");
        sb.append("codigo='").append(codigo).append('\'');
        sb.append(",nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Region getOrPutSubRegion(String cod) {
        Region subRegion = (Region) subRegiones.get(cod);

        if (subRegion == null) subRegiones.put(cod, new Region(cod, ""));

        return (Region) subRegiones.get(cod);
    }

    public String getCodigo() {
        return codigo;
    }
}

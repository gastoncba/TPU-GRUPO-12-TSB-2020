package negocio;

public class Agrupacion {

    private String codigo;
    private String nombre;
    private int votos;

    public Agrupacion(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Agrupacion{");
        sb.append("codigo='").append(codigo).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", votos=").append(votos).append('\'');
        sb.append('}');
        sb.append("\n");
        return sb.toString();
    }

    public void incrementarVotos(int cantVotos) {
        votos += cantVotos;
    }
}

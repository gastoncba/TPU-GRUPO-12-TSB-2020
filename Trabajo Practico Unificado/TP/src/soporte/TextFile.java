package soporte;

import negocio.Agrupacion;
import negocio.GestorDeResultado;
import negocio.Region;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class TextFile {

    private File file;


    public TextFile(String path) {
        file = new File(path);
    }

    public String leerEncabezado() {

        Scanner scanner;
        String linea = "";

        try {

            scanner = new Scanner(file);

            //necesitamos que nos de la primera linea
            while (scanner.hasNext()) {
                linea = scanner.nextLine();
                break;
            }

        } catch (IOException e) {

            System.out.println("No se pudo leer el archivo");

        }

        return linea;
    }

    public HashMap identificarAgrupaciones() {
        Scanner scanner;
        String linea = "", campos[];
        HashMap<String,Agrupacion> table = new HashMap<>(15);
        Agrupacion agrupacion;

        try {

            scanner = new Scanner(file);

            //necesitamos que nos de la primera linea
            while (scanner.hasNext()) {
                linea = scanner.nextLine();

                //slip reciebe una expresion regular...se pone doble barra por que justo el pibe tiene  otro significado
                // slipt nos devueve un arreglo de string
                campos = linea.split("\\|");

                if (campos[0].compareTo("000100000000000") == 0) {
                    agrupacion = new Agrupacion(campos[2],campos[3]);
                    table.put(agrupacion.getCodigo(), agrupacion);
                }
            }

        } catch (IOException e) {

            System.out.println("No se pudo leer el archivo");

        }

        return table;
    }

    public void sumarVotosPorAgrupacion(HashMap agrupaciones) {

        Scanner scanner;
        String linea = "", campos[];
        Agrupacion agrupacion;

        try {

            scanner = new Scanner(file);

            //necesitamos que nos de la primera linea
            while (scanner.hasNext()) {
                linea = scanner.nextLine();
                int votos;

                //slip reciebe una expresion regular...se pone doble barra por que justo el pibe tiene  otro significado
                // slipt nos devueve un arreglo de string
                campos = linea.split("\\|");

                if (campos[4].compareTo("000100000000000") == 0) {
                    agrupacion = (Agrupacion) agrupaciones.get(campos[5]);
                    votos = Integer.parseInt(campos[6]);
                    agrupacion.incrementarVotos(votos);
                }
            }

        } catch (IOException e) {

            System.out.println("No se pudo leer el archivo");

        }

    }

    public void sumarVotosPorRegion(GestorDeResultado gestorDeResultado) {

        Scanner scanner;
        String linea = "", campos[];

        try {

            scanner = new Scanner(file);

            //necesitamos que nos de la primera linea
            while (scanner.hasNext()) {
                linea = scanner.nextLine();
                int votos;

                //slip reciebe una expresion regular...se pone doble barra por que justo el pibe tiene  otro significado
                // slipt nos devueve un arreglo de string
                campos = linea.split("\\|");

                if (campos[4].compareTo("000100000000000") == 0) {
                    votos = Integer.parseInt(campos[6]);
                    //acumulamos los votos del pais
                    gestorDeResultado.incrementarVotos("00",campos[5], votos);
                    //acumulamos los votos del dist, sec y cir de la mesa
                    for (int i = 0; i < 3; i++) {
                        gestorDeResultado.incrementarVotos(campos[i],campos[5],votos);
                    }
                }
            }

        } catch (IOException e) {

            System.out.println("No se pudo leer el archivo");

        }


    }

    public Region identificarRegiones() {

        //lo que tiene que hacer este metodo es leer el archivo de regiones e ir
        //armando una estructura de regiones

        Scanner scanner;
        String linea = "", campos[],cod, nom;
        Region pais = new Region("00","Argentina");
        Region districto, seccion, circuito;

        try {

            scanner = new Scanner(file);

            //necesitamos que nos de la primera linea
            while (scanner.hasNext()) {
                linea = scanner.nextLine();

                //slip reciebe una expresion regular...se pone doble barra por que justo el pibe tiene  otro significado
                // slipt nos devueve un arreglo de string
                campos = linea.split("\\|");
                cod = campos[0];
                nom = campos[1];

                switch (cod.length()) {
                    case 2:
                        //districto
                        districto = pais.getOrPutSubRegion(cod);
                        districto.setNombre(nom);
                        break;
                    case 5:
                        //circuito
                        districto = pais.getOrPutSubRegion(cod.substring(0,2));
                        seccion = districto.getOrPutSubRegion(cod);
                        seccion.setNombre(nom);
                        break;
                    case 11:
                        //circuito
                        districto = pais.getOrPutSubRegion(cod.substring(0,2));
                        seccion = districto.getOrPutSubRegion(cod.substring(0,5));
                        seccion.agregarSubRegion(new Region(cod,nom));
                        break;
                }


            }

        } catch (IOException e) {

            System.out.println("No se pudo leer el archivo");

        }

        return pais;
    }


}

package clases;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TSBHashtableDATest {

    private TSBHashtableDA<Integer, String> ht1;

    @org.junit.Before
    public void setUp() {

        ht1 = new TSBHashtableDA<>(3, 0.2f);

        // algunas inserciones...
        ht1.put(1, "Argentina");
        ht1.put(2, "Brasil");
        ht1.put(3, "Chile");
        ht1.put(4, "Mexico");
        ht1.put(5, "Uruguay");
        ht1.put(6, "Perú");
        ht1.put(7, "Colombia");
    }

    @org.junit.After
    public void tearDown() {
    }

    @org.junit.Test
    public void containsKey() {

        //deberia ser null ya que no hay pais con key 1000
        assertNull(ht1.get(1000));

        //no deberia ser null ya que hay un pais con key: 5
        assertNotNull(ht1.get(5));
    }

    @org.junit.Test
    public void containsValue() {

        //se quiere verificar si el pais "Perú" se encuentra en la hashtable
        assertTrue(ht1.contains("Perú"));

        //se quiere verificar si el pais "Francia" se encuentra en la hashtable
        assertFalse(ht1.contains("Francia"));

    }

    @org.junit.Test
    public void get() {

        //Colombia tiene la key : 1
        assertEquals("Colombia",ht1.get(7));

        //No hay pais con key: 10
        assertNull(ht1.get(10));

        //Argentina tiene la key : 1
        assertNotNull(ht1.get(1));
    }

    @org.junit.Test
    public void put() {

        //se agrega un pais
        ht1.put(2,"Paraguay");

        assertEquals("Paraguay", ht1.get(2));

    }

    @org.junit.Test
    public void remove() {

        //se remueve el pais Brasil
        ht1.remove(2);

        //deberia dar null , porque se retiro Brasil
        assertNull(ht1.get(2));

    }

    @org.junit.Test
    public void clear() {

        //limpiamos la tabla hash
        ht1.clear();

        //el tamaño deberia ser cero , ya que se limpio
        assertEquals(0,ht1.size());
    }

    @org.junit.Test
    public void testToString() {
    }

    @org.junit.Test
    public void testClone() throws CloneNotSupportedException {
        //se crea una copia de la ht1

        TSBHashtableDA<Integer, String> copy = (TSBHashtableDA<Integer, String>) ht1.clone();

        //deberia retornar Chile ya que , este País tiene la key : 3
        assertEquals("Chile", copy.get(3));
    }

    @org.junit.Test
    public void testKeySet() {

        //creamos una vista para las claves
        Set<Integer> keys = ht1.keySet();

        System.out.println("Claves de la hashtable: ");
        //iteramos sobre la vista
        for (Integer i: keys) {
            System.out.println(i);
        }
    }

    @org.junit.Test
    public void testValues() {

        //creamos una vista para los valores
        Collection<String> values = ht1.values();

        System.out.println("Valores de hashtable: ");
        //iteramos sobre la vista de valores
        for (String i: values) {
            System.out.println(i);
        }
    }

    @org.junit.Test
    public void testEntrySet() {

        //creamos una vista para las pares
        Set<Map.Entry<Integer,String>> entrySet = ht1.entrySet();

        System.out.println("pares clave valor de la hashtable: ");
        //iteramos sobre la vista de pares
        for (Map.Entry<Integer,String> i: entrySet) {
            System.out.println(i);
        }
    }

    @org.junit.Test
    public void testInsertAndRemove() {
        ht1.remove(2);
        ht1.put(2,"Rusia");

        Collection<String> values = ht1.values();

        System.out.println("Valores de hashtable: ");
        //iteramos sobre la vista de valores
        for (String i: values) {
            System.out.println(i);
        }

    }

    @org.junit.Test
    public void testEntrySetIteratorRemove() {

        Set<Map.Entry<Integer, String>> se = ht1.entrySet();
        Iterator<Map.Entry<Integer, String>> it = se.iterator();
        while(it.hasNext())
        {
            Map.Entry<Integer, String> entry = it.next();

            if (entry.getKey().equals(4)) {it.remove();}

        }

        //recoremos con otro iterador para ver si el pais con key: 4 fue removido
        Iterator<Map.Entry<Integer, String>> it2 = se.iterator();
        while(it2.hasNext())
        {
            Map.Entry<Integer, String> entry = it2.next();

            System.out.println(entry);

        }
    }
}
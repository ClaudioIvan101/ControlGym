package Membresias;

import java.util.ArrayList;
import java.util.List;

public class CrudMembresia {
    private final List<Membresia> lista = new ArrayList<>();
    private int nextId = 1;

    public CrudMembresia(List<Membresia> listaInicial) {
        if (listaInicial != null) {
            this.lista.addAll(listaInicial);
            for (Membresia m : listaInicial) {
                if (m.getId() >= nextId) {
                    nextId = m.getId() + 1;
                }
            }
        }
    }

    public Membresia agregar(String nombre, double precio) {
        if (!esPrecioValido(precio)) {
            System.out.println("El precio debe ser mayor a cero.");
        }
        Membresia m = new Membresia(nextId++, nombre, precio);
        lista.add(m);
        return m;
    }

    public boolean modificar(int id, String nuevoNombre, double nuevoPrecio) {
        if (!esPrecioValido(nuevoPrecio)) {
            return false;
        }
        Membresia m = buscarPorId(id);
        m.setNombre(nuevoNombre);
        m.setPrecio(nuevoPrecio);
        return true;
    }

    public void eliminar(int id){
        Membresia m = buscarPorId(id);
        lista.remove(m);
    }



    public List<Membresia> listar() {
        return new ArrayList<>(lista);
    }

    public Membresia buscarPorId(int id){
        for (Membresia m : lista) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    public static boolean esPrecioValido(double precio) {
        return precio > 0;
    }
}

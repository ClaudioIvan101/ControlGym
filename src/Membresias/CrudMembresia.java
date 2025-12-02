package Membresias;

import Excepciones.MembresiaNoEncontradaException;

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

    public boolean modificar(int id, String nuevoNombre, double nuevoPrecio) throws MembresiaNoEncontradaException {
        if (!esPrecioValido(nuevoPrecio)) {
            return false;
        }
        try {
            Membresia m = buscarPorId(id);
            m.setNombre(nuevoNombre);
            m.setPrecio(nuevoPrecio);
            return true;
        } catch (MembresiaNoEncontradaException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public void eliminar(int id) throws MembresiaNoEncontradaException {
        Membresia m = buscarPorId(id);
        lista.remove(m);
    }



    public List<Membresia> listar() {
        return new ArrayList<>(lista);
    }

    public Membresia buscarPorId(int id) throws MembresiaNoEncontradaException {
        for (Membresia m : lista) {
            if (m.getId() == id) return m;
        }
        throw new MembresiaNoEncontradaException(id);
    }

    public static boolean esPrecioValido(double precio) {
        return precio > 0;
    }
}

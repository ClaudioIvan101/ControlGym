package Socios;

import Membresias.Membresia;


public class Socio extends Persona {
    private Membresia membresia;

    public Socio(int dni, String nombre, int edad) {
        super(dni, nombre, edad);
    }


    @Override
    public String toString() {
        return getNombre() + " (" + getDni() + ") - Membresía: " + membresia.getNombre();
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    @Override
    public void mostrarInfo() {
        System.out.println(getNombre() + " (" + getDni() + ") - Membresía: " + membresia.getNombre());
    }
}

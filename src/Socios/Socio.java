package Socios;

import Membresias.Membresia;
import Cuotas.Cuota;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Socio extends Persona {
    private List<Cuota> cuotas = new ArrayList<>();
    private LocalDate vencimientoActual;
    private Membresia membresia;


    public Socio(int dni, String nombre, int edad) {
        super(dni, nombre, edad);
    }

    @Override
    public void mostrarInfo() {
        System.out.println(getNombre() + " (" + getDni() + ") - Membresía: " + membresia.getNombre());
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public Cuota getUltimaCuotaPagada() {
        if (cuotas.isEmpty()) return null;

        return cuotas.stream()
                .filter(Cuota::isPagado)
                .max(Comparator.comparing(Cuota::getFechaPago))
                .orElse(null);
    }

    public boolean tieneCuotaAlDia() {
        Cuota ultima = getUltimaCuotaPagada();
        if (ultima == null) return false;
        return !ultima.getFechaVencimiento().isBefore(LocalDate.now());
    }

}

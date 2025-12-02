package Asistencias;

import Asistencias.Asistencia;
import Membresias.Membresia;
import Socios.Socio;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrudAsistencia {
    private final List<Asistencia> asistencias;
    private final List<Socio> socios;
    private final List<Membresia> membresias;
    private final Scanner sc;
    private static int idGenerator = 1;

    public CrudAsistencia(List<Socio> socios,
                          List<Membresia> membresias,
                          List<Asistencia> asistencias,
                          Scanner sc) {

        this.socios = socios;
        this.membresias = membresias;
        this.asistencias = asistencias;
        this.sc = sc;
    }

    public boolean registrarAsistencia() {

        int id = idGenerator++;
        Socio socioEncontrado = null;

        while (socioEncontrado == null) {

            System.out.print("Ingrese el DNI del socio (ENTER para cancelar): ");
            Integer dni = leerEntero();

            if (dni == null) {
                System.out.println("Operación cancelada. Volviendo al menú...");
                return false;
            }

            for (Socio s : socios) {
                if (s.getDni() == dni) {
                    socioEncontrado = s;
                    break;
                }
            }

            if (socioEncontrado == null) {
                System.out.println("Socio no encontrado. Intente nuevamente.\n");
            }
        }

        System.out.println("Socio encontrado: " + socioEncontrado.getNombre());

        Membresia membresiaEncontrada = socioEncontrado.getMembresia();

        if (membresiaEncontrada == null) {
            System.out.println("El socio no tiene membresía asignada. No se puede registrar asistencia.");
            return false;
        }
        LocalDateTime fechaHora = LocalDateTime.now();

        Asistencia asistencia = new Asistencia(
                id,
                socioEncontrado,
                fechaHora,
                membresiaEncontrada
        );

        asistencias.add(asistencia);

        System.out.println("Asistencia registrada! Número " + id);
        return true;
    }
    private Integer leerEntero() {
        String input = sc.nextLine().trim();

        if (input.isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}

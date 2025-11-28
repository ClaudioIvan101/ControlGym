package Asistencias;

import java.util.Scanner;

public class MenuAsistencia {
    private CrudAsistencia crudAsistencia;
    private Scanner sc;

    public MenuAsistencia(CrudAsistencia crudAsistencia, Scanner sc) {
        this.crudAsistencia = crudAsistencia;
        this.sc = sc;
    }

    public void mostrarMenu() {
        int opcion = 0;

        do {
            System.out.println("\n===== MENÚ DE ASISTENCIAS =====");
            System.out.println("1. Registrar asistencia");
            System.out.println("2. Listar asistencias");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:
                    System.out.println("Registrar asistencia");
                    break;
                case 2:
                    System.out.println("listar asistencias");
                    break;
                case 3:
                    System.out.println("Volviendo al menú anterior...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 3);
    }

    private int leerEntero() {
        while (true) {
            try {
                String linea = sc.nextLine();
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Intente de nuevo: ");
            }
        }
    }
}

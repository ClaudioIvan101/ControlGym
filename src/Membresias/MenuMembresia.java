package Membresias;

import java.util.List;
import java.util.Scanner;

public class MenuMembresia {
    private final Scanner sc;

    public MenuMembresia(Scanner sc) {
        this.sc = sc;
    }
    int opcion = 0;

    public void mostrarMenuMembresia() throws Exception {
        do {
            System.out.println("----- Menu Membresia -----");
            System.out.println("1. Agregar Membresia");
            System.out.println("2. Modificar Membresia");
            System.out.println("3. Eliminar Membresia");
            System.out.println("4. Mostrar Membresias");
            System.out.println("5. Listar socios de membresia");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");
            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Agregar Membresia");
                    break;
                case 2:
                    System.out.println("Modificar Membresia");
                    break;
                case 3:
                    System.out.println("Eliminar Membresia");
                    break;
                case 4:
                    System.out.println("Mostrar Membresias");
                    break;
                case 5:
                    System.out.println("Listar socios de membresia");
                    break;
                case 6:
                    System.out.println("Saliendo del menu membresia.");
                default:
                    System.out.println("Opcion invalida. Por favor intente de nuevo.");
            }
        } while (opcion != 6);
    }
}

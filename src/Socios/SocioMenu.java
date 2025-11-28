package Socios;

import java.util.Scanner;

public class SocioMenu {
    private Scanner scanner;
    public void mostrarMenu() {
    int opcion = -1;

    do {
        System.out.println("\n=== SUB MENU SOCIOS ===");
        System.out.println("1. Agregar socio");
        System.out.println("2. Buscar socio");
        System.out.println("3. Modificar socio");
        System.out.println("4. Eliminar socio");
        System.out.println("5. Listar socios");
        System.out.println("0. Volver");
        System.out.print("Seleccione opción: ");


        opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion)
        {
            case 1:
                System.out.println("Agregar"); break;
            case 2:
                System.out.println("buscar"); break;
            case 3:
                System.out.println("modificar"); break;
            case 4:
                System.out.println("eliminar"); break;
            case 5:
                System.out.println("listar"); break;
            case 0: break;
            default:
                System.out.println("Opción inválida.");
        }



    } while (opcion != 0);
}
}

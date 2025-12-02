import Asistencias.Asistencia;
import Asistencias.CrudAsistencia;
import Asistencias.MenuAsistencia;
import Membresias.CrudMembresia;
import Membresias.Membresia;
import Membresias.MenuMembresia;
import Reportes.MenuReporte;
import Reportes.Reporte;
import Socios.CrudSocios;
import Socios.Socio;
import Socios.SocioMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // BASE DE DATOS EN MEMORIA
        List<Socio> socios = new ArrayList<>();
        List<Asistencia> asistencias = new ArrayList<>();
        List<Membresia> membresias = new ArrayList<>();
        // CRUD SERVICES
        CrudMembresia crudMembresia = new CrudMembresia(membresias);
        CrudSocios socioService = new CrudSocios(socios,crudMembresia);
        CrudAsistencia crudAsistencia = new CrudAsistencia(socios, membresias, asistencias, sc);

        Reporte reporte = new Reporte(socios, asistencias);
        // MENUS
        MenuAsistencia menuAsistencia = new MenuAsistencia(crudAsistencia, sc);
        MenuReporte menuReportes = new MenuReporte(reporte);
        SocioMenu socioMenu = new SocioMenu(crudMembresia, socioService, sc);
        MenuMembresia menuMembresia = new MenuMembresia(crudMembresia, socioService, sc);

        int opcion;
        do {
            System.out.println("===== SISTEMA DE GESTIÓN DE GIMNASIO =====");
            System.out.println("1. Gestión de Socios");
            System.out.println("2. Gestión de Membresías");
            System.out.println("3. Registrar Asistencia");
            System.out.println("4. Reportes");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            opcion = Integer.parseInt(sc.nextLine().trim());
            switch (opcion) {
                case 1:
                    socioMenu.mostrarMenu();
                    break;
                case 2:
                    menuMembresia.mostrarMenuMembresia();
                    break;
                case 3:
                    menuAsistencia.mostrarMenu();
                    break;
                case 4:
                    menuReportes.mostrarMenuReportes();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
            System.out.println();

        } while (opcion != 5);

        sc.close();
    }
}
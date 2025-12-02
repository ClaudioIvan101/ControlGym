package Reportes;

import Asistencias.Asistencia;
import Socios.Socio;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MenuReporte {
    private final Reporte reporte;
    private final Scanner sc = new Scanner(System.in);
    private int opcion = 0;

    public MenuReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public void mostrarMenuReportes() {
        do {
            System.out.println("----- Menu Reportes -----");
            System.out.println("1. Monto total facturado");
            System.out.println("2. Mostrar asistencias de hoy");
            System.out.println("3. Contar asistencias por socio");
            System.out.println("4. Socios con cuota vencida");
            System.out.println("5. Socios con cuota al día");
            System.out.println("6. Ver vencimiento de socio por DNI");
            System.out.println("7. Salir");

            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {

                case 1:
                    System.out.println("Monto total facturado: $" + reporte.montoTotalFacturado());
                    break;
                case 2:
                    List<Asistencia> hoy = reporte.asistenciasHoy();
                    System.out.println("Asistencias de hoy (" + LocalDate.now() + "):");
                    hoy.forEach(System.out::println);
                    break;
                case 3:
                    Map<Socio, Long> conteo = reporte.contarAsistenciasPorSocio();
                    System.out.println("Cantidad de asistencias por socio:");
                    conteo.forEach((socio, cant) ->
                            System.out.println(socio.getNombre() + ": " + cant));
                    break;
                case 4:
                    System.out.println("Socios con cuota vencida:");
                    reporte.sociosConCuotaVencida()
                            .forEach(s -> System.out.println(s.getNombre()));
                    break;
                case 5:
                    System.out.println("Socios con cuota al día:");
                    reporte.sociosConCuotaAlDia()
                            .forEach(s -> System.out.println(s.getNombre()));
                    break;
                case 6:
                    System.out.print("Ingrese DNI del socio: ");
                    int dni = Integer.parseInt(sc.nextLine());
                    LocalDate venc = reporte.vencimientoDeSocio(dni);
                    if (venc == null) {
                        System.out.println("No tiene cuotas pagadas o el socio no existe.");
                    } else {
                        System.out.println("La cuota vence el: " + venc);
                    }
                    break;
                case 7:
                    System.out.println("Saliendo del menú de reportes.");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor intente de nuevo.");
            }

        } while (opcion != 9);
    }
}

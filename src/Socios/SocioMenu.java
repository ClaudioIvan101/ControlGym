package Socios;

import Membresias.CrudMembresia;
import Membresias.Membresia;
import java.util.List;
import java.util.Scanner;

public class SocioMenu {
    private final CrudMembresia crudMembresia;
    private final CrudSocios servicio;
    private Scanner scanner;

    public SocioMenu(CrudMembresia crudMembresia, CrudSocios servicio) {
        this.crudMembresia = crudMembresia;
        this.servicio = servicio;
    }

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
    private void agregar()  {
        List<Membresia> lista = crudMembresia.listar();
        System.out.println("--- ALTA DE SOCIO ---");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("DNI: ");
        int dni = Integer.parseInt(scanner.nextLine());

        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());

        System.out.println("Seleccionar membresía:");
        lista.forEach(m ->
                System.out.println("ID " + m.getId() + " - " + m.getNombre() + " ($" + m.getPrecio() + ")")
        );

        System.out.print("ID membresia: ");
        int idMembresia = Integer.parseInt(scanner.nextLine());

        Membresia membresiaSeleccionada = crudMembresia.buscarPorId(idMembresia);


        Socio nuevo = new Socio(dni, nombre, edad);
        nuevo.setMembresia(membresiaSeleccionada);
        servicio.agregarSocio(nuevo);

        System.out.println(">> Socio guardado");
    }
    private void buscar() {
        System.out.print("Ingrese DNI del socio: ");
        int dni = Integer.parseInt(scanner.nextLine());
        Socio s = servicio.buscarSocio(dni);
        if (s != null) s.mostrarInfo();
        else System.out.println(">> No encontrado.");
    }
    private void modificar() {
        System.out.print("DNI del socio a modificar: ");
        int dni = Integer.parseInt(scanner.nextLine());

        Socio s = servicio.buscarSocio(dni);

        if (s == null) {
            System.out.println(">> No encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (actual: " + s.getNombre() + "): ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Nueva edad (actual: " + s.getEdad() + "): ");
        int nuevaEdad = Integer.parseInt(scanner.nextLine());

        s.setNombre(nuevoNombre);
        s.setEdad(nuevaEdad);

        System.out.println(">> Datos modificados.");
    }


    private void eliminar() {
        System.out.print("DNI del socio a eliminar: ");
        int dni = Integer.parseInt(scanner.nextLine());

        if (servicio.eliminarSocio(dni))
            System.out.println(">> Eliminado.");
        else
            System.out.println(">> No encontrado.");
    }


    private void listar() {
        System.out.println("--- LISTADO DE SOCIOS ---");
        if (servicio.obtenerTodos().isEmpty()) {
            System.out.println("No hay socios cargados.");
            return;
        }

        servicio.obtenerTodos().forEach(Socio::mostrarInfo);
    }


}

package Membresias;

import Socios.CrudSocios;
import Socios.Socio;

import java.util.List;
import java.util.Scanner;

public class MenuMembresia {
    private final Scanner sc;
    private final CrudMembresia crudMembresia;
    private final CrudSocios crudSocios;


    public MenuMembresia(CrudMembresia crudMembresia, CrudSocios crudSocios,Scanner sc) {
        this.sc = sc;
        this.crudMembresia = crudMembresia;
        this.crudSocios = crudSocios;
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
                    agregarMembresia();
                    break;
                case 2:
                    modificarMembresia();
                    break;
                case 3:
                    eliminarMembresia();
                    break;
                case 4:
                    List<Membresia> lista = crudMembresia.listar();
                    if (lista.isEmpty()) {
                        System.out.println("No hay membresias registradas.");
                    } else {
                        mostrarMembresias(lista, 0);
                    }
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
    private void agregarMembresia() {
        String nombre;
        double precio = 0;
        boolean precioEsValido = false;

        try {
            System.out.print("Nombre: ");
            nombre = sc.nextLine().trim();
            do {
                System.out.print("Precio: ");
                String precioStr = sc.nextLine().trim();
                try {
                    precio = Double.parseDouble(precioStr);
                    precioEsValido = CrudMembresia.esPrecioValido(precio);

                    if (!precioEsValido) {
                        System.out.println("ERROR: El precio debe ser un valor positivo (mayor a cero).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("RROR: Ingrese un número válido para el precio.");
                    precioEsValido = false;
                }

            } while (!precioEsValido);
            Membresia m = crudMembresia.agregar(nombre, precio);
            System.out.println("Membresia agregada: " + m);

        } catch (Exception e) {
            System.out.println("Operación cancelada debido a: " + e.getMessage());
        }
    }

    private void modificarMembresia() {
        try {
            System.out.print("Ingrese ID de la membresia a modificar: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Membresia existente = crudMembresia.buscarPorId(id);
            if (existente == null) {
                System.out.println("Membresia no encontrada.");
                return;
            }
            System.out.println("Encontrado: " + existente);
            System.out.print("Nuevo nombre (enter para mantener '" + existente.getNombre() + "'): ");
            String nombre = sc.nextLine().trim();
            if (nombre.isEmpty()) {
                nombre = existente.getNombre();
            }

            double precio = existente.getPrecio();
            boolean precioEsValido = false;

            do {
                System.out.print("Nuevo precio (enter para mantener $" + existente.getPrecio() + "): ");
                String precioStr = sc.nextLine().trim();

                if (precioStr.isEmpty()) {
                    precio = existente.getPrecio();
                    precioEsValido = true;
                } else {
                    try {
                        double nuevoPrecio = Double.parseDouble(precioStr);
                        precioEsValido = CrudMembresia.esPrecioValido(nuevoPrecio);

                        if (precioEsValido) {
                            precio = nuevoPrecio;
                        } else {
                            System.out.println("ERROR: El precio debe ser un valor positivo (mayor a cero).");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Ingrese un número válido o presione Enter.");
                        precioEsValido = false;
                    }
                }
            } while (!precioEsValido);

            boolean ok = crudMembresia.modificar(id, nombre, precio);

            if (ok) {
                System.out.println("Membresia modificada.");
            } else {
                System.out.println("No se pudo modificar. Verifique los datos o el ID.");
            }

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Operacion cancelada.");
        }
    }

    private void eliminarMembresia() {
        try {
            System.out.print("Ingrese ID de la membresía a eliminar: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            crudMembresia.eliminar(id);
            System.out.println("Membresía eliminada correctamente.");

        } catch (NumberFormatException e) {
            System.out.println("ID inválido. Operación cancelada.");
        }
    }

    public void mostrarMembresias(List<Membresia> lista, int indice) {
        if (indice >= lista.size()) {
            return;
        }

        System.out.println(lista.get(indice));

        mostrarMembresias(lista, indice + 1);
    }

    private void listarSociosDeMembresia(){
        Scanner sc = new Scanner(System.in);

        System.out.println("----- Membresías disponibles -----");
        mostrarTodas();

        System.out.print("Ingrese el ID de la membresía para ver sus socios: ");
        int id = Integer.parseInt(sc.nextLine());

        Membresia m = crudMembresia.buscarPorId(id);

        if (m == null) {
            System.out.println("No existe una membresía con ese ID.");
            return;
        }
        List<Socio> socios = crudSocios.buscarPorMembresia(m);
        if (socios.isEmpty()) {
            System.out.println(
                    "No hay socios registrados en la membresía '" + m.getNombre() + "'."
            );
            return;
        }

        System.out.println("Socios con la membresía '" + m.getNombre() + "':");
        for (Socio s : socios) {
            System.out.println(s);
        }
    }
    public void mostrarTodas() {
        List<Membresia> lista = crudMembresia.listar();

        if (lista.isEmpty()) {
            System.out.println("No hay membresías registradas.");
            return;
        }
        lista.forEach(m ->
                System.out.println("ID " + m.getId() + " - " + m.getNombre() + " ($" + m.getPrecio() + ")")
        );
    }
}

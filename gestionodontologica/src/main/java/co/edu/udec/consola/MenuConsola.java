package co.edu.udec.consola;

import java.util.Scanner;

import co.edu.udec.servicio.SistemaCitas;

public class MenuConsola {

    private final Scanner scanner;
    private final SistemaCitas sistema;

    public MenuConsola() {
        scanner = new Scanner(System.in);
        sistema = new SistemaCitas();
    }

    // Inicia el ciclo principal del menu
    public void iniciar() {

        int opcion = -1;

        do {

            mostrarMenu();

            try {

                System.out.println("Seleccione una opcion: ");
                opcion = Integer.parseInt(scanner.nextLine());

                procesarOpcion(opcion);

            } catch (NumberFormatException e) {

                System.out.println("Debe ingresar un numero valido");

            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());

            }

        } while (opcion != 0);

        System.out.println("Sistema finalizado...");

    }

    // Muestra las opciones disponibles
    private void mostrarMenu() {

        System.out.println("\n======================================");
        System.out.println(" SISTEMA DE CITAS ODONTOLOGICAS");
        System.out.println("======================================");
        System.out.println("1. Registrar paciente");
        System.out.println("2. Registrar odontologo");
        System.out.println("3. Registrar cita");
        System.out.println("4. Listar todas las citas");
        System.out.println("5. Buscar cita por codigo");
        System.out.println("6. Consultar especialidades");
        System.out.println("7. Listar citas por fecha");
        System.out.println("8. Consultar siguiente cita");
        System.out.println("9. Atender siguiente cita");
        System.out.println("10. Modificar cita");
        System.out.println("11. Cancelar cita");
        System.out.println("12. Eliminar cita");
        System.out.println("13. Mostrar estadisticas");
        System.out.println("0. Salir");
        System.out.println("======================================");

    }

    // Procesa la opcion seleccionada
    private void procesarOpcion(int opcion) throws Exception {
        
        switch (opcion) {

            case 0:
                break;

            default:
                System.out.println("Esta opcion aun no ha sido implementada");
                break;
        }
    }
}

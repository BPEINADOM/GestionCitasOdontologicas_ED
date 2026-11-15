package co.edu.udec.consola;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import co.edu.udec.modelo.Cita;
import co.edu.udec.modelo.Odontologo;
import co.edu.udec.modelo.Paciente;
import co.edu.udec.modelo.Prioridad;
import co.edu.udec.servicio.SistemaCitas;

public class MenuConsola {

    private final Scanner scanner;
    private final SistemaCitas sistema;

    public MenuConsola(SistemaCitas sistema) {
        scanner = new Scanner(System.in);
        this.sistema = sistema;
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

            case 1:
                registrarPaciente();
                break;
            
            case 2:
                registrarOdontologo();
                break;
            
            case 3:
                registrarCita();
                break;

            case 4:
                listarCitas();
                break;
            
            case 5:
                buscarCita();
                break;
            
            case 6:
                consultarEspecialidades();
                break;

            case 7:
                listarCitasPorFecha();
                break;

            case 8:
                consultarSiguienteCita();
                break;

            case 9:
                atenderSiguienteCita();
                break;
            
            case 10:
                modificarCita();
                break;

            case 11:
                cancelarCita();
                break;
            
            case 12:
                eliminarCita();
                break;

            case 13:
                mostrarEstadisticas();
                break;

            case 0:
                break;

            default:
                System.out.println("Esta opcion aun no ha sido implementada");
                break;
        }
    }

    // Solicita los datos y registra un paciente
    private void registrarPaciente() throws Exception {

        System.out.println("\n===== REGISTRAR PACIENTE =====");

        System.out.print("Documento: ");
        String documento = scanner.nextLine().trim();

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Telefono: ");
        String telefono = scanner.nextLine().trim();

        Paciente paciente = new Paciente(documento, nombre, telefono);

        sistema.registrarPaciente(paciente);

        System.out.println("Paciente registrado correctamente");

    }

    // Solicita los datos y registra un odontologo
    private void registrarOdontologo() throws Exception {

        System.out.println("\n===== REGISTRAR ODONTOLOGO =====");

        System.out.print("Documento: ");
        String documento = scanner.nextLine().trim();

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine().trim();

        Odontologo odontologo = new Odontologo(
                documento, nombre, especialidad
        );

        sistema.registrarOdontologo(odontologo);

        System.out.println("Odontologo registrado correctamente");

    }

    // Solicita los datos y registra una cita
    private void registrarCita() throws Exception {

        System.out.println("\n===== REGISTRAR CITA =====");

        System.out.print("Codigo de la cita: ");
        String codigo = scanner.nextLine().trim();

        System.out.print("Documento del paciente: ");
        String documentoPaciente = scanner.nextLine().trim();

        Paciente paciente = sistema.buscarPaciente(documentoPaciente);

        if (paciente == null) {
            throw new Exception("No existe un paciente con ese documento");
        }

        System.out.print("Documento del odontologo: ");
        String documentoOdontologo = scanner.nextLine().trim();

        Odontologo odontologo = sistema.buscarOdontologo(documentoOdontologo);

        if (odontologo == null) {
            throw new Exception("No existe un odontologo con ese documento");
        }

        System.out.print("Fecha y hora (dd/MM/yyyy HH:mm): ");
        String fechaTexto = scanner.nextLine().trim();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime fechaHora = LocalDateTime.parse(fechaTexto, formato);

        System.out.println("\nPrioridades disponibles:");
        System.out.println("1. CRITICA");
        System.out.println("2. ALTA");
        System.out.println("3. MEDIA");
        System.out.println("4. BAJA");

        System.out.print("Seleccione la prioridad: ");
        int opcionPrioridad = Integer.parseInt(scanner.nextLine());

        Prioridad prioridad;

        switch (opcionPrioridad) {

            case 1:
                prioridad = Prioridad.CRITICA;
                break;
            case 2:
                prioridad = Prioridad.ALTA;
                break;
            case 3:
                prioridad = Prioridad.MEDIA;
                break;
            case 4:
                prioridad = Prioridad.BAJA;
                break;
            default:
                throw new Exception("La prioridad seleccionada no es valida");
        
        }

        Cita cita = new Cita(codigo, paciente, odontologo, fechaHora, prioridad);

        sistema.registrarCita(cita);

        System.out.println("\nCita registrada correctamente");
        System.out.println(cita);

    }

    // Listar todas las citas registradas
    private void listarCitas() {

        System.out.println("\n===== TODAS LAS CITAS =====");

        List<Cita> lista = sistema.listarCitas();

        if (lista.isEmpty()) {
            System.out.println("No hay citas registradas");
            return;
        }

        for (Cita cita : lista) {
            System.out.println(cita);
        }

    }

    // Buscar citas por codigo
    private void buscarCita() {

        System.out.println("\n===== BUSCAR CITA =====");

        System.out.print("Ingrese el codigo de la cita: ");
        String codigo = scanner.nextLine().trim();

        Cita cita = sistema.buscarCita(codigo);

        if (cita == null) {
            
            System.out.println("No se encontro ninguna cita con ese codigo");
            return;

        }

        System.out.println("\nCita encontrada:");
        System.out.println(cita);

    }

    // Consultar las especialidades disponibles
    public void consultarEspecialidades() {

        System.out.println("\n===== ESPECIALIDADES DISPONIBLES =====");

        Set<String> lista = sistema.listarEspecialidades();

        if (lista.isEmpty()) {
            System.out.println("No hay especialidades registradas");
            return;
        }

        for (String especialidad : lista) {
            System.out.println("- " + especialidad);
        }

    }

    // Listar las citas por fecha
    private void listarCitasPorFecha() {

        System.out.println("\n===== CITAS ORDENADAS POR FECHA =====");

        List<Cita> lista = sistema.listarCitasPorFecha();

        if (lista.isEmpty()) {
            System.out.println("No hay citas registradas");
            return;
        }

        for (Cita cita : lista) {
            System.out.println(cita);
        }

    }

    // Consultar la siguiente cita
    private void consultarSiguienteCita() {

        System.out.println("\n===== SIGUIENTE CITA POR ATENDER =====");

        Cita cita = sistema.consultarSiguienteCita();

        if (cita == null) {
            System.out.println("No hay citas pendientes por atender");
            return;
        }

        System.out.println("La siguiente cita es:");
        System.out.println(cita);

    }

    // Atender la siguiente cita
    private void atenderSiguienteCita() throws Exception {

        System.out.println("\n===== ATENDER SIGUIENTE CITA =====");

        Cita cita = sistema.atenderSiguienteCita();

        System.out.println("La cita ha sido atendida correctamente");
        System.out.println(cita);

    }

    // Modificar las citas pendientes
    private void modificarCita() throws Exception {

        System.out.println("\n===== MODIFICAR CITA =====");

        System.out.print("Codigo de la cita: ");
        String codigo = scanner.nextLine().trim();

        Cita cita = sistema.buscarCita(codigo);

        if (cita == null) {
            System.out.println("No existe una cita con ese codigo");
        }

        System.out.println("Cita actual:");
        System.out.println(cita);

        System.out.print("\nDocumento del nuevo paciente: ");
        String documentoPaciente = scanner.nextLine().trim();

        Paciente paciente = sistema.buscarPaciente(documentoPaciente);

        if (paciente == null) {
            System.out.println("No existe un paciente con ese documento");
            return;
        }

        System.out.print("Documento del nuevo odontologo: ");
        String documentoOdontologo = scanner.nextLine().trim();

        Odontologo odontologo = sistema.buscarOdontologo(documentoOdontologo);

        if (odontologo == null) {
            System.out.println("No existe un odontologo con ese documento");
            return;
        }

        System.out.print("Nueva fecha y hora (dd/MM/yyyy HH:mm): ");
        String fechaTexto = scanner.nextLine().trim();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime fechaHora = LocalDateTime.parse(fechaTexto, formato);

        System.out.println("\nPrioridades disponibles:");
        System.out.println("1. CRITICA");
        System.out.println("2. ALTA");
        System.out.println("3. MEDIA");
        System.out.println("4. BAJA");

        System.out.print("Seleccione la nueva prioridad: ");
        int opcionPrioridad = Integer.parseInt(scanner.nextLine());

        Prioridad prioridad;

        switch (opcionPrioridad) {
            case 1:
                prioridad = Prioridad.CRITICA; break;
            case 2: 
                prioridad = Prioridad.ALTA; break;
            case 3: 
                prioridad = Prioridad.MEDIA; break;
            case 4: 
                prioridad = Prioridad.BAJA; break;
            default:
                throw new Exception("La prioridad seleccionada no es valida");
        }

        sistema.modificarCita(codigo, paciente, odontologo, fechaHora, prioridad);

        System.out.println("\nCita modificada correctamente");
        System.out.println(sistema.buscarCita(codigo));
    }

    // Cancelar una cita por codigo
    private void cancelarCita() throws Exception {

        System.out.println("\n===== CANCELAR CITA =====");

        System.out.print("Codigo de la cita: ");
        String codigo = scanner.nextLine().trim();

        Cita cita = sistema.buscarCita(codigo);

        if (cita == null) {
            System.out.println("No existe una cita con ese codigo");
            return;
        }

        System.out.println("\nCita seleccionada:");
        System.out.println(cita);

        sistema.cancelarCita(codigo);

        System.out.println("\nCita cancelada correctamente");
        System.out.println(sistema.buscarCita(codigo));
    }

    // Eliminar citas por el codigo
    private void eliminarCita() throws Exception {

        System.out.println("\n===== ELIMINAR CITA =====");

        System.out.print("Codigo de la cita: ");
        String codigo = scanner.nextLine().trim();

        Cita cita = sistema.buscarCita(codigo);

        if (cita == null) {
            System.out.println("No existe una cita con ese codigo");
        }

        System.out.println("\nCita seleccionada:");
        System.out.println(cita);

        sistema.eliminarCita(codigo);

        System.out.println("\nCita eliminada correctamente");

    }

    // Mostrar las estadisticas del sistema de citas
    private void mostrarEstadisticas() {
        sistema.mostrarEstadisticas();
    }

}

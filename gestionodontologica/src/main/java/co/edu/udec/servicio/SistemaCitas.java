package co.edu.udec.servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

import co.edu.udec.modelo.Cita;
import co.edu.udec.modelo.EstadoCita;
import co.edu.udec.modelo.Odontologo;
import co.edu.udec.modelo.Paciente;

public class SistemaCitas {

    // Almacena todas las citas en orden de registro
    private List<Cita> citas = new ArrayList<>();

    // Evitar registrar especialidades repetidas
    private Set<String> especialidades = new HashSet<>();

    // Permite buscar citas directamente por su codigo
    private Map<String, Cita> citasPorCodigo = new HashMap<>();

    // Organiza las citas cronologicamente
    private TreeMap<LocalDateTime, List<Cita>> citasPorfecha = new TreeMap<>();

    // Almacena las citas pendientes segun su prioridad
    private PriorityQueue<Cita> citasPendientes;

    // Permite buscar pacientes por su documento
    private Map<String, Paciente> pacientesPorDocumento = new HashMap<>();

    // Permite buscar odontologos por su documento
    private Map<String, Odontologo> odontologosPorDocumento = new HashMap<>();

    // Define el orden de la atencion de las listas
    public SistemaCitas() {

        Comparator<Cita> comparadorPrioridad = Comparator.comparingInt((Cita cita) -> cita.getPrioridad().getNivel())

        .thenComparing(Cita::getFechaHora)
        .thenComparing(Cita::getCodigo);

        citasPendientes = new PriorityQueue<>(comparadorPrioridad);
    }

    // Registra un paciente utilizando su documento como identificador
    public void registrarPaciente(Paciente paciente) throws Exception {

        if (paciente == null) {
            throw new Exception("El paciente no puede ser nulo");
        }

        if (paciente.getDocumento() == null || paciente.getDocumento().isBlank()) {
            throw new Exception("El documento del paciente es obligatorio");
        }

        if (paciente.getNombre() == null || paciente.getNombre().isBlank()) {
            throw new Exception("El nombre del paciente es obligatorio");
        }

        if (pacientesPorDocumento.containsKey(paciente.getDocumento())) {
            throw new Exception("Ya existe un paciente con ese documento");
        }

        pacientesPorDocumento.put(paciente.getDocumento(), paciente);

    }

    // Busca un paciente por su documento
    public Paciente buscarPaciente(String documento) {
        return pacientesPorDocumento.get(documento);
    }

    // Comprueba si un paciente esta registrado
    public boolean existePaciente(String documento) {
        return pacientesPorDocumento.containsKey(documento);
    }

    // Registrar un odontologo y almacena su especialidad
    public void registrarOdontologo(Odontologo odontologo) throws Exception {

        if (odontologo == null) {
            throw new Exception("El odontologo no puede ser nulo");
        }

        if (odontologo.getDocumento() == null || odontologo.getDocumento().isBlank()) {
            throw new Exception("El documento del odontologo es obligatorio");
        }

        if (odontologo.getNombre() == null || odontologo.getNombre().isBlank()) {
            throw new Exception("El nombre del odontólogo es obligatorio.");
        }

        if (odontologo.getEspecialidad() == null || odontologo.getEspecialidad().isBlank()) {
            throw new Exception("La especialidad es obligatoria");
        }

        if (odontologosPorDocumento.containsKey(odontologo.getDocumento())) {
            throw new Exception("Ya existe un odontologo con ese documento");
        }

        odontologosPorDocumento.put(odontologo.getDocumento(), odontologo);

        // Hashset evita almacenar especialidades repetidas
        especialidades.add(odontologo.getEspecialidad().trim());

    }

    // Busca un odontologo por su documento
    public Odontologo buscarOdontologo(String documento) {
        return odontologosPorDocumento.get(documento);
    }

    // Comprueba si un odontologo esta registrado
    public boolean existeOdontologo(String documento) {
        return odontologosPorDocumento.containsKey(documento);
    }

    // Registrar una cita y la incorpora a las colecciones del sistema
    public void registrarCita(Cita cita) throws Exception {

        if (cita == null) {
            throw new Exception("La cita no puede ser nula.");
        }

        if (cita.getCodigo() == null || cita.getCodigo().isBlank()) {
            throw new Exception("El codigo de la cita es obligatorio");
        }

        if (citasPorCodigo.containsKey(cita.getCodigo())) {
            throw new Exception("Ya existe una cita con ese codigo");
        }

        if (cita.getPaciente() == null || !existePaciente(cita.getPaciente().getDocumento())) {
            throw new Exception("El paciente no esta registrado");
        }

        if (cita.getOdontologo() == null || !existeOdontologo(cita.getOdontologo().getDocumento())) {
            throw new Exception("El odontologo no esta registrado");
        }

        if (cita.getFechaHora() == null) {
            throw new Exception("La fecha y hora son obligatorias");
        }

        if (cita.getPrioridad() == null) {
            throw new Exception("La prioridad es obligatoria");
        }

        if (cita.getEstado() == null) {
            throw new Exception("El estado de la cita es obligatorio");
        }

        // Almacenar la cita en la lista general.
        citas.add(cita);

        // Registrar la cita en el mapa utilizando su código.
        citasPorCodigo.put(cita.getCodigo(), cita);

        // Organizar la cita por fecha y hora.
        citasPorfecha
                .computeIfAbsent(cita.getFechaHora(), fecha -> new ArrayList<>())
                .add(cita);

        // Incorporar la cita a la cola si está pendiente.
        if (cita.getEstado() == EstadoCita.PENDIENTE) {
            citasPendientes.offer(cita);
        }
    }

    // Busca una cita mediante su codigo
    public Cita buscarCita(String codigo) {
        return citasPorCodigo.get(codigo);
    }

    // Comprueba si existe una cita con determinado codigo
    public boolean existeCita(String codigo) {
        return citasPorCodigo.containsKey(codigo);
    }
    
}

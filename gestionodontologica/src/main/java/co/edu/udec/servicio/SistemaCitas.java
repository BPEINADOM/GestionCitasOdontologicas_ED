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
}

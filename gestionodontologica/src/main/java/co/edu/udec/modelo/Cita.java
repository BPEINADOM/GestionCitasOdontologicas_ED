package co.edu.udec.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class Cita {

    private final String codigo;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDateTime fechaHora;
    private Prioridad prioridad;
    private EstadoCita estado;


    public Cita(String codigo, Paciente paciente, Odontologo odontologo, LocalDateTime fechaHora, Prioridad prioridad) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHora = fechaHora;
        this.prioridad = prioridad;
        this.estado = EstadoCita.PENDIENTE;
    }


    public String getCodigo() {
        return codigo;
    }


    public Paciente getPaciente() {
        return paciente;
    }


    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    public Odontologo getOdontologo() {
        return odontologo;
    }


    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }


    public LocalDateTime getFechaHora() {
        return fechaHora;
    }


    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }


    public Prioridad getPrioridad() {
        return prioridad;
    }


    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }


    public EstadoCita getEstado() {
        return estado;
    }


    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Cita)) {
            return false;
        }

        Cita cita = (Cita) objeto;

        return codigo.equals(cita.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Cita{" + 
        "codigo='" + codigo + '\'' +
        ", paciente=" + paciente.getNombre() +
        ", odontologo=" + odontologo.getNombre() +
        ", fechaHora=" + fechaHora +
        ", prioridad=" + prioridad +
        ", estado=" + estado +
        '}';
    }
}

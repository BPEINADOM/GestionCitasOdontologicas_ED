package co.edu.udec.modelo;

import java.util.Objects;

public class Paciente {

    private final String documento;
    private String nombre;
    private String telefono;


    public Paciente(String documento, String nombre, String telefono) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
    }


    public String getDocumento() {
        return documento;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals (Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Paciente)) {
            return false;
        }

        Paciente paciente = (Paciente) objeto;

        return documento.equals(paciente.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }

    @Override
    public String toString() {
        return "Paciente{" +
        "documento='" + documento + '\'' +
        ", nombre='" + nombre + '\'' +
        ", telefono='" + telefono + '\'' +
        '}';
    }

    
}

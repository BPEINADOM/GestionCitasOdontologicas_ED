package co.edu.udec.modelo;

public class Odontologo {

    private final String documento;
    private String nombre;
    private String especialidad;

    
    public Odontologo(String documento, String nombre, String especialidad) {
        this.documento = documento;
        this.nombre = nombre;
        this.especialidad = especialidad;
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


    public String getEspecialidad() {
        return especialidad;
    }


    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Odontologo)) {
            return false;
        }

        Odontologo odontologo = (Odontologo) objeto;

        return documento.equals(odontologo.documento);
    }

    @Override
    public String toString() {
        return "Odontolog{" +
        "documento='" + documento + '\'' +
        ", nombre='" + nombre + '\'' +
        ", especialidad='" + especialidad + '\'' +
        '}'; 
    }
}

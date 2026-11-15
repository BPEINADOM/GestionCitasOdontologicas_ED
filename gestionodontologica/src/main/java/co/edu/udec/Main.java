package co.edu.udec;

import co.edu.udec.consola.MenuConsola;
import co.edu.udec.servicio.SistemaCitas;

public class Main 
{
    public static void main( String[] args )
    {
        SistemaCitas sistema = new SistemaCitas();

        try {
            sistema.cargarDatosPrueba();

            MenuConsola menu = new MenuConsola(sistema);
            menu.iniciar();

        } catch (Exception e) {
            System.out.println("Error al iniciar el sistema: " + e.getMessage());
        }
    }
}

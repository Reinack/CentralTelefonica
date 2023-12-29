/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador;
import modelo.Puesto;
import modelo.Trabajador;

/**
 *
 * @author Julio Peraza
 */
public interface IVistaAplicacionTrabajadores {

    void mostrarDatosTrabajadorPuesto(Trabajador t, Puesto p);

    void mostrarLlamadaEnCurso(Trabajador t, Puesto p);

    void mostrarLlamadaFinalizada();

    void mostrarMensaje(String msj);

    boolean confirmarSalida();
    
    void mostrarMensajePantalla(String cadena);
}

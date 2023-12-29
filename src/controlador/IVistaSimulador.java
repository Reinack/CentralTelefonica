/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Llamada;
import modelo.Sector;

/**
 *
 * @author peraza
 */
public interface IVistaSimulador {

    void agregarDigitoYMostrar(String n);

    void mostrarMensajeCentralTelefonica(String mensaje);

    void mostrarNumeroIngresado(String string);

    void listarSectores(ArrayList<Sector> listaSectores);

    void mostrarMensajeLlamadaFinalizada(Llamada llamadaActual);

    void solicitarCedula();

    void mostrarMensajeLlamadaEnCurso(Llamada llamadaActual);

}

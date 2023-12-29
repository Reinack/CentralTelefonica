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
 * @author Julio Peraza
 */
public interface IVistaMonitoreo {

    public void borrarDetalles();
    public void mostrarLlamadas(ArrayList<Llamada> listaLlamadas,boolean todos);
    public void mostrarSectores(ArrayList<Sector> sectores);

}

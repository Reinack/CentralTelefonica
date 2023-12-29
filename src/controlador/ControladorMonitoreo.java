/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import modelo.Fachada;
import modelo.Sector;
import observador.Observable;
import observador.Observador;

/**
 *
 * @author Julio Peraza
 */
public class ControladorMonitoreo implements Observador {

    private IVistaMonitoreo vista;
    private Sector filtro;
    private boolean todos;
    
    public ControladorMonitoreo(IVistaMonitoreo vista) {
        this.vista = vista;
        Fachada.getInstancia().agregarObservador(this);
    }

    @Override
    public void actualizar(Object evento, Observable origen) {

        vista.mostrarSectores(listarSectores());
        if (filtro != null) {
            vista.mostrarLlamadas(filtro.getListaLlamadasHistoricoSector(),todos);

        }

    }

    public ArrayList<Sector> listarSectores() {
        return Fachada.getInstancia().getListaSectores();
    }

    public void seleccion(Sector s, boolean todos) {
        filtro = s;
        if (filtro == null) {
            vista.borrarDetalles();
        } else {
                vista.mostrarLlamadas(filtro.getListaLlamadasHistoricoSector(),todos);
        }
    }


    public void listarLlamadasDeTodosLosSectores() {
        vista.mostrarLlamadas(Fachada.getInstancia().getListaLlamadasHistorico(),true);
    }

    public void todos(boolean seleccionoTodos) {
        todos = seleccionoTodos;
    }

    public void quitarObservador() {
        Fachada.getInstancia().quitarObservador(this);
    }



}

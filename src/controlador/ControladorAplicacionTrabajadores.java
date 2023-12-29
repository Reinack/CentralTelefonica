package controlador;

import modelo.Fachada;
import modelo.Llamada;
import modelo.Puesto;
import modelo.Sector;
import modelo.Trabajador;
import observador.Observable;
import observador.Observador;
import vistas.VistaAlertLlamadaFinalizada;
import vistas.VistaAplicacionTrabajadores;

/**
 *
 * @author Julio Peraza
 */
public class ControladorAplicacionTrabajadores implements Observador {

    private Trabajador trabajador;
    private Puesto puesto;
    private Llamada llamada;
    private IVistaAplicacionTrabajadores vista;

    public ControladorAplicacionTrabajadores(Trabajador trabajador, Puesto puesto, VistaAplicacionTrabajadores v) {
        this.trabajador = trabajador;
        this.puesto = puesto;
        this.vista = v;
        Fachada.getInstancia().agregarObservador(this);
    }

    @Override
    public void actualizar(Object evento, Observable origen) {
        if (evento.equals(Fachada.eventos.nuevallamada)) {
            llamada = puesto.getLlamadaActual();
            atenderLlamada();
        } else if (evento.equals(Fachada.eventos.llamadaTermino)) {
            if (llamada != null) {
                if (llamada.getEstadoSin().equals("Finalizada")) {
                    vista.mostrarLlamadaFinalizada();
                }
            }
        }

    }

    public boolean salirDeLaAplicacion() {
        if (llamada != null) {
            if (vista.confirmarSalida()) {
                finalizarLlamada();
                Fachada.getInstancia().logout(trabajador, puesto);
                Fachada.getInstancia().quitarObservador(this);
                return true;
            }
        } else {
            Fachada.getInstancia().logout(trabajador, puesto);
            Fachada.getInstancia().quitarObservador(this);
            return true;
        }
        return false;

    }

    private void atenderLlamada() {
        if (llamada != null && llamada.getAtendida() == null) {
            Fachada.getInstancia().agregarLlamadaAListaHistorico(llamada);
            vista.mostrarLlamadaEnCurso(trabajador, puesto);
        }

    }

    public void agregarDescripcionLlamada(String text) {
        if (llamada != null) {
            llamada.setDescripcion(text);

        }
    }

    public void finalizarLlamada() {
        if (llamada != null) {
            Fachada.getInstancia().finalizarLlamada(llamada);
        }

    }

    public int llamadasAtendidas() {

        return puesto.cantidadLlamadasAtendidasTrabActual();
    }

    public long llamadasTiempoPromedio() {
        return puesto.tiempoPromedioLlamadas();

    }

    public void mostrarAlertLlamadaFinalizada(java.awt.Dialog parent) {
        if (llamada != null) {
            new VistaAlertLlamadaFinalizada(parent, false, llamada).setVisible(true);
        }
    }

    public void agregarSiguienteLlamada() {
        Fachada.getInstancia().asignarSiguienteLlamada(trabajador.getSector());
    }

    public void limpiarLlamadaActual() {
        llamada = null;
    }
}

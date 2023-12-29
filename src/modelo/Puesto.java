package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Puesto {

    private int idPuesto;
    private Date tiempoPromedio;
    private Trabajador trabajador;
    private Sector sector;

    private ArrayList<Llamada> listaAtendidasXTrabajadorActual = new ArrayList();
    private Llamada llamadaActual = null;

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;

    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public Puesto(Sector s, int id) {
        this.sector = s;
        this.idPuesto = id;

    }

    public ArrayList<Llamada> getListaAtendidasXTrabajadorActual() {
        return listaAtendidasXTrabajadorActual;
    }

    public int cantidadLlamadasAtendidasTrabActual() {
        return listaAtendidasXTrabajadorActual.size();
    }

    public long tiempoPromedioLlamadas() {
        long total = 0;

        for (Llamada llamada : listaAtendidasXTrabajadorActual) {
            total += llamada.duracionAtendidaFin();
        }

        if (cantidadLlamadasAtendidasTrabActual() != 0) {
            return total / cantidadLlamadasAtendidasTrabActual();
        }

        return total;
    }

    public Llamada getLlamadaActual() {
        return llamadaActual;
    }

    public void setLlamadaActual(Llamada llamadaActual) {
        this.llamadaActual = llamadaActual;
        if (llamadaActual != null) {
            listaAtendidasXTrabajadorActual.add(llamadaActual);
        }
    }

    public void limpiarListaLlamadas() {
        listaAtendidasXTrabajadorActual.clear();
    }

    public boolean estaDisponibleParaLlamada() {
        return trabajador != null && llamadaActual == null;
    }

    public long tiempoPromedioEsperaPuesto() {
        long tiempo = 0;
        for (Llamada l : listaAtendidasXTrabajadorActual) {
            tiempo += l.espera();
        }

        return tiempo / listaAtendidasXTrabajadorActual.size();
    }

}

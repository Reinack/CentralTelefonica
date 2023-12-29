package modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import observador.Observable;

public class Sector {

    private String nombre;

    private int numero;

    private int cantPuestos;

    private Queue<Llamada> colaLlamadas = new LinkedList<Llamada>();

    private ArrayList<Trabajador> listaTrabajadores = new ArrayList();

    private ArrayList<Puesto> listaPuestos = new ArrayList();

    private ArrayList<Llamada> listaLlamadasHistoricoSector = new ArrayList();

    public Sector(String nombre) {
        this.nombre = nombre;
    }

    public boolean agregarTrabajador(Trabajador t) {
        if (!estaTrabajador(t)) {
            listaTrabajadores.add(t);
            return true;
        }
        return false;
    }

    public Puesto hayPuestosDisponibles() {
        for (Puesto p : listaPuestos) {
            if (p.estaDisponibleParaLlamada()) {
                return p;
            }
        }
        return null;

    }

    public void trabajadoresDisponibles() throws ObligatorioException {
        boolean hayPuesto = false;

        for (Puesto p : listaPuestos) {
            if (p.getTrabajador() != null) {
                hayPuesto = true;
            }
        }
        if (!hayPuesto) {
            throw new ObligatorioException("Sector no disponible");

        }
    }


    public Sector(String nombre, int numero, int cantPuestos) {
        this.nombre = nombre;
        this.numero = numero;
        this.cantPuestos = cantPuestos;
        inicializarPuestos();

    }

    public boolean estaTrabajador(Trabajador u) {
        for (Trabajador trab : listaTrabajadores) {
            if (trab.equals(u)) {
                return true;
            }
        }
        return false;
    }

    public Puesto asignarPuesto(Trabajador u) throws ObligatorioException {
        
        for (Puesto p : listaPuestos) {
            if (p.getTrabajador() == null) {
                p.setTrabajador(u);
                return p;
            }
        }
        throw new ObligatorioException("No hay puesto disponible");

    }

    private void inicializarPuestos() {
        for (int i = 0; i < cantPuestos; i++) {
            Puesto puesto = new Puesto(this, i + 1);
            listaPuestos.add(puesto);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public boolean hayLlamadaEnEspera() {
        return !colaLlamadas.isEmpty();
    }

    public Puesto asignarLlamada() {
        if (hayLlamadaEnEspera()) {
            for (Puesto p : listaPuestos) {
                if (p.estaDisponibleParaLlamada()) {
                    Llamada actual = colaLlamadas.poll();
                    actual.setPuesto(p);
                    actual.setTrabajador(p.getTrabajador());
                    p.setLlamadaActual(actual);
                    actual.actualizarEstado();
                    listaLlamadasHistoricoSector.add(actual);
                    actual.setIdLlamada(listaLlamadasHistoricoSector.size());
                    Fachada.getInstancia().avisar(Fachada.eventos.nuevallamada);
                    return p;
                }
            }
        }
        return null;
    }

    public void asignarLlamada(Llamada l, Puesto p) {

        l.setPuesto(p);
        l.setTrabajador(p.getTrabajador());
        p.setLlamadaActual(l);
        listaLlamadasHistoricoSector.add(l);
        l.setIdLlamada(listaLlamadasHistoricoSector.size());
        Fachada.getInstancia().avisar(Fachada.eventos.nuevallamada);

    }

    public void ponerLlamadaEnEspera(Llamada l) {
        colaLlamadas.add(l);

    }

    public void finalizarLlamada(Llamada l) {
        l.setFin(); // setea a la fecha de la llamada en la fecha actual
        l.getCliente().actualizarSaldo(l.calcularCosto());
        l.setSaldoAlFinalizarLlamada(l.getCliente().getSaldo());
        l.getPuesto().setLlamadaActual(null);
        Fachada.getInstancia().avisar(Fachada.eventos.llamadaTermino);

    }

    public int getNumero() {
        return numero;
    }

    public int posicionLlamadaEnCola(Llamada llam) {
        int contador = 1;
        for (Llamada l : colaLlamadas) {
            if (l.equals(llam)) {
                return contador;
            }
            contador++;
        }

        return -1;
    }

    public long tiempoPromedioMinutosLlamadaEnEsperaSector() {
        long tiempo = 0;
        int c = 0;
        for (Puesto p : listaPuestos) {
            if (p.getTrabajador() != null) {
                tiempo += p.tiempoPromedioEsperaPuesto();
                c++;
            }
        }
        if (c > 0) {
            return (tiempo / c) * cantidadLlamadasEnEspera() / 60;
        } else {
            return -1;
        }
    }

    public int cantidadLlamadasEnEspera() {

        return colaLlamadas.size();
    }

    public void desEncolarLlamada(Llamada l) {
        Fachada.getInstancia().restarLlamadasTotales();
        colaLlamadas.remove(l);
    }

    @Override
    public String toString() {
        return nombre;
    }

    public ArrayList<Llamada> getListaLlamadasHistoricoSector() {
        return listaLlamadasHistoricoSector;
    }

}

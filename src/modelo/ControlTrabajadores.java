package modelo;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;

public class ControlTrabajadores {

    private ArrayList<Trabajador> listaTrabajadores = new ArrayList<Trabajador>();
    private ArrayList<Sector> listaSectores = new ArrayList<Sector>();
    private ArrayList<Llamada> listaLlamadasHistorico = new ArrayList<Llamada>();

    private int cantidadLlamadasTotales = 0;

    public void agregarTrabajador(Trabajador t) {
        listaTrabajadores.add(t);
        t.getSector().agregarTrabajador(t);
    }

    public void agregarSector(Sector s) {
        listaSectores.add(s);
    }

    public void logout(Trabajador t, Puesto p) {
        p.limpiarListaLlamadas();
        p.setTrabajador(null);
    }

    public Trabajador login(String cedula, String pwd) throws ObligatorioException {       
        for (Trabajador trab : listaTrabajadores) {
            if (trab.getCedula().equals(cedula) && trab.getPassword().equals(pwd)) {
                return trab;
            }
        }
        throw new ObligatorioException("Acceso denegado");
    }

    public Puesto asignarPuestoATrabajador(Trabajador u) throws ObligatorioException {        
        for (Sector sector : listaSectores) {
            if (sector.estaTrabajador(u)) {
                return sector.asignarPuesto(u);
            }
        }
        throw null;
    }

    public int getCantidadLlamadasTotales() {
        return cantidadLlamadasTotales;
    }

    public void agregarLlamada(Llamada l) throws ObligatorioException {

        Puesto p = l.getSector().hayPuestosDisponibles();

        if (p != null) {
            l.getSector().asignarLlamada(l, p);
        } else {
            l.getSector().ponerLlamadaEnEspera(l);
            throw new ObligatorioException("Aguarde en línea, ud. se encuentra a" + l.getSector().posicionLlamadaEnCola(l) + " llamadas de ser\n atendido, la espera estimada es de" + l.getSector().tiempoPromedioMinutosLlamadaEnEsperaSector() + " minutos");
        }

    }

    public void finalizarLlamada(Llamada l) {
        l.getSector().finalizarLlamada(l);
        //l.getCliente().actualizarSaldo(l.calcularCosto());
        //l.setSaldoAlFinalizarLlamada(l.getCliente().getSaldo());
        restarLlamadasTotales();
    }

    public void asignarSiguienteLlamada(Sector s) {
        s.asignarLlamada();
    }

    public ArrayList<Sector> getListaSectores() {
        return listaSectores;
    }

    public Sector obtenerSectorPorId(String id) throws ObligatorioException {
        for (Sector s : listaSectores) {
            if (s.getNumero() == parseInt(id)) {
                return s;
            }

        }
        throw new ObligatorioException("Sector no valido");
    }

    public void restarLlamadasTotales() {
        cantidadLlamadasTotales--;
    }

    public void sumarLlamadasTotales() {
        cantidadLlamadasTotales++;

    }

    public void agregarLlamadaAListaHistorico(Llamada llamada) {
        listaLlamadasHistorico.add(llamada);
    }

    public ArrayList<Llamada> getListaLlamadasHistorico() {
        return listaLlamadasHistorico;
    }
    
    

}

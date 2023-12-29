package modelo;

import java.util.ArrayList;
import observador.Observable;

public class Fachada extends Observable{
    
    private static Fachada instancia = new Fachada();
    
    public static Fachada getInstancia(){
        return instancia;
    }
    
    private Fachada(){
    }
    
    private ControlClientes cl = new ControlClientes();
    private ControlTrabajadores ct = new ControlTrabajadores();
   
    public enum eventos {
        nuevallamada,
        llamadaTermino
    }
    
    public void agregarCliente(Cliente c) {
        cl.agregarCliente(c);
    }

    public void agregarTrabajador(Trabajador t) {
        ct.agregarTrabajador(t);
    }

    public void agregarSector(Sector s) {
        ct.agregarSector(s);
    }

    public void setLlamadasMaximas(int llamadasMaximas) {
        cl.setLlamadasMaximas(llamadasMaximas);
    }

    public void setCosteLlamada(int costeLlamada) {
        cl.setCosteLlamada(costeLlamada);
    }

    public void logout(Trabajador t, Puesto p) {
        ct.logout(t,p);
    }

    public Trabajador login(String cedula, String pwd) throws ObligatorioException {
        return ct.login(cedula, pwd);
    }

    public Puesto asignarPuestoATrabajador(Trabajador u) throws ObligatorioException {
        return ct.asignarPuestoATrabajador(u);
    }

    public int getLlamadasMaximas() {
        return cl.getLlamadasMaximas();
    }

    public int getCantidadLlamadasTotales() {
        return ct.getCantidadLlamadasTotales();
    }

    //si no hay puestos disponibles, pone la llamada en espera, retorna false
    public void agregarLlamada(Llamada l) throws ObligatorioException {
        ct.agregarLlamada(l);
    }

    public void finalizarLlamada(Llamada l) {
        ct.finalizarLlamada(l);
    }
    
    public void asignarSiguienteLlamada(Sector s){
        ct.asignarSiguienteLlamada(s);
    }

    public Llamada crearLlamada() throws ObligatorioException {
        return cl.crearLlamada();
    }

    public Cliente obtenerClientePorCi(String cedula) {
        return cl.obtenerClientePorCi(cedula);
    }

    public ArrayList<Sector> getListaSectores() {
        return ct.getListaSectores();
    }

    public Sector obtenerSectorPorId(String id) throws ObligatorioException {
        return ct.obtenerSectorPorId(id);
    }

    public void restarLlamadasTotales() {
        ct.restarLlamadasTotales();
    }

    public double getCosteLlamada() {
        return cl.getCosteLlamada();
    }

    public void sumarLlamadasTotales() {
       ct.sumarLlamadasTotales();
    }

    public void agregarLlamadaAListaHistorico(Llamada llamada) {
        llamada.setAtendida();
        ct.agregarLlamadaAListaHistorico(llamada);
    }

    public ArrayList<Llamada> getListaLlamadasHistorico() {
        return ct.getListaLlamadasHistorico();
    }


    
    
    
    
    

}

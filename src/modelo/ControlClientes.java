package modelo;

import java.util.ArrayList;

public class ControlClientes {

    private int llamadasMaximas;

    private int costeLlamada;

    private ArrayList<Llamada> listaLlamadas = new ArrayList();

    private ArrayList<Cliente> listaClientes = new ArrayList();

    //Clientes - Información básica: cédula de identidad, nombre completo, y saldo
    public void agregarCliente(Cliente c) {
        listaClientes.add(c);
    }

    public void setLlamadasMaximas(int llamadasMaximas) {
        this.llamadasMaximas = llamadasMaximas;
    }

    public void setCosteLlamada(int costeLlamada) {
        this.costeLlamada = costeLlamada;
    }

    public int getLlamadasMaximas() {
        return llamadasMaximas;
    }
    
    
    
    
    public void agregarLlamada(Llamada l) throws ObligatorioException {
       Fachada.getInstancia().agregarLlamada(l);
       
    }

    public void finalizarLlamada(Llamada l) {
        Fachada.getInstancia().finalizarLlamada(l);
    }
    
    public Llamada crearLlamada() throws ObligatorioException {
        Fachada.getInstancia().sumarLlamadasTotales();
        if (Fachada.getInstancia().getCantidadLlamadasTotales() > llamadasMaximas) {
            Fachada.getInstancia().restarLlamadasTotales();
            throw new ObligatorioException("Comuniquese mas tarde");
        }

        Llamada llamada = new Llamada();

        return llamada;
        
                
          
        
    }
    
    public Cliente obtenerClientePorCi(String cedula){
        for (Cliente c : listaClientes) {        
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    
    }
    
    public double getCosteLlamada(){
        return costeLlamada;
    }
}

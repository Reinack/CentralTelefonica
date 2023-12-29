package modelo;

import java.util.ArrayList;

public class Cliente {

    private TipoCliente tipoCliente;
    private String cedula;
    private String nombre;
    private double saldo;
    

    public Cliente(String cedula, String nombre, double saldo, TipoCliente tc) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.saldo = saldo;
        this.tipoCliente = tc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public double actualizarSaldo(double costoLlamada){
        double saldoActualizado = saldo-costoLlamada;
        setSaldo(saldoActualizado);
        return saldo;
    }
            
   public double getDescuento(){
        return tipoCliente.getDescuento();
    }   
    public String getTipoCliente() {
        return tipoCliente.getNombre();
    }
    

}

package modelo;

public class Gestor extends TipoCliente{

    public Gestor() {

        super("Gestor");
    }
    
    

    @Override
    public double getDescuento() {
        return 0.5; 
    }
   
}
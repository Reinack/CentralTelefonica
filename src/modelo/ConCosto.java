package modelo;

public class ConCosto extends TipoCliente{

    public ConCosto() {
        super("Con Costo");
    }
    
    @Override
    public double getDescuento() {
        return 0.5;
    }
    
}

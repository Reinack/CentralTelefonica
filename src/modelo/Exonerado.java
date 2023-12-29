package modelo;

public class Exonerado extends TipoCliente{

    public Exonerado() {

        super("Exonerado");
    }

    @Override
    public double getDescuento() {
        return 1;
    }

    
}

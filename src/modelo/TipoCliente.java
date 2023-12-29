package modelo;

public abstract class TipoCliente {

    private String nombre;

    public TipoCliente(String nombre) {
        this.nombre = nombre;
    }
    
    
    

    public String getNombre() {
        return nombre;
    }


    
    

    public double getDescuento() {
        return -1;
    }
}

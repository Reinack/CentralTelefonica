package modelo;

import java.util.ArrayList;

public class Trabajador {

    private String cedula;
    private String password;
    private String nombreCompleto;
    private Sector sector;
    private ArrayList<Llamada> listaLlamadas = new ArrayList();

    public Trabajador(String cedula, String password, String nombreCompleto) {
        this.cedula = cedula;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
    }

    public String getCedula() {
        return cedula;
    }

    public String getPassword() {
        return password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }
    
    

    
    
    
    
    


}

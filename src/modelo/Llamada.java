package modelo;

import java.time.LocalDateTime;
import java.util.Date;
import javax.print.attribute.standard.DateTimeAtCompleted;

public class Llamada {

    private String descripcion;

    private double saldoAlFinalizarLlamada;

    private Sector sector;

    private Date inicio;

    private Date atendida;

    private Date fin;

    private static double costeFijoXSegundo;

    private Puesto puesto;

    private Cliente cliente;

    private String estado;
    
    private int idLlamada;

    private Trabajador trabajador;
    
    public Llamada(Sector sector, Cliente cliente) {
        this.sector = sector;
        this.inicio = new Date();
        this.cliente = cliente;
    }

    public Llamada() {
        inicio = new Date();
        estado = "Creando";
        costeFijoXSegundo = Fachada.getInstancia().getCosteLlamada();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSaldoAlFinalizarLlamada() {
        return saldoAlFinalizarLlamada;
    }

    public void setSaldoAlFinalizarLlamada(double deuda) {
        this.saldoAlFinalizarLlamada = deuda;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getAtendida() {
        return atendida;
    }

    public void setAtendida() {
        this.atendida = new Date();
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }
    
    
    public static double getCosteFijoXSegundo() {
        return costeFijoXSegundo;
    }

    public static void setCosteFijoXSegundo(int costeFijoXSegundo) {
        Llamada.costeFijoXSegundo = costeFijoXSegundo;
    }

    public long duracionAtendidaFin() {
        return (fin.getTime() - atendida.getTime()) / 1000;
    }

    public void setFin() {
        this.fin = new Date();
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void actualizarEstado() {

        if (this.cliente != null && this.sector != null) {
            if (this.atendida == null) {
                this.estado = "En espera";
            } else if (this.fin == null) {
                this.estado = "Atendida";
            } else {
                this.estado = "Finalizada";
            }

        }

    }

    public String getEstadoSin() {
        
        if (this.cliente != null && this.sector != null) {
            if (this.puesto == null) {
                return "Creando";
            } else if (this.atendida == null) {
                return "En espera";
            } else if (this.fin == null) {
                return "Atendida";
            } else {
                return "Finalizada";
            }

        } else {
            return "No contemplado";
        }
    }

    public long espera() {
        return (atendida.getTime() - inicio.getTime()) / 1000;
    }

    public long duracionTotal() {
        return (fin.getTime() - inicio.getTime()) / 1000;
    }

    public double calcularCosto() {

        double c = getCosteFijoXSegundo() * duracionAtendidaFin();

        if ("Exonerado".equals(cliente.getTipoCliente())) {
            c = 0;
        }

        if ("Con Costo".equals(cliente.getTipoCliente())) {

            if (espera() > 60) {
                c = c * cliente.getDescuento();
            }
        }

        if ("Gestor".equals(cliente.getTipoCliente())) {

            if (duracionAtendidaFin() < 180) {
                c = c * cliente.getDescuento() - espera() * costeFijoXSegundo;
            }
        }

        if (c < 0) {
            c = 0;
        }

        return c;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(int idLlamada) {
        this.idLlamada = idLlamada;
    }
    
    

}

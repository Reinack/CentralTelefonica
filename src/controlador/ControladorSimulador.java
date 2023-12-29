package controlador;
import modelo.Cliente;
import modelo.Fachada;
import modelo.Llamada;
import modelo.ObligatorioException;
import modelo.Sector;
import observador.Observable;
import observador.Observador;

/**
 *
 * @author Julio Peraza
 */
public class ControladorSimulador implements Observador {

    private IVistaSimulador vista;
    private Llamada llamadaActual;
    String numerosIngresadosPorUsuario = "";

    public ControladorSimulador(IVistaSimulador vista) {
        this.vista = vista;
        Fachada.getInstancia().agregarObservador(this);

    }

    public Cliente obtenerClientePorCi(String ci) {
        Cliente c = Fachada.getInstancia().obtenerClientePorCi(ci);
        if (c != null) {
            llamadaActual.setCliente(c);
        }
        return c;
    }

 
    @Override
    public void actualizar(Object evento, Observable origen) {
        if (llamadaActual != null) {
            if (evento.equals(Fachada.eventos.nuevallamada) && llamadaActual.getEstadoSin().equals("Atendida")) {
                vista.mostrarMensajeLlamadaEnCurso(llamadaActual);
            } else if (evento.equals(Fachada.eventos.llamadaTermino) && (llamadaActual.getEstadoSin().equals("Finalizada"))) {
                vista.mostrarMensajeLlamadaFinalizada(llamadaActual);
                borrarLlamada();
            }
        }

    }

    public void crearLlamada() {
        if (llamadaActual == null) {
            try {                
                llamadaActual = Fachada.getInstancia().crearLlamada();
                vista.solicitarCedula();
            } catch (ObligatorioException ex) {
                vista.mostrarMensajeCentralTelefonica(ex.getMessage());
            }
        }
    }

    public void borrarLlamada() {
        llamadaActual = null;
    }

    public void seleccionarSectorYLlamar(String idSector) {
        if (llamadaActual != null) {
            try {
                Sector s = Fachada.getInstancia().obtenerSectorPorId(idSector);
                llamadaActual.setSector(s);
                s.trabajadoresDisponibles();
                Fachada.getInstancia().agregarLlamada(llamadaActual);
            } catch (ObligatorioException ex) {
                vista.mostrarMensajeCentralTelefonica(ex.getMessage());

            }
        }
    }

  
    private void finalizarCasoDeUso() {
        borrarLlamada();
        vaciarNumerosIngresadosPorElUsuario();
        vista.mostrarNumeroIngresado("");

    }

    public void vaciarNumerosIngresadosPorElUsuario() {
        numerosIngresadosPorUsuario = "";

    }

    public String getNumerosIngresadosPorElUsuario() {
        return numerosIngresadosPorUsuario;
    }

    public void agregarNumero(String n) {
        if (llamadaActual != null) {
            numerosIngresadosPorUsuario += n;
        }
    }

    public void finalizarLlamada() {
        if (llamadaActual != null) {
            if (llamadaActual.getEstadoSin().equals("Atendida")) { // estado atendida
                llamadaActual.setFin();
                Fachada.getInstancia().finalizarLlamada(llamadaActual);                    
            } else if (llamadaActual.getEstadoSin().equals("En espera")) {

                llamadaActual.getSector().desEncolarLlamada(llamadaActual);
                vista.mostrarMensajeCentralTelefonica("Llamada desencolada");
                

            }else if (llamadaActual.getEstadoSin().equals("Creando")) {
                
            }
        }
    }

    public void ingresoDeDigito(String n) {
        if (llamadaActual != null && llamadaActual.getSector() == null) {
            if (!n.equals("#")) {
                if (llamadaActual.getEstado().equals("Creando")) {
                    vista.agregarDigitoYMostrar(n);
                } else {
                    seleccionarSectorYLlamar(n);

                }
            } else {
                // cliente ingresó "#" 

                if (!llamadaActual.getEstado().equals("Cedula asignada")) {

                    if (obtenerClientePorCi(getNumerosIngresadosPorElUsuario()) != null) {
                        vaciarNumerosIngresadosPorElUsuario();
                        vista.mostrarNumeroIngresado("");
                        vista.listarSectores(Fachada.getInstancia().getListaSectores());
                        llamadaActual.setEstado("Cedula asignada");
                    } else {
                        vista.mostrarMensajeCentralTelefonica("Cliente no registrado");
                        finalizarCasoDeUso();
                    }
                }

            }
        }
    }
    
    public void quitarObservador(){
        Fachada.getInstancia().quitarObservador(this);
    }

}

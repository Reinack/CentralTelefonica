package controlador;
import modelo.Fachada;
import modelo.ObligatorioException;
import modelo.Puesto;
import modelo.Trabajador;
import vistas.VistaAplicacionTrabajadores;

/**
 *
 * @author Julio Peraza
 */
public class ControladorLogin {

    private IVistaLogin vista;

    public ControladorLogin(IVistaLogin vista) {
        this.vista = vista;
    }

    public void llamarLogin(String cedula, String pwd) {

        try {
            Trabajador u = Fachada.getInstancia().login(cedula, pwd);
            //Prox caso de uso
            Puesto p = Fachada.getInstancia().asignarPuestoATrabajador(u);
            atenderLlamadas(u, p);

        } catch (ObligatorioException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }

    }

    public void atenderLlamadas(Trabajador u, Puesto p) {
        new VistaAplicacionTrabajadores(null, false, u, p).setVisible(true);

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Julio Peraza
 */
public class Sesion {
    private Trabajador trabajador;
    private Date fechaIgreso = new Date();

    public Sesion(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public Date getFechaIgreso() {
        return fechaIgreso;
    }


}

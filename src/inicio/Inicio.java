/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;

import UI.AplicacionObligatorio;

/**
 *
 * @author Julio Peraza
 */
public class Inicio {
    public static void main(String[] args) {
        DatosPrueba.cargar();
        //inicializar login
        new AplicacionObligatorio().setVisible(true);
    }
        
}

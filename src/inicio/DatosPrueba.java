/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;

import modelo.Cliente;
import modelo.ConCosto;
import modelo.Exonerado;
import modelo.Fachada;
import modelo.Gestor;
import modelo.Sector;
import modelo.Trabajador;


/**
 *
 * @author Julio Peraza
 */
public class DatosPrueba {

    public static void cargar() {
        Fachada logica = Fachada.getInstancia();

        Exonerado exonerado = new Exonerado();
        Gestor gestor = new Gestor();
        ConCosto conCosto = new ConCosto();
        
        logica.agregarCliente(new Cliente("11", "Mario Uno", 111, exonerado));
        logica.agregarCliente(new Cliente("22", "Maria Perez", 222, gestor));
        logica.agregarCliente(new Cliente("33333333", "Fulano Detal", 333, gestor));
        logica.agregarCliente(new Cliente("44444444", "Pablo Diaz", 444, gestor));
        logica.agregarCliente(new Cliente("55", "Rodrigo Ferreira", 100, conCosto));
        logica.agregarCliente(new Cliente("66666666", "Carla De La Rua", 666, conCosto));

        Sector sector1 = new Sector("Sector 1", 1, 1);
        logica.agregarSector(sector1);
        
        Trabajador trabajador1 = new Trabajador("1", "1", "Luis Suarez");
        trabajador1.setSector(sector1);
        logica.agregarTrabajador(trabajador1);
        
        Sector sector2 = new Sector("Sector 2", 2, 3);
        logica.agregarSector(sector2);
        Trabajador trabajador2 = new Trabajador("2", "2", "Carlos Tejera");    
        trabajador2.setSector(sector2);
        logica.agregarTrabajador(trabajador2);
        
        Trabajador trabajador3 =new Trabajador("3", "3", "Matias Alonso");
        trabajador3.setSector(sector1);
        logica.agregarTrabajador(trabajador3);

        
        Trabajador trabajador4 =new Trabajador("4", "4", "Emiliano Diaz");
        trabajador4.setSector(sector2);
        logica.agregarTrabajador(trabajador4);
        
        Sector sector3 = new Sector("Sector 3", 3, 10);
        logica.agregarSector(sector3);
        
        
        Trabajador trabajador5 =new Trabajador("5", "5", "Lucia Pereira");
        trabajador5.setSector(sector3);
        logica.agregarTrabajador(trabajador5);
        
        
        Trabajador trabajador6 =new Trabajador("6", "6", "Marta Sanchez");
        trabajador6.setSector(sector3);
        logica.agregarTrabajador(trabajador6);        
        
        
        Trabajador trabajador7 =new Trabajador("7", "7", "Teresa Rojas");
        trabajador7.setSector(sector3);
        logica.agregarTrabajador(trabajador7);  
        

        logica.agregarSector(new Sector("Sector 4", 4, 4));
        logica.agregarSector(new Sector("Sector 5", 5, 7));

        logica.setLlamadasMaximas(5);

        logica.setCosteLlamada(1);
        
           
    }
}


    


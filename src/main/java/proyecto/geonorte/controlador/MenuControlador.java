/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import proyecto.geonorte.vista.ClienteJFrame;
import proyecto.geonorte.vista.MenuJFrame;

/**
 *
 * @author FP DAM
 */
public class MenuControlador {
    
    private MenuJFrame menu;
    private ClienteJFrame cliente;
    
    
    public MenuControlador () {
        menu = new MenuJFrame();    //instanciamos la vista
        inicializarEventos();       //inicializamos el metodo encargado de escuchar
        
        menu.setVisible(true);      //mostramos la vista
    }
    
    //escuchamos cada boton, cada uno nos lleva a su controlador correspondiente
    private void inicializarEventos() {
        menu.getjButtonCliente().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                new ClienteControlador(menu);
                menu.setVisible(false);         //ocualtamos la interfaz de menu
            }
        });
        
        menu.getjButtonServicio().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                System.out.println("Has pulsado Servicio");
            }
        });
        
        menu.getjButtonProyecto().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                System.out.println("Has pulsado Proyecto");
            }
        });
        
        menu.getjButtonContrato().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                System.out.println("Has pulsado Contrato");
            }
        });
        
        menu.getjButtonP_Enlace().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                System.out.println("Has pulsado Persona enlace");
            }
        });
        
        menu.getjButtonEmpleado().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                System.out.println("Has pulsado Trabajador");
            }
        });
        
        menu.getjButtonSalir().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                System.out.println("Has pulsado Salir");
                System.exit(0);
            }
        });
    }   
}
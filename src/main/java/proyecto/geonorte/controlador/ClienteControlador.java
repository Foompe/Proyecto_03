/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import proyecto.geonorte.vista.ClienteJFrame;
import proyecto.geonorte.vista.ConsultaClienteJPanel;
import proyecto.geonorte.vista.ListaClientesJPanel;
import proyecto.geonorte.vista.MenuJFrame;
import proyecto.geonorte.vista.ModificClienteJPanel;
import proyecto.geonorte.vista.NuevoClienteJPanel;

/**
 *
 * @author FP DAM
 */
public class ClienteControlador {
    
       
    private MenuJFrame menu;
    private ClienteJFrame cliente;
    private ListaClientesJPanel lisCliente;
    private NuevoClienteJPanel nuevoCliente;
    private ModificClienteJPanel modificCliente;
    private ConsultaClienteJPanel consultaCliente;
    
    
    
    
    public ClienteControlador() {
        cliente = new ClienteJFrame();
        inicializarEventos();       //inicializamos el metodo encargado de escuchar
       
        cliente.setVisible(true);
        
        lisCliente = new ListaClientesJPanel();
        mostrarPanelCentral(lisCliente);
    
        
    }
    
    
    //metodo para mostrar pantallas en el panel central
    public void mostrarPanelCentral(JPanel panel) {
        
        panel.setSize(1000,600);
        panel.setLocation(0,0);
        
        cliente.getPanelPantalla().removeAll();
        cliente.getPanelPantalla().add(panel, BorderLayout.CENTER);
        cliente.getPanelPantalla().revalidate();
        cliente.getPanelPantalla().repaint();
    }
    
    private void inicializarEventos() {
        cliente.getjButtonNuevoCliente().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                
                mostrarPanelCentral(nuevoCliente);
                
            }
        });
        
        
        
        
    }
}

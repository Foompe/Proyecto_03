/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import proyecto.geonorte.modelo.dao.ClienteDAO;
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
    private NuevoClienteControlador nuevoCliente;
    private ModificClienteJPanel modificCliente;
    private ConsultaClienteJPanel consultaCliente;
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    
    
    
    public ClienteControlador(MenuJFrame menuVista) {
        this.menu = menuVista;
        cliente = new ClienteJFrame();
        inicializarEventos();       //inicializamos el metodo encargado de escuchar
       
        cliente.setVisible(true);
        
        lisCliente = new ListaClientesJPanel();
        mostrarPanelCentral(lisCliente);  
    }
    
    
    //metodo para mostrar pantallas en el panel central
    public void mostrarPanelCentral(JPanel panel) {
        System.out.println("Cambiando panel central a: " + panel.getClass().getSimpleName());
        panel.setSize(1000,600);
        panel.setLocation(0,0);
        
        cliente.getPanelPantalla().removeAll();
        cliente.getPanelPantalla().add(panel, BorderLayout.CENTER);
        cliente.getPanelPantalla().revalidate();
        cliente.getPanelPantalla().repaint();
    }
    
    
    public void volverLista() {
        mostrarPanelCentral(lisCliente);
    }
    
    private void inicializarEventos() {
        cliente.getjButtonNuevoCliente().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //accion boton nuevo cliente
                System.out.println("Has pulsado nuevo cliente");
                nuevoCliente = new NuevoClienteControlador(ClienteControlador.this, clienteDAO);
                mostrarPanelCentral(nuevoCliente.getVista());
                
            }
        });
        
        cliente.getjButtonModificarCliente().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //accion boton modificar cliente 
                System.out.println("Has pulsado modificar cliente");
                modificCliente = new ModificClienteJPanel();
                mostrarPanelCentral(modificCliente);
            }
        });
        
        cliente.getjButtonConsultarCliente().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //accion boton consultar cliente
                System.out.println("Has pulsado consultar cliente");
                consultaCliente = new ConsultaClienteJPanel();
                mostrarPanelCentral(consultaCliente);
                
            }
        });
        
        cliente.getjButtonBorrarCliente().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //accion boton borrar cliente
                System.out.println("Has pulsado borrar cliente");
                
            }
        });
        
        cliente.getjButtonAtras().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //accion boton atras
                System.out.println("Has pulsado atras");
                cliente.dispose();      //cerramos la ventana
                menu.setVisible(true); //traemos menu de vuelta
            }
        });
        
        cliente.getjButtonSalir().addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //accion boton salir
                System.out.println("Has pulsado Salir");
                System.exit(0);
                
            }
        });
    }
}

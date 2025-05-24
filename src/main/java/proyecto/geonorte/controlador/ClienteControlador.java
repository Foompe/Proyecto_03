/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.controlador;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import proyecto.geonorte.modelo.dao.ClienteDAO;
import proyecto.geonorte.vista.ClienteJFrame;
import proyecto.geonorte.vista.MenuJFrame;


/**
 *
 * @author FP DAM
 */
public class ClienteControlador {

    private MenuJFrame menu;
    private ClienteJFrame cliente;
    private ListaClientesControlador listaClientesControl;
    private NuevoClienteControlador nuevoCliente;
    private ModificacionClienteControlador modificCliente;
    private ConsultaClienteControlador consultaCliente;
    private ClienteDAO clienteDAO = new ClienteDAO();

    
    //contructor del controlador
    public ClienteControlador(MenuJFrame menuVista) {
        this.menu = menuVista;              //nos traemos la clase menuJFrame para al dale atras que no cree una nueva instancia
        cliente = new ClienteJFrame();      //instanciamos la interfaz  ppara este controlador
        inicializarEventos();               //inicializamos el metodo encargado de escuchar los bonones
        cliente.setVisible(true);           //ponemos el jframe creado antes en visible

        listaClientesControl = new ListaClientesControlador(clienteDAO); //instanciamos el controlador del panel que mostrara la lista de clientes
        mostrarPanelCentral(listaClientesControl.getVista());               //pasamos al metodo encarfado de mostrar en el panel central la pantalla
    }

    //metodo para mostrar pantallas en el panel central
    public void mostrarPanelCentral(JPanel panel) {
        panel.setSize(1000, 600);
        panel.setLocation(0, 0);

        cliente.getPanelPantalla().removeAll();                         //quitamos el panel anterior
        cliente.getPanelPantalla().add(panel, BorderLayout.CENTER);     //llamamos al panel que trae el metodo
        cliente.getPanelPantalla().revalidate();                        //recalcula el layout
        cliente.getPanelPantalla().repaint();                           //fuerza el repintado
    }

    //metodo encargado de habilitar o desabilitar los botones
    private void setBotonesPanelActivos (boolean activo) {
        cliente.getjButtonNuevoCliente().setEnabled(activo);
        cliente.getjButtonModificarCliente().setEnabled(activo);
        cliente.getjButtonConsultarCliente().setEnabled(activo);
        cliente.getjButtonBorrarCliente().setEnabled(activo);
    }
    
    //metodo para traer de nuevo la lista, tambien habilita los botones de nuevo
    public void volverLista() {
        listaClientesControl.cargaClientes();
        mostrarPanelCentral(listaClientesControl.getVista());
        setBotonesPanelActivos(true);
    }

    //metodo encargado de escuchar los evventos de los botones
    private void inicializarEventos() {
        cliente.getjButtonNuevoCliente().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion boton nuevo cliente
                //inanciamos un nuevo panel de nuevvo cliente
                nuevoCliente = new NuevoClienteControlador(ClienteControlador.this, clienteDAO); 
                setBotonesPanelActivos(false);                      //desactivamos los botones laterales
            }
        });

        cliente.getjButtonModificarCliente().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion boton modificar cliente 
                int indice = listaClientesControl.elementoSelecionado(); //traemos el elemento seleccionado de la lista
                
                //comprobamos que hay algun elemento seleccionado, en caso negativo saltará un opcionpanel
                if (indice >= 0) {
                    
                    //si hay un panel seleccionado instanciamos el controlador de modigicacion de clientes, y le pasamos el indice, 
                modificCliente = new ModificacionClienteControlador(indice,ClienteControlador.this, clienteDAO);
                setBotonesPanelActivos(false);                      //desabilitamos botones
                }else {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar primero un cliente");
                }
            }
        });

        cliente.getjButtonConsultarCliente().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion boton consultar cliente
                
                //mismo funcionamiento que el caso anterior
                int indice = listaClientesControl.elementoSelecionado(); //traemos el elemento seleccionado de la lista

                if (indice >= 0) {
                consultaCliente = new ConsultaClienteControlador(indice,ClienteControlador.this, clienteDAO, listaClientesControl);
                setBotonesPanelActivos(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar primero un cliente");
                }
            }
        });

        cliente.getjButtonBorrarCliente().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion boton borrar cliente
                int indice = listaClientesControl.elementoSelecionado(); //traemos el elemento seleccionado de la lista
                
                if (indice >= 0) {
                    clienteDAO.getClientes().get(indice);
                    //mostramos un mensaje de confirmacion del cliente que se quiere borrar
                    String mensaje = "Borrar cliente: \n" + 
                                     clienteDAO.getClientes().get(indice).getCod_cliente() + " - " +
                                     clienteDAO.getClientes().get(indice).getNif() + " - " +
                                     clienteDAO.getClientes().get(indice).getRazon_social();
                            
                    int opcion = JOptionPane.showConfirmDialog(
                            null,
                            mensaje,
                            "Confirmar borrado",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                        
                        //llamamos al metodo de borrado de clientes y le pasamos el objeto indicado en el indice
                        clienteDAO.borrar(clienteDAO.getClientes().get(indice));
                       
                        JOptionPane.showMessageDialog(null,"Borrado exitoso");

                        // Actualizar la lista
                        listaClientesControl.cargaClientes(); 
                    } 

                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un cliente para borrar.");
                }

            }
        });

        cliente.getjButtonAtras().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion boton atras
                System.out.println("Has pulsado atras");
                cliente.dispose();      //cerramos la vista cliente
                menu.setVisible(true); //traemos menu de vuelta
            }
        });

        cliente.getjButtonSalir().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion boton salir, cierra el programa
                System.out.println("Has pulsado Salir");
                System.exit(0);
            }
        });
    }
}

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
import proyecto.geonorte.vista.ConsultaClienteJPanel;

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

    public ClienteControlador(MenuJFrame menuVista) {
        this.menu = menuVista;
        cliente = new ClienteJFrame();
        inicializarEventos();       //inicializamos el metodo encargado de escuchar

        cliente.setVisible(true);

        listaClientesControl = new ListaClientesControlador(ClienteControlador.this, clienteDAO);
        mostrarPanelCentral(listaClientesControl.getVista());
    }

    //metodo para mostrar pantallas en el panel central
    public void mostrarPanelCentral(JPanel panel) {
        System.out.println("Cambiando panel central a: " + panel.getClass().getSimpleName());
        panel.setSize(1000, 600);
        panel.setLocation(0, 0);

        cliente.getPanelPantalla().removeAll();
        cliente.getPanelPantalla().add(panel, BorderLayout.CENTER);
        cliente.getPanelPantalla().revalidate();
        cliente.getPanelPantalla().repaint();
    }

    private void setBotonesPanelActivos (boolean activo) {
        cliente.getjButtonNuevoCliente().setEnabled(activo);
        cliente.getjButtonModificarCliente().setEnabled(activo);
        cliente.getjButtonConsultarCliente().setEnabled(activo);
        cliente.getjButtonBorrarCliente().setEnabled(activo);
    }
    
    public void volverLista() {
        mostrarPanelCentral(listaClientesControl.getVista());
        setBotonesPanelActivos(true);
    }

    private void inicializarEventos() {
        cliente.getjButtonNuevoCliente().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion boton nuevo cliente
                
                nuevoCliente = new NuevoClienteControlador(ClienteControlador.this, clienteDAO, listaClientesControl);
                mostrarPanelCentral(nuevoCliente.getVista());
                setBotonesPanelActivos(false);
            }
        });

        cliente.getjButtonModificarCliente().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion boton modificar cliente 
                int indice = listaClientesControl.elementoSelecionado(); //traemos el elemento seleccionado de la lista

                if (indice >= 0) {
                modificCliente = new ModificacionClienteControlador(indice,ClienteControlador.this, clienteDAO, listaClientesControl);
                mostrarPanelCentral(modificCliente.getVista());
                setBotonesPanelActivos(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Debes seleccionar primero un cliente");
                }
            }
        });

        cliente.getjButtonConsultarCliente().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion boton consultar cliente
                int indice = listaClientesControl.elementoSelecionado(); //traemos el elemento seleccionado de la lista

                if (indice >= 0) {
                consultaCliente = new ConsultaClienteControlador(indice,ClienteControlador.this, clienteDAO, listaClientesControl);
                mostrarPanelCentral(consultaCliente.getVista());
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
                        clienteDAO.getClientes().remove(indice);

                        JOptionPane.showMessageDialog(
                                null,
                                "Cliente borrado.",
                                "Borrado exitoso",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        // Actualizar la lista
                        listaClientesControl.cargaClientes(); // ← asumimos que tenés este método en el controlador

                    } // Si es NO, no hacés nada

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
                //accion boton salir
                System.out.println("Has pulsado Salir");
                System.exit(0);

            }
        });
    }
}

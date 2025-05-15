/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import proyecto.geonorte.modelo.VO.ClienteVO;
import proyecto.geonorte.modelo.dao.ClienteDAO;
import proyecto.geonorte.vista.NuevoClienteJPanel;

/**
 *
 * @author FP DAM
 */
public class NuevoClienteControlador {

    private NuevoClienteJPanel nuevoCliente;
    private ClienteControlador controlCliente;
    private ClienteDAO clienteDAO;
    private ListaClientesControlador listaClientes;

    public NuevoClienteControlador(ClienteControlador controlCliente, ClienteDAO clienteDAO, ListaClientesControlador listaClientes) {
        this.controlCliente = controlCliente;
        this.clienteDAO = clienteDAO;
        this.listaClientes = listaClientes;
        nuevoCliente = new NuevoClienteJPanel();
        inicializarEventos();       //inicializamos el metodo encargado de escuchar

    }

    public JPanel getVista() {
        return nuevoCliente;
    }

    private void inicializarEventos() {
        nuevoCliente.getjButtonGuardar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //acciones del boton guardar (almacenar todos los datos en el objeto)
                String pos = "0";
                
                if (nuevoCliente.getjTextField_ID().getText().isEmpty()
                        || nuevoCliente.getjTextField_nif().getText().isEmpty()
                        || nuevoCliente.getjTextField_RazonSocial().getText().isEmpty()
                        || nuevoCliente.getjTextField_Calle().getText().isEmpty()
                        || nuevoCliente.getjTextField_Numero().getText().isEmpty()
                        || nuevoCliente.getjTextField_Localidad().getText().isEmpty()
                        || nuevoCliente.getjTextField_cp().getText().isEmpty()
                        || nuevoCliente.getjTextField_telefono().getText().isEmpty()
                        || nuevoCliente.getjTextField_TipoEmpresa().getText().isEmpty() ||
                        (!nuevoCliente.getjRadioButton_EmpresaUnica().isSelected() && !nuevoCliente.getjRadioButton_GrupoEmpresas().isSelected())) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    System.out.println("Faltan campos por completar");
                    return;
                }
                
                if (nuevoCliente.getjRadioButton_EmpresaUnica().isSelected()) {
                    pos = "E";
                } else if (nuevoCliente.getjRadioButton_GrupoEmpresas().isSelected()) {
                    pos = "G";
                }
                String id = nuevoCliente.getjTextField_ID().getText();
                String nif = nuevoCliente.getjTextField_nif().getText();
                String razon_social = nuevoCliente.getjTextField_RazonSocial().getText();
                String calle = nuevoCliente.getjTextField_Calle().getText();
                int numero = Integer.parseInt(nuevoCliente.getjTextField_Numero().getText());
                String localidad = nuevoCliente.getjTextField_Localidad().getText();
                int cod_postal = Integer.parseInt(nuevoCliente.getjTextField_cp().getText());
                int telefono = Integer.parseInt(nuevoCliente.getjTextField_telefono().getText());
                String tipo_empresa = nuevoCliente.getjTextField_TipoEmpresa().getText();

                

                clienteDAO.getClientes().add(new ClienteVO(pos, id, nif, razon_social, calle, numero, localidad, cod_postal, telefono, tipo_empresa));
                
                
                listaClientes.cargaClientes();
                controlCliente.volverLista();
            }
        });

        nuevoCliente.getjButtonCancelar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion del boton cancelar
                controlCliente.volverLista(); //volvemos a poner lista de clientes en el panel
            }
        });

    }
}

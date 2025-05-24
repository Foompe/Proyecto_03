/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import proyecto.geonorte.modelo.VO.Cliente;
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

    public NuevoClienteControlador(ClienteControlador controlCliente, ClienteDAO clienteDAO) {
        this.controlCliente = controlCliente;
        this.clienteDAO = clienteDAO;
        nuevoCliente = new NuevoClienteJPanel();
        inicializarEventos();       //inicializamos el metodo encargado de escuchar
        controlCliente.mostrarPanelCentral(nuevoCliente);
    }

    private void inicializarEventos() {
        nuevoCliente.getjButtonGuardar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //acciones del boton guardar (almacenar todos los datos en el objeto)
                String pos = "0";
                //comprobamos que los todos los campos esten completos
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
                    
                    //si faltan campos por completar saltara un mensaje y no dejara continuar
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios."); 
                    return;
                }
                
                //capturamos la excepcion que manda el programa si no se ponen datos de tipo numerico
                try {
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
                
                 //instanciamos un objeto cliente con los datos obtenidos
                String cod_cliente = pos.concat(id);
                Cliente cliente = new Cliente(cod_cliente, nif, razon_social, calle, numero, localidad, cod_postal, telefono, tipo_empresa);
                //llamamos al metodo para insertar el cliente
                clienteDAO.insertar(cliente);
                
                //llamamos al metodo que nos devuelve a la lista
                controlCliente.volverLista();

                } catch (NumberFormatException z) {
                    JOptionPane.showMessageDialog(null, "En campos númericos debes introducir números");
                }
               
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

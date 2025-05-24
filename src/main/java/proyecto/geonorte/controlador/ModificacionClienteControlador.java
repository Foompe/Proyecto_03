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
import proyecto.geonorte.vista.ModificClienteJPanel;

/**
 *
 * @author FP DAM
 */
public class ModificacionClienteControlador {

    private int indice;
    private ModificClienteJPanel modificCliente;
    private ClienteControlador controlCliente;
    private ClienteDAO cliente;


    public ModificacionClienteControlador(int indice, ClienteControlador controlCliente, ClienteDAO cliente) {
        this.indice = indice;
        this.controlCliente = controlCliente;
        this.cliente = cliente;
        
        modificCliente = new ModificClienteJPanel();
        controlCliente.mostrarPanelCentral(modificCliente);
        inicializarEventos();   //inicializamos el metodo encargado de escuchar
        cargarDatosCliente();   //llamamos al metodo encargado de cargar los datos del cliente
    }

    public void cargarDatosCliente() {
        //tomamos el codigo de cliente para descomponerlo y mostroarlo
        String cod = cliente.getClientes().get(indice).getCod_cliente();
        String grupo = cod.substring(0, 1);  //tomamos la primera posicion
        String id = cod.substring(1);       //tomamos el resto de posiciones
        //una vez descompuesto el cod_cliente, lo mostramos en la tabla
        //fijamos el radiobutton
        if (grupo.equals("E")) {
            modificCliente.getjRadioButton_EmpresaUnica().setSelected(true);
        } else if (grupo.equals("G")) {
            modificCliente.getjRadioButton_GrupoEmpresas().setSelected(true);
        }

        modificCliente.getjTextField_ID().setText(id);
        modificCliente.getjTextField_nif().setText(cliente.getClientes().get(indice).getNif());
        modificCliente.getjTextField_RazonSocial().setText(cliente.getClientes().get(indice).getRazon_social());
        modificCliente.getjTextField_Calle().setText(cliente.getClientes().get(indice).getCalle());
        modificCliente.getjTextField_Numero().setText(String.valueOf(cliente.getClientes().get(indice).getNumero()));
        modificCliente.getjTextField_cp().setText(String.valueOf(cliente.getClientes().get(indice).getCod_postal()));
        modificCliente.getjTextField_Localidad().setText(cliente.getClientes().get(indice).getLocalidad());
        modificCliente.getjTextField_telefono().setText(String.valueOf(cliente.getClientes().get(indice).getTelefono()));
        modificCliente.getjTextField_TipoEmpresa().setText(cliente.getClientes().get(indice).getTipo_empresa());
    }


    private void inicializarEventos() {
        modificCliente.getjButtonGuardar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //acciones del boton guardar (almacenar todos los datos en el objeto)
                String pos = "0";

                if (modificCliente.getjTextField_ID().getText().isEmpty()
                        || modificCliente.getjTextField_nif().getText().isEmpty()
                        || modificCliente.getjTextField_RazonSocial().getText().isEmpty()
                        || modificCliente.getjTextField_Calle().getText().isEmpty()
                        || modificCliente.getjTextField_Numero().getText().isEmpty()
                        || modificCliente.getjTextField_Localidad().getText().isEmpty()
                        || modificCliente.getjTextField_cp().getText().isEmpty()
                        || modificCliente.getjTextField_telefono().getText().isEmpty()
                        || modificCliente.getjTextField_TipoEmpresa().getText().isEmpty()
                        || (!modificCliente.getjRadioButton_EmpresaUnica().isSelected() && !modificCliente.getjRadioButton_GrupoEmpresas().isSelected())) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                    System.out.println("Faltan campos por completar");
                    return;
                }
                try {
                    if (modificCliente.getjRadioButton_EmpresaUnica().isSelected()) {
                        pos = "E";
                    } else if (modificCliente.getjRadioButton_GrupoEmpresas().isSelected()) {
                        pos = "G";
                    }
                    String id = modificCliente.getjTextField_ID().getText();
                    String nif = modificCliente.getjTextField_nif().getText();
                    String razon_social = modificCliente.getjTextField_RazonSocial().getText();
                    String calle = modificCliente.getjTextField_Calle().getText();
                    int numero = Integer.parseInt(modificCliente.getjTextField_Numero().getText());
                    String localidad = modificCliente.getjTextField_Localidad().getText();
                    int cod_postal = Integer.parseInt(modificCliente.getjTextField_cp().getText());
                    int telefono = Integer.parseInt(modificCliente.getjTextField_telefono().getText());
                    String tipo_empresa = modificCliente.getjTextField_TipoEmpresa().getText();

                    //tomamos los datos de la clave primaria del objeto para poder identificarlo
                    String cod_original = cliente.getClientes().get(indice).getCod_cliente();
                    String nif_original = cliente.getClientes().get(indice).getNif();
                    //instanciamos un objeto cliente con los datos obtenidos
                    String cod_client = pos.concat(id);
                    Cliente clienteD = new Cliente(cod_client, nif, razon_social, calle, numero, localidad, cod_postal, telefono, tipo_empresa);
                    //llamamos al metodo para insertar el cliente
                    cliente.actualizar(clienteD, cod_original, nif_original);

                    controlCliente.volverLista();

                } catch (NumberFormatException z) {
                    JOptionPane.showMessageDialog(null, "En campos númericos debes introducir números");
                }
            }
        });

        modificCliente.getjButtonCancelar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion del boton cancelar
                controlCliente.volverLista(); //volvemos a poner lista de clientes en el panel
            }
        });
    }
}

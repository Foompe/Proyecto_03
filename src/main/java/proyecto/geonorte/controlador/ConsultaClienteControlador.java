/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import proyecto.geonorte.modelo.VO.ClienteVO;
import proyecto.geonorte.modelo.dao.ClienteDAO;
import proyecto.geonorte.vista.ConsultaClienteJPanel;
import proyecto.geonorte.vista.ModificClienteJPanel;

/**
 *
 * @author FP DAM
 */
public class ConsultaClienteControlador {

    private int indice;
    private ConsultaClienteJPanel consultaCliente;
    private ClienteControlador controlCliente;
    private ClienteDAO cliente;
    private ListaClientesControlador listaClientes;

    public ConsultaClienteControlador(int indice, ClienteControlador controlCliente, ClienteDAO cliente, ListaClientesControlador listaClientes) {
        this.indice = indice;
        this.controlCliente = controlCliente;
        this.cliente = cliente;
        this.listaClientes = listaClientes;
        consultaCliente = new ConsultaClienteJPanel();
        inicializarEventos();       //inicializamos el metodo encargado de escuchar
        cargarDatosCliente();
        descativarCampos();
        
    }

    public void cargarDatosCliente() {
        //tomamos el codigo de cliente para descomponerlo y mostroarlo
        String cod = cliente.getClientes().get(indice).getCod_cliente();
        String grupo = cod.substring(0,1);  //tomamos la primera posicion
        String id = cod.substring(1);       //tomamos el resto de posiciones
        //una vez descompuesto el cod_cliente, lo mostramos en la tabla
        //fijamos el radiobutton
        if (grupo.equals("E")) {
            consultaCliente.getjRadioButton_EmpresaUnica().setSelected(true);
        } else if (grupo.equals("G")) {
            consultaCliente.getjRadioButton_GrupoEmpresas().setSelected(true);
        }
        
        consultaCliente.getjTextField_ID().setText(id);
        consultaCliente.getjTextField_nif().setText(cliente.getClientes().get(indice).getNif());
        consultaCliente.getjTextField_RazonSocial().setText(cliente.getClientes().get(indice).getRazon_social());
        consultaCliente.getjTextField_Calle().setText(cliente.getClientes().get(indice).getCalle());
        consultaCliente.getjTextField_Numero().setText(String.valueOf(cliente.getClientes().get(indice).getNumero()));
        consultaCliente.getjTextField_cp().setText(String.valueOf(cliente.getClientes().get(indice).getCod_postal()));
        consultaCliente.getjTextField_Localidad().setText(cliente.getClientes().get(indice).getLocalidad());
        consultaCliente.getjTextField_telefono().setText(String.valueOf(cliente.getClientes().get(indice).getTelefono()));
        consultaCliente.getjTextField_TipoEmpresa().setText(cliente.getClientes().get(indice).getTipo_empresa());
    }

    public JPanel getVista() {
        return consultaCliente;
    }

    private void inicializarEventos() {    
        consultaCliente.getjButtonCancelar().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //accion del boton cancelar
                controlCliente.volverLista(); //volvemos a poner lista de clientes en el panel
            }
        });
    }
    
    public void descativarCampos() {
        consultaCliente.getjRadioButton_EmpresaUnica().setEnabled(false);
        consultaCliente.getjRadioButton_GrupoEmpresas().setEnabled(false);
        consultaCliente.getjTextField_ID().setEditable(false);
        consultaCliente.getjTextField_nif().setEditable(false);
        consultaCliente.getjTextField_RazonSocial().setEditable(false);
        consultaCliente.getjTextField_Calle().setEditable(false);
        consultaCliente.getjTextField_Numero().setEditable(false);
        consultaCliente.getjTextField_cp().setEditable(false);
        consultaCliente.getjTextField_Localidad().setEditable(false);
        consultaCliente.getjTextField_telefono().setEditable(false);
        consultaCliente.getjTextField_TipoEmpresa().setEditable(false);
        consultaCliente.getjButtonGuardar().setVisible(false);
    }
}

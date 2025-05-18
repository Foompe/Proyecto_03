/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.controlador;

import java.util.ArrayList;
import javax.swing.JPanel;
import proyecto.geonorte.modelo.VO.Cliente;
import proyecto.geonorte.modelo.dao.ClienteDAO;
import proyecto.geonorte.vista.ListaClientesJPanel;

/**
 *
 * @author FP DAM
 */
public class ListaClientesControlador {
    
    private ListaClientesJPanel listaCliente;
    private ClienteControlador controlCliente;
    private ClienteDAO clienteDAO;
    
    
    //constructor
    public ListaClientesControlador(ClienteControlador controlCliente, ClienteDAO clienteDAO) {
        this.controlCliente = controlCliente;
        this.clienteDAO = clienteDAO;
        listaCliente = new ListaClientesJPanel();
        cargaClientes();
    }

    
    //metodo que carga los clientes en la lista
    public void cargaClientes () {
        //tomamos el array de objetos
        ArrayList<String> datosClientes = new ArrayList<>();
        //recorremos el array de objetos hasta finalizarlo
        for(Cliente cliente : clienteDAO.getClientes()) {
        //en cada una de las vueltas pasamos el to string al array del jpanel
            datosClientes.add(cliente.toString());
        }
        listaCliente.getjList_Clientes().setListData(datosClientes.toArray(new String[0]));
    }
    
    //metodo que pasa el indice del elemento pulsado en la lista
    public int elementoSelecionado () {
        return listaCliente.getjList_Clientes().getSelectedIndex();
    }
    
    
    //metodo que devuelve la pantalla que ejecuta este controlador
    public JPanel getVista() {
        return listaCliente;
    }   
        
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.modelo.dao;

import java.util.ArrayList;
import proyecto.geonorte.modelo.VO.ClienteVO;

/**
 *
 * @author FP DAM
 */
public class ClienteDAO {

    //lista donde almacenamos todos los clientes
    private ArrayList<ClienteVO> clientes;

    //constructor
    public ClienteDAO() {
        clientes = new ArrayList<>();

        //añadimos clientes de prueba
        clientes.add(new ClienteVO("G", "23", "A55667788", "Nike", "Perico Perez", 23, "Lugo", 15009, 666999333, "S.A."));
        clientes.add(new ClienteVO("E", "345", "B99999999", "Adidas", "Fernando Perez", 2, "Orense", 23509, 698745212, "Autonomo"));
        clientes.add(new ClienteVO("G", "3", "T23423333", "Puma", "Campo Perez", 233, "La Coruña", 15109, 644444444, "S.L."));
    }

    //metood para acceder al arraylist
    public ArrayList<ClienteVO> getClientes() {
        return clientes;
    }

}

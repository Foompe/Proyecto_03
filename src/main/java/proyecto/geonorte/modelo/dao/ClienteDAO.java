/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import proyecto.geonorte.ConexionBD;
import proyecto.geonorte.modelo.VO.Cliente;

/**
 *
 * @author FP DAM
 */
public class ClienteDAO {

    //lista donde almacenamos todos los clientes
    private ArrayList<Cliente> clientes;

    //constructor
    public ClienteDAO() {
        System.out.println("se creo un cliente dao");
        clientes = new ArrayList<>();
        cargaClientesBD();
        
    }

    //metood para acceder al arraylist
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    //Metodo añadir cliente desde controlador con parametros
    //Metodo editar cliente desde controlador con parametros
    //Metodo para pasar una lista de los datos de los clientes
    //metodos con la base de datos:
    public void cargaClientesBD() {
        System.out.println("se lanzo carga de clientes");
        clientes.clear();
        String sentencia = "SELECT * FROM CLIENTE";
        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sentencia); ResultSet rs = ps.executeQuery()) {

            System.out.println("Conexion establecida: " + (con !=null));
            
            while (rs.next()) {
                System.out.println("Entra en el while rs.next");
                 Cliente cliente = new Cliente(
                        rs.getString("COD_CLIENTE"),
                        rs.getString("NIF"),
                        rs.getString("RAZON_SOCIAL"),
                        rs.getString("CALLE"),
                        rs.getInt("NUMERO"),
                        rs.getString("LOCALIDAD"),
                        rs.getInt("COD_POSTAL"),
                        rs.getInt("TLFN"),
                        rs.getString("TIPO_EMPRESA")
                );
                 System.out.println("Cliente cargado: " + cliente);
                 clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("erroe en la carga" + e.getMessage());
        }
    }

    //insercion de cliente
    public void insertar(Cliente cliente) {
        String sentencia = "INSERT INTO cliente (cod_cliente, nif, razon_social, calle, numero, localidad, cod_postal, tlfn, tipo_empresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sentencia)) {

            ps.setString(1, cliente.getCod_cliente());
            ps.setString(2, cliente.getNif());
            ps.setString(3, cliente.getRazon_social());
            ps.setString(4, cliente.getCalle());
            ps.setInt(5, cliente.getNumero());
            ps.setString(6, cliente.getLocalidad());
            ps.setInt(7, cliente.getCod_postal());
            ps.setInt(8, cliente.getTelefono());
            ps.setString(9, cliente.getTipo_empresa());

            int cantidadFilas = ps.executeUpdate();
            System.out.println(cantidadFilas + " fila/s insertadas");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //actualizacion de cliente
    public void actualizar(Cliente cliente) {
        String sentencia = "UPDATE cliente SET cod_cliente = ?, nif = ?, razon_social = ?, calle = ?, numero = ?, localidad = ?, cod_postal = ?, tlfn = ?, tipo_empresa = ?";
        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sentencia)) {

            ps.setString(1, cliente.getCod_cliente());
            ps.setString(2, cliente.getNif());
            ps.setString(3, cliente.getRazon_social());
            ps.setString(4, cliente.getCalle());
            ps.setInt(5, cliente.getNumero());
            ps.setString(6, cliente.getLocalidad());
            ps.setInt(7, cliente.getCod_postal());
            ps.setInt(8, cliente.getTelefono());
            ps.setString(9, cliente.getTipo_empresa());

            int cantidadFilas = ps.executeUpdate();
            System.out.println(cantidadFilas + " fila/s modificadas");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //borrado cliente
    public void borrar(Cliente cliente) {
        String sentencia = "DELETE FROM cliente WHERE cod_cliente = ? AND nif = ?";
        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sentencia)) {

            ps.setString(1, cliente.getCod_cliente());
            ps.setString(2, cliente.getNif());

            int cantidadFilas = ps.executeUpdate();
            System.out.println(cantidadFilas + " fila/s borradas");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

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
        clientes = new ArrayList<>(); //inicializa el arraylist donde almacenaremos los datos de los clientes de la base de datos
        cargaClientesBD();            //llamamos al metodo encargado de cargar los clientes de la base al array
    }

    //metood para acceder al arraylist
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    //metodos con la base de datos:
    public void cargaClientesBD() {
        clientes.clear();   //limpiamos la lista
        String sentencia = "SELECT * FROM CLIENTE";   //definimos la sentencia a ejecurar
        try (Connection con = ConexionBD.obtenerConexion();  //lanzamos la conexion
                PreparedStatement ps = con.prepareStatement(sentencia); 
                ResultSet rs = ps.executeQuery()) {;    //tomamos los resultados 
                   
                //array que recorre los resultados hasta que termine
            while (rs.next()) {
                //almacenamos los resultados en un objeto cliente
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
                 clientes.add(cliente); //añadimos el objeto creado al arraylist
            }
            
        } catch (SQLException e) {
            System.out.println("erroe en la carga" + e.getMessage());
        }
    }

    /* 
    Metodo para la insercion de clientes nuevos.
    -> Definimos la sentencia a ejecutar y completamos cada uno de los campos
        con su correspondiente de la clase
    */
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
            System.out.println("Error al insertar" + e.getMessage());
        }
    }

    /*
    -->MEtodo de actualizacion.
        Este metodo y el siguiente, a parte del objeto toman dos datos a mayores,
        si editamos la clave primaria del objeto no tenemos manera de cambiarla
        por lo tanto tomamos los parametros de este antes de la edicion para poder
        buscarlo y posteriormente editarlo.
    */
    public void actualizar(Cliente cliente, String cod_original, String nif_original) {
        String sentencia = "UPDATE cliente SET cod_cliente = ?, nif = ?, razon_social = ?, calle = ?, numero = ?, localidad = ?, cod_postal = ?, tlfn = ?, tipo_empresa = ? WHERE cod_cliente = ? AND nif = ?";
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
            
            //usamos los valores originales del dato,por si se quiere actualizar
            ps.setString(10,cod_original);
            ps.setString(11,nif_original);
            
            int cantidadFilas = ps.executeUpdate();
            System.out.println(cantidadFilas + " fila/s modificadas");

        } catch (SQLException e) {
            System.out.println("Error al actualizar" + e.getMessage());
        }
    }

    //Metodo de borrado de cliente
    
    public void borrar(Cliente cliente) {
        String sentencia = "DELETE FROM cliente WHERE cod_cliente = ? AND nif = ?";
        try (Connection con = ConexionBD.obtenerConexion(); PreparedStatement ps = con.prepareStatement(sentencia)) {
            
            //tomamos la clave primaria del dato a borrar en nuestro caso son dos atributos 
            ps.setString(1, cliente.getCod_cliente());
            ps.setString(2, cliente.getNif());

            int cantidadFilas = ps.executeUpdate();
            System.out.println(cantidadFilas + " fila/s borradas");

        } catch (SQLException e) {
            System.out.println("Error al borrar " + e.getMessage());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.geonorte;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author fompe
 */
public class ConexionBD {

    //datos que usaremos para conectar a la base de datos
    private static final Connection conexion = null;
    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String usuario = "netbeansBD";
    private static final String contrasena = "netbeansBD";

    //metodo encargado de abrir la conexion con la base de datos 
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(url, usuario, contrasena);
    }
}

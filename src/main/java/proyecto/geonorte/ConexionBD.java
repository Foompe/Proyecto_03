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

    private static final Connection conexion = null;
    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String usuario = "netbeansBD";
    private static final String contrasena = "netbeansBD";

    
    /*
    public ConexionBD() {
        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e) {
            System.out.println("Codigo error: " + e.getMessage());
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                    System.out.println("Conexión cerrada");
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar: " + e.getMessage());
            }

        }

    }
*/
    
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(url, usuario, contrasena);
    }
}

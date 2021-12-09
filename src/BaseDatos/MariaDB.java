/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

/**
 *
 * @author usuario
 */
public class MariaDB extends BaseDatos{
    
    public MariaDB(String servidor, String base, String usuario, String contraseña) {
        super("com.mysql.cj.jdbc.Driver", "jdbc", "mysql", servidor, base, usuario, contraseña);
    }
    
}

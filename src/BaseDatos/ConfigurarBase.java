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
public class ConfigurarBase {

    private String IP;
    private String nombreBase;
    private String usuario;
    private String contrasena;
    private MariaDB db;

    public ConfigurarBase(String IP, String nombreBase, String usuario, String contrasena) {
        this.IP = IP;
        this.nombreBase = nombreBase;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public void cargarConexion() {
        db = new MariaDB(this.IP, this.contrasena, this.usuario, this.contrasena);

    }
}

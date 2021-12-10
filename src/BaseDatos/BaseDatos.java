/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class BaseDatos {

    private String driver; //"com.mysql.cj.jdbc.Driver"
    private String api;// jdbc
    private String motor;//mysql
    private String servidor;// 127.0.0.1
    private String base;// prueba
    private String usuario; //root
    private String contraseña;// ""
    private String error;

    public String getError() {
        return error;
    }

    private Connection conexion;
    private PreparedStatement setencia;
    private ResultSet datos;

    public BaseDatos(String driver, String api, String motor, String servidor, String base, String usuario, String contraseña) {
        this.driver = driver;
        this.api = api;
        this.motor = motor;
        this.servidor = servidor;
        this.base = base;
        this.usuario = usuario;
        this.contraseña = contraseña;
        if (!this.conectar()) {
            throw new NullPointerException();
        }
    }

    private boolean conectar() {
        this.error = null;
        try {
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(this.api + ":" + this.motor + "://" + this.servidor + "/"
                    + this.base, this.usuario, this.contraseña);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            this.error = ex.toString();
            return false;
        }

    }

    public Connection getConexion() {
        return conexion;
    }

    //sql para mandar que hacer , actualizar , eliminar , consultar
    public boolean prepararSetencia(String sql) {
        this.error = null;
        try {
            this.setencia = this.conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return true;
        } catch (SQLException ex) {
            this.error = ex.toString();
            return false;
        }

    }

    public Object[][] seleccionar(Object[] parametros) {
        this.error = null;
        try {

            this.cargar(parametros);
            this.datos = this.setencia.executeQuery();
            return this.toArray(this.datos);

        } catch (SQLException ex) {
            this.error = ex.toString();
            return null;
        }

    }

    private void cargar(Object[] parametros) throws SQLException {
        int i = 1;
        for (Object parametro : parametros) {
            if (parametro instanceof Integer) {
                this.setencia.setInt(i, (int) parametro);
            }
            if (parametro instanceof Double) {
                this.setencia.setDouble(i, (double) parametro);
            }
            if (parametro instanceof String) {
                this.setencia.setString(i, (String) parametro);
            }
            if (parametro instanceof LocalDate) {
                this.setencia.setDate(i,Date.valueOf(parametro.toString()) );
            }if (parametro instanceof LocalTime) {
                this.setencia.setTime(i,Time.valueOf(parametro.toString()+":00"));
            }
            i++;
        }
    }

    private Object[][] toArray(ResultSet rs) throws SQLException {
        rs.last();// posicionar en el ultimo de los registros 

        Object[][] datos = new Object[rs.getRow()][rs.getMetaData().getColumnCount()];
        rs.beforeFirst();// se posiciona antes del primer registro para luegocuando se le da next se posicione en el primer registro
        int f = 0;
        while (rs.next()) {
            for (int c = 0; c < rs.getMetaData().getColumnCount(); c++) {
                datos[f][c] = rs.getObject(c + 1);
                
            }
            f++;
        }
        return datos;// retorna la matriz de objeto
    }
    // indica si hubieron modificaciones en la base 

    public boolean ejecutar(Object[] parametros) {
        this.error = null;
        try {

            this.cargar(parametros);
            return this.setencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            this.error = ex.toString();
            return false;
        }

    }
    
    
}

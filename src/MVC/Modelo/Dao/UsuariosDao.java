/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo.Dao;

import BaseDatos.BaseDatos;
import MVC.Modelo.Usuarios;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class UsuariosDao implements Dao<Usuarios> {

    private BaseDatos db;

    public UsuariosDao(BaseDatos db) {
        this.db = db;
    }

    @Override
    public boolean insertar(Usuarios ob) {
//        
        if (ob.requeridos() && this.validarPK(ob) && this.validarUnicos(ob)) {
            this.db.prepararSetencia("Insert into usuarios values(?,?,?,?,?,?,?,?)");
            Object[] param = {ob.getCedula(), ob.getNombre(), ob.getFechaNacimiento(), ob.getTelefono(), ob.getCorreo(), ob.getNombreUsuario(),
                ob.getContrasena(), ob.getTipoUsuario()};
            return this.db.ejecutar(param);
        }
        return false;
    }
// hacer un metodo aparte solo para cambiar la contrasena 

    @Override
    public boolean modificar(Usuarios ob) {
        if (ob.requeridos() && this.validarUnicos(ob)) {
            this.db.prepararSetencia("Update into usuarios set NombreCompleto=?,FechaNacimiento=?,Telefono=?,CorreoElectronico=?,NombreUsuario=?,TipoUsuario=? where Cedula=?");

            Object[] param = {ob.getNombre(), ob.getFechaNacimiento(), ob.getTelefono(), ob.getCorreo(), ob.getNombreUsuario(),
                ob.getTipoUsuario(), ob.getCedula()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean eliminar(Usuarios ob) {
        if (ob.requeridos()) {
            this.db.prepararSetencia("Delete usuarios where Cedula =?");

            Object[] param = {ob.getCedula()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public Usuarios buscar(Usuarios ob) {
        this.db.prepararSetencia("select * from usuarios where Cedula=?");
        Object[] param = {ob.getCedula()};
        Object[][] valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            return new Usuarios((int) valores[0][0], String.valueOf(valores[0][1]), (Date) valores[0][2], (int) valores[0][3], String.valueOf(valores[0][4]),
                    String.valueOf(valores[0][5]), String.valueOf(valores[0][6]), String.valueOf(valores[0][7]));
        }
        return null;

    }

    @Override
    public Usuarios[] listar() {
        this.db.prepararSetencia("select * from usuarios order by  NombreCompleto");

        Object[] param = {};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            Usuarios[] usua = new Usuarios[valores.length];
            for (int f = 0; f <= valores.length - 1; f++) {
                usua[f] = new Usuarios((int) valores[0][0], String.valueOf(valores[0][1]), (Date) valores[0][2], (int) valores[0][3], String.valueOf(valores[0][4]),
                        String.valueOf(valores[0][5]), String.valueOf(valores[0][6]), String.valueOf(valores[0][7]));
            }
            return usua;
        }
        return null;
    }

    @Override
    public Usuarios[] filtrar(String fil) {
        this.db.prepararSetencia("select * from usuarios where NombreCompleto like (?)order by  Cedula");

        Object[] param = {fil};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            Usuarios[] usua = new Usuarios[valores.length];
            for (int f = 0; f <= valores.length - 1; f++) {
                usua[f] = new Usuarios((int) valores[0][0], String.valueOf(valores[0][1]), (Date) valores[0][2], (int) valores[0][3], String.valueOf(valores[0][4]),
                        String.valueOf(valores[0][5]), String.valueOf(valores[0][6]), String.valueOf(valores[0][7]));
            }
            return usua;
        }
        return null;
    }

    @Override
    public boolean validarPK(Usuarios ob) {
        ob = this.buscar(ob);
        return ob == null;
    }

    private Usuarios buscarNombre(Usuarios ob) {

        this.db.prepararSetencia("select * from usuarios where NombreCompleto=?");
        Object[] param = {ob.getNombre()};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            return new Usuarios((int) valores[0][0], String.valueOf(valores[0][1]), (Date) valores[0][2], (int) valores[0][3], String.valueOf(valores[0][4]),
                    String.valueOf(valores[0][5]), String.valueOf(valores[0][6]), String.valueOf(valores[0][7]));
        }
        return null;

    }

    private Usuarios buscarCorreo(Usuarios ob) {

        this.db.prepararSetencia("select * from usuarios where CorreoElectronico=?");
        Object[] param = {ob.getCorreo()};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            return new Usuarios((int) valores[0][0], String.valueOf(valores[0][1]), (Date) valores[0][2], (int) valores[0][3], String.valueOf(valores[0][4]),
                    String.valueOf(valores[0][5]), String.valueOf(valores[0][6]), String.valueOf(valores[0][7]));
        }
        return null;

    }

    @Override
    public boolean validarUnicos(Usuarios ob) {
        return buscarNombre(ob) == null && buscarCorreo(ob) == null;

    }

    @Override
    public boolean validarFK(Usuarios ob) {
        return true;
    }

    public boolean cambiarContrasena(String pass, Usuarios usua) {
        if (pass != null) {
            this.db.prepararSetencia("Update into usuarios set Contraseña=? where Cedula=?");

            Object[] param = {pass, usua.getCedula()};
            return this.db.ejecutar(param);
        }
        return false;
    }
}

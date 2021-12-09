/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo.Dao;

import BaseDatos.BaseDatos;
import MVC.Modelo.Tecnicos;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class TecnicosDao implements Dao<Tecnicos> {
private BaseDatos db;

    public TecnicosDao(BaseDatos db) {
        this.db = db;
    }

    @Override
    public boolean insertar(Tecnicos ob) {
          if (ob.requeridos() && this.validarPK(ob) && this.validarUnicos(ob)) {
            this.db.prepararSetencia("Insert into tecnicos values(?,?,?,?,?,?)");
            Object[] param = {ob.getCedula(),ob.getNombre(),ob.getFechaNacimiento(),ob.getTelefono(),ob.getCorreo(),ob.getSalario()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean modificar(Tecnicos ob) {
        if (ob.requeridos() && this.validarUnicos(ob)) {
            this.db.prepararSetencia("Update into tecnicos set NombreCompleto=?,FechaNacimiento=?,Telefono=?,CorreoElectronico=?,Salario=? where Cedula=?");

            Object[] param = {ob.getNombre(), ob.getFechaNacimiento(), ob.getTelefono(), ob.getCorreo(), ob.getSalario(),
                 ob.getCedula()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean eliminar(Tecnicos ob) {
         if (ob.requeridos()) {
            this.db.prepararSetencia("Delete tecnicos where Cedula =?");

            Object[] param = {ob.getCedula()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public Tecnicos buscar(Tecnicos ob) {
        //        private int cedula;
//    private String nombre;
//    private Date FechaNacimiento;
//    private int telefono;
//    private String correo;
//    private int salario;
this.db.prepararSetencia("select * from tecnicos where Cedula=?");
        Object[] param = {ob.getCedula()};
        Object[][] valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            return new Tecnicos((int) valores[0][0], String.valueOf(valores[0][1]), (Date) valores[0][2], (int) valores[0][3], String.valueOf(valores[0][4]),
                   (int)valores[0][5]);
        }
        return null;        
    }

    @Override
    public Tecnicos[] listar() {
        this.db.prepararSetencia("select * from tecnicos order by  NombreCompleto");

        Object[] param = {};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            Tecnicos[] tec = new Tecnicos[valores.length];
            for (int f = 0; f <= valores.length - 1; f++) {
                tec[f] = new Tecnicos((int) valores[0][0], String.valueOf(valores[0][1]), (Date) valores[0][2], (int) valores[0][3], String.valueOf(valores[0][4]),
                   (int)valores[0][5]);
            }
            return tec;
        }
        return null;
    }

    @Override
    public Tecnicos[] filtrar(String fil) {
       this.db.prepararSetencia("select * from usuarios where NombreCompleto like (?)order by  NombreCompleto");

        Object[] param = {fil};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            Tecnicos[] tec = new Tecnicos[valores.length];
            for (int f = 0; f <= valores.length - 1; f++) {
                tec[f] = new Tecnicos((int) valores[0][0], String.valueOf(valores[0][1]), (Date) valores[0][2], (int) valores[0][3], String.valueOf(valores[0][4]),
                   (int)valores[0][5]);
            }
            return tec;
        }
        return null;
    }

    @Override
    public boolean validarPK(Tecnicos ob) {
        ob = this.buscar(ob);
        return ob == null;
    }

    @Override
    public boolean validarUnicos(Tecnicos ob) {
        return buscarCorreo(ob)==null;
    }
private Tecnicos buscarCorreo(Tecnicos ob) {

        this.db.prepararSetencia("select * from usuarios where CorreoElectronico=?");
        Object[] param = {ob.getCorreo()};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            return new Tecnicos((int) valores[0][0], String.valueOf(valores[0][1]), (Date) valores[0][2], (int) valores[0][3], String.valueOf(valores[0][4]),
                   (int)valores[0][5]);
        }
        return null;

    }
    @Override
    public boolean validarFK(Tecnicos ob) {
        return true;
    }
    
}

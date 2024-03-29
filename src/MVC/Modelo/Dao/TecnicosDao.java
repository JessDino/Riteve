/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo.Dao;

import BaseDatos.BaseDatos;
import MVC.Modelo.Tecnicos;
import java.time.LocalDate;
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
            this.db.prepararSetencia("Update tecnicos set NombreCompleto=?,FechaNacimiento=?,Telefono=?,CorreoElectronico=?,Salario=? where Cedula=?");

            Object[] param = {ob.getNombre(), ob.getFechaNacimiento(), ob.getTelefono(), ob.getCorreo(), ob.getSalario(),
                 ob.getCedula()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean eliminar(Tecnicos ob) {
         if (ob.requeridos()) {
            this.db.prepararSetencia("Delete from tecnicos where Cedula =?");

            Object[] param = {ob.getCedula()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public Tecnicos buscar(Tecnicos ob) {
   
this.db.prepararSetencia("SELECT * FROM tecnicos WHERE Cedula=?");
        System.out.println(ob.getCedula());
        Object[] param = {ob.getCedula()};
        Object[][] valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
             String fe[] = valores[0][2].toString().split("-");
                int y = Integer.parseInt(fe[0]);
                int m = Integer.parseInt(fe[1]);
                int d = Integer.parseInt(fe[2]);
                
            return new Tecnicos((int) valores[0][0], String.valueOf(valores[0][1]),LocalDate.of(y, m, d), (int) valores[0][3], String.valueOf(valores[0][4]),
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
               String fe[] = valores[f][2].toString().split("-");
                int y = Integer.parseInt(fe[0]);
                int m = Integer.parseInt(fe[1]);
                int d = Integer.parseInt(fe[2]);
                tec[f] = new Tecnicos((int) valores[f][0], String.valueOf(valores[f][1]),LocalDate.of(y, m, d), (int) valores[f][3], String.valueOf(valores[f][4]),
                   (int)valores[f][5]);
            }
            return tec;
        }
        return null;
    }

    @Override
    public Tecnicos[] filtrar(String fil) {
       this.db.prepararSetencia("select * from tecnicos where Cedula like (?)order by  NombreCompleto");

        Object[] param = {fil};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            Tecnicos[] tec = new Tecnicos[valores.length];
            for (int f = 0; f <= valores.length - 1; f++) {
               String fe[] = valores[f][2].toString().split("-");
                int y = Integer.parseInt(fe[0]);
                int m = Integer.parseInt(fe[1]);
                int d = Integer.parseInt(fe[2]);
                tec[f] = new Tecnicos((int) valores[f][0], String.valueOf(valores[f][1]),LocalDate.of(y, m, d), (int) valores[f][3], String.valueOf(valores[f][4]),
                   (int)valores[f][5]);
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
            String fe[] = valores[0][2].toString().split("-");
                int y = Integer.parseInt(fe[0]);
                int m = Integer.parseInt(fe[1]);
                int d = Integer.parseInt(fe[2]);
               return new Tecnicos((int) valores[0][0], String.valueOf(valores[0][1]),LocalDate.of(y, m, d), (int) valores[0][3], String.valueOf(valores[0][4]),
                   (int)valores[0][5]);
        }
        return null;

    }
    @Override
    public boolean validarFK(Tecnicos ob) {
        return true;
    }
    
}

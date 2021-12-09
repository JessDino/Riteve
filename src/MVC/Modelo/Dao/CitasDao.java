/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo.Dao;

import BaseDatos.BaseDatos;
import MVC.Modelo.Citas;
import MVC.Modelo.Vehiculos;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class CitasDao implements Dao<Citas>{
   private BaseDatos db;  

    public CitasDao(BaseDatos db) {
        this.db = db;
    }

//     private int idCita;
//    private Date fecha;
//    private String hora;
//    private Vehiculos vehiculo;
    @Override
    public boolean insertar(Citas ob) {
        if (ob.requeridos() && this.validarPK(ob) && this.validarFK(ob)) {
            this.db.prepararSetencia("Insert into citas values(?,?,?)");

            Object[] param = {ob.getFecha(), ob.getHora(),ob.getVehiculo().getNumeroDePlaca()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean modificar(Citas ob) {
         if (ob.requeridos() && this.validarUnicos(ob)&& this.validarFK(ob)) {
            this.db.prepararSetencia("Update into citas set Fecha=?,Hora=? where IdCita=?");

            Object[] param = {ob.getFecha(), ob.getHora(),ob.getIdCita()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean eliminar(Citas ob) {
        if (ob.requeridos()) {
            
            this.db.prepararSetencia("Delete citas where IdCita =?");

            Object[] param = {ob.getIdCita()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public Citas buscar(Citas ob) {
        
        this.db.prepararSetencia("select * from citas where IdCita=?");
        Object[] param = {ob.getIdCita()};
        Object[][] valores = this.db.seleccionar(param);

        if (valores.length > 0 && valores != null) {
            VehiculosDao vehi=new VehiculosDao(this.db);
            return new Citas((int) valores[0][0], (Date)valores[0][1],String.valueOf(valores[0][2]),vehi.buscar(new Vehiculos( (int)valores[0][0])));
        }
        return null;
    }

    @Override
    public Citas[] listar() {
          this.db.prepararSetencia("select * from citas order by IdCita");

        Object[] param = {};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            VehiculosDao vehi=new VehiculosDao(this.db);
            Citas[] ci = new Citas[valores.length];
            for (int f = 0; f <= valores.length - 1; f++) {
                ci[f] = new  Citas((int) valores[0][0], (Date)valores[0][1],String.valueOf(valores[0][2]),vehi.buscar(new Vehiculos( (int)valores[0][0])));
            }
            return ci;
        }
        return null;
    }

    @Override
    public Citas[] filtrar(String fil) {
        this.db.prepararSetencia("select * from Citas where NúmeroDePlaca like (?)order by  NumeroDePlaca");
       
        Object[] param = {fil};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0&& valores!=null) {
             VehiculosDao vehi=new VehiculosDao(this.db);
            Citas []ci=new Citas [valores.length];
            for (int f = 0; f <= valores.length-1; f++) {
                ci [f]=  new  Citas((int) valores[0][0], (Date)valores[0][1],String.valueOf(valores[0][2]),vehi.buscar(new Vehiculos( (int)valores[0][0])));
            }
            return ci;
        }
        return null;
    }

    @Override
    public boolean validarPK(Citas ob) {
       
       ob=this.buscar(ob);
      return ob==null;
    }

    @Override
    public boolean validarUnicos(Citas ob) {
        return true;
    }

    @Override
    public boolean validarFK(Citas ob) {
       VehiculosDao vehi=new  VehiculosDao(this.db);
        return vehi.buscar(ob.getVehiculo())!=null;
    }
    
}

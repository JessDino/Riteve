/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo.Dao;

import BaseDatos.BaseDatos;
import MVC.Modelo.Vehiculos;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class VehiculosDao implements Dao<Vehiculos>{
  private BaseDatos db;

    public VehiculosDao(BaseDatos db) {
        this.db = db;
    }

    @Override
    public boolean insertar(Vehiculos vehiculo) {
        
         if (vehiculo.requeridos() && this.validarPK(vehiculo) && this.validarUnicos(vehiculo)) {
            this.db.prepararSetencia("Insert into vehiculos values(?,?,?,?,?,?,?)");
            Object[] param = {vehiculo.getNumeroDePlaca(), vehiculo.getMarca(),vehiculo.getModelo(),vehiculo.getAnio(),
            vehiculo.getFechaInscripcion(),vehiculo.getCedulaPropietario(),vehiculo.getNombrePropietario()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean modificar(Vehiculos vehiculo) {
         if (vehiculo.requeridos() && this.validarUnicos(vehiculo)) {
            this.db.prepararSetencia("Update into vehiculos set Marca=?,Modelo=?,Año=?,FechaDeInscripción=?,CedulaPropietario=?,NombrePropietario=? where NumeroDePlaca =?");

            Object[] param = {vehiculo.getMarca(),vehiculo.getModelo(),vehiculo.getAnio(),vehiculo.getFechaInscripcion(),
                vehiculo.getCedulaPropietario(),vehiculo.getNombrePropietario(),vehiculo.getNumeroDePlaca()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean eliminar(Vehiculos vehiculo) {
       if (vehiculo.requeridos()) {
            this.db.prepararSetencia("Delete vehiculos where NumeroDePlaca =?");

            Object[] param = {vehiculo.getNumeroDePlaca()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public Vehiculos buscar(Vehiculos vehiculo) {
          this.db.prepararSetencia("select * from vehiculos where NumeroDePlaca=?");
        Object[] param = {vehiculo.getNumeroDePlaca()};
        Object[][] valores= this.db.seleccionar(param);
        if (valores.length > 0&& valores!=null) {
            return new Vehiculos((int) valores[0][0], String.valueOf(valores[0][1]),String.valueOf(valores[0][2]),(int)valores[0][3],(Date)valores[0][4]
            ,(int)valores[0][5],String.valueOf(valores[0][6]));
        }
        return null;

    }

    @Override
    public Vehiculos[] listar() {
         this.db.prepararSetencia("select * from vehiculos order by  NumeroDePlaca");
       
        Object[] param = {};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0&& valores!=null) {
            Vehiculos [] vehi=new Vehiculos [valores.length];
            for (int f = 0; f <= valores.length-1; f++) {
                vehi [f]=new Vehiculos((int) valores[0][0], String.valueOf(valores[0][1]),String.valueOf(valores[0][2]),(int)valores[0][3],(Date)valores[0][4]
            ,(int)valores[0][5],String.valueOf(valores[0][6]));
            }
            return  vehi;
        }
        return null;
    }

    @Override
    public boolean validarPK(Vehiculos vehiculo) {
        vehiculo=this.buscar(vehiculo);
      return vehiculo==null;
    }

    @Override
    public boolean validarUnicos(Vehiculos vehiculo) {
      return true;
    }

    
    
    @Override
    public boolean validarFK(Vehiculos vehiculo) {
        return true;
    }

    @Override
    public Vehiculos[] filtrar(String fil) {
        this.db.prepararSetencia("select * from vehiculos where Marca like (?)order by  NumeroDePlaca");
       
        Object[] param = {fil};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0&& valores!=null) {
            Vehiculos []vehi=new Vehiculos [valores.length];
            for (int f = 0; f <= valores.length-1; f++) {
                vehi [f]= new Vehiculos((int) valores[0][0], String.valueOf(valores[0][1]),String.valueOf(valores[0][2]),(int)valores[0][3],(Date)valores[0][4]
            ,(int)valores[0][5],String.valueOf(valores[0][6]));
            }
            return vehi;
        }
        return null;
    }
    
}
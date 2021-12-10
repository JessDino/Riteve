/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo.Dao;

import BaseDatos.BaseDatos;
import MVC.Modelo.Citas;
import MVC.Modelo.Revisiones;
import MVC.Modelo.Tecnicos;
import MVC.Modelo.Vehiculos;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author usuario
 */
public class RevisionesDao implements Dao<Revisiones>{
private BaseDatos db;

    public RevisionesDao(BaseDatos db) {
        this.db = db;
    }

    @Override
    public boolean insertar(Revisiones ob) {
        if (ob.requeridos() && this.validarPK(ob) && this.validarFK(ob)) {
            this.db.prepararSetencia("Insert into revisiones(Fecha,Hora,IdTecnico,TipoRevision,Observación,Estado,Vehiculo) values(?,?,?,?,?,?,?)");

            Object[] param = {ob.getFecha(), ob.getHora(),ob.getTecnico().getCedula(),ob.getTipoRevision()
            ,ob.getObservacion(),ob.getEstado(),ob.getVehiculo().getNumeroDePlaca()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean modificar(Revisiones ob) {
          if (ob.requeridos() && this.validarUnicos(ob)&& this.validarFK(ob)) {
            this.db.prepararSetencia("Update revisiones set Fecha=?,Hora=?,IdTecnico=?,TipoRevision=?,Observación=?,Estado=? where IdRevision=?");

            Object[] param = {ob.getFecha(), ob.getHora(),ob.getTecnico().getCedula(),ob.getTipoRevision() ,ob.getObservacion(),ob.getEstado(),ob.getIdRevision()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public boolean eliminar(Revisiones ob) {
        if (ob.requeridos()) {
            
            this.db.prepararSetencia("Delete from revisiones where IdRevision=?");

            Object[] param = {ob.getIdRevision()};
            return this.db.ejecutar(param);
        }
        return false;
    }

    @Override
    public Revisiones buscar(Revisiones ob) {
        
        this.db.prepararSetencia("select * from revisiones where IdRevision=?");
        Object[] param = {ob.getIdRevision()};
        Object[][] valores = this.db.seleccionar(param);

        if (valores.length > 0 && valores != null) {
            VehiculosDao vehi=new VehiculosDao(this.db);
            TecnicosDao tec=new TecnicosDao(this.db);
            
            return new Revisiones((LocalDate)valores[0][0], (LocalTime) valores[0][1],tec.buscar(new Tecnicos((int)valores[0][2])),String.valueOf(valores[0][3]),String .valueOf(valores[0][4]),String .valueOf(valores[0][5]),vehi.buscar(new Vehiculos( (int)valores[0][6])),(int)valores[0][7]);
        }
        return null;
    }

    @Override
    public Revisiones[] listar() {
        this.db.prepararSetencia("select * from revisiones order by Fecha");

        Object[] param = {};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            VehiculosDao vehi=new VehiculosDao(this.db);
            TecnicosDao tec=new TecnicosDao(this.db);
            Revisiones[] revi = new Revisiones[valores.length];
            for (int f = 0; f <= valores.length - 1; f++) {
                String fe[] = valores[f][0].toString().split("-");
                int y = Integer.parseInt(fe[0]);
                int m = Integer.parseInt(fe[1]);
                int d = Integer.parseInt(fe[2]);
                
                String time[]=valores[f][1].toString().split(":");
                int h =Integer.parseInt(time[0]);
                int mi =Integer.parseInt(time[1]);
                int s =Integer.parseInt(time[2]);
                revi[f] = new  Revisiones(LocalDate.of(y, m, d), LocalTime.of(h, mi, s),
                        tec.buscar(new Tecnicos((int)valores[f][2])),String.valueOf(valores[f][3]),String .valueOf(valores[f][4]),String .valueOf(valores[f][5]),vehi.buscar(new Vehiculos( (int)valores[f][6])),(int)valores[f][7]);
            }
            return revi;
        }
        return null;
    }

    @Override
    public Revisiones[] filtrar(String fil) {
         this.db.prepararSetencia("select * from revisiones where IdRevision=? order by  Fecha");

        Object[] param = {fil};
        Object[][] valores;
        valores = this.db.seleccionar(param);
        if (valores.length > 0 && valores != null) {
            VehiculosDao vehi=new VehiculosDao(this.db);
            TecnicosDao tec=new TecnicosDao(this.db);
            Revisiones[] revi = new Revisiones[valores.length];
            for (int f = 0; f <= valores.length - 1; f++) {
                String fe[] = valores[f][0].toString().split("-");
                int y = Integer.parseInt(fe[0]);
                int m = Integer.parseInt(fe[1]);
                int d = Integer.parseInt(fe[2]);
                String time[]=valores[f][1].toString().split(":");
                int h =Integer.parseInt(time[0]);
                int mi =Integer.parseInt(time[1]);
                int s =Integer.parseInt(time[2]);
                revi[f] = new  Revisiones(LocalDate.of(y, m, d),LocalTime.of(h, mi, s),tec.buscar(new Tecnicos((int)valores[f][2])),String.valueOf(valores[f][3]),
                        String .valueOf(valores[f][4]),String .valueOf(valores[f][5]),vehi.buscar(new Vehiculos( (int)valores[f][6])),(int)valores[f][7]);
            }
            return revi;
        }
        return null;
    }

    @Override
    public boolean validarPK(Revisiones ob) {
    return true;   
    }

    @Override
    public boolean validarUnicos(Revisiones ob) {
        return true;
    }

    @Override
    public boolean validarFK(Revisiones ob) {
        VehiculosDao vehi=new  VehiculosDao(this.db);
        TecnicosDao tec=new TecnicosDao(this.db);
        return vehi.buscar(ob.getVehiculo())!=null && tec.buscar(ob.getTecnico())!=null;
    }

     public Revisiones[] exportar(Revisiones ob) {
        
        this.db.prepararSetencia("select * from revisiones where Vehiculo=?");
        Object[] param = {ob.getVehiculo().getNumeroDePlaca()};
        Object[][] valores = this.db.seleccionar(param);

       if (valores.length > 0 && valores != null) {
            VehiculosDao vehi=new VehiculosDao(this.db);
            TecnicosDao tec=new TecnicosDao(this.db);
            Revisiones[] revi = new Revisiones[valores.length];
            for (int f = 0; f <= valores.length - 1; f++) {
                 String fe[] = valores[f][0].toString().split("-");
                int y = Integer.parseInt(fe[0]);
                int m = Integer.parseInt(fe[1]);
                int d = Integer.parseInt(fe[2]);
                String time[]=valores[f][1].toString().split(":");
                int h =Integer.parseInt(time[0]);
                int mi =Integer.parseInt(time[1]);
                int s =Integer.parseInt(time[2]);
                revi[f] = new  Revisiones(LocalDate.of(y, m, d) ,LocalTime.of(h, mi, s),tec.buscar(new Tecnicos((int)valores[f][2])),String.valueOf(valores[f][3]),
                        String .valueOf(valores[f][4]),String .valueOf(valores[f][5]),vehi.buscar(new Vehiculos( (int)valores[f][6])),(int)valores[f][7]);
            }
            return revi;
        }
        return null;
    }
     
     public Citas[] citasHoy() {
        this.db.prepararSetencia("select * from citas where Fecha=? and Hora>?");
       
        DateTimeFormatter fo=DateTimeFormatter.ofPattern("HH:mm");
       
        Object[] param = {LocalDate.now().toString() ,fo.format(LocalTime.now())};
        System.out.println(LocalDate.now());
        Object[][] valores;
        valores = this.db.seleccionar(param);
         System.out.println("***************"+valores.length);
        if ( valores!=null&&valores.length > 0) {
          VehiculosDao vehi=new VehiculosDao(this.db);
            Citas []ci=new Citas [valores.length];
            for (int f = 0; f <= valores.length-1; f++) {
                String fe[] = valores[f][1].toString().split("-");
                int y = Integer.parseInt(fe[0]);
                int m = Integer.parseInt(fe[1]);
                int d = Integer.parseInt(fe[2]);
                String time[]=valores[f][2].toString().split(":");
                int h =Integer.parseInt(time[0]);
                int mi =Integer.parseInt(time[1]);
                int s =Integer.parseInt(time[2]);
                ci [f]=  new  Citas((int) valores[f][0], LocalDate.of(y, m, d),LocalTime.of(h, mi, s),vehi.buscar(new Vehiculos( (int)valores[f][3])));
            }
            
            return ci;
        }
        return null;
    }
}

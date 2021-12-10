/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controlador;

import BaseDatos.MariaDB;
import MVC.Modelo.Dao.VehiculosDao;
import MVC.Modelo.Vehiculos;
import Vistas.Vista;

/**
 *
 * @author usuario
 */
public class ControlVehiculo implements Control<Vehiculos>{
private Vehiculos vehiculo;
    private Vista vista;
    private MariaDB bd;
    private VehiculosDao dao;

    public ControlVehiculo( Vista vista) {
         this.vista = vista;
        this.bd=new MariaDB("127.0.0.1", "rtv", "root","");
        this.vehiculo=new Vehiculos ();
        this.dao=new VehiculosDao(this.bd);
    }

    public Vista getVista() {
        return vista;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public void guardar(Vehiculos clase) {
        
        if (dao.insertar(clase)) {
             this.vehiculo=clase;
             Object []mensaje={"OK","Registro agregado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al agregar :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void modificar(Vehiculos clase) {
         if (dao.modificar(clase)) {
             this.vehiculo=clase;
             Object []mensaje={"OK","Registro modificado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al modificar registro :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void eliminar(Vehiculos clase) {
         if (dao.eliminar(clase)) {
             this.vehiculo=null;
             Object []mensaje={"OK","Registro eliminado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al eliminar :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void cancelar() {
        if (this.vehiculo!=null) {
             Object []valores =new Object[1];
        valores[0]=this.vehiculo;
        vista.mostrar(valores);
        }
         
    }

    @Override
    public void buscar() {
       Vehiculos vehi[]=this.dao.listar();
        if (vehi !=null) {
            this.vista.mostrar(vehi);
        }else{
        Object mensaje[]={"ERROR","NO HAY RESULTADO"};
        this.vista.notificar(mensaje);
        
        } 
    }

    @Override
    public void filtrar(String des) {
       Vehiculos vehi[]=this.dao.filtrar(des);
        if (vehi !=null) {
            this.vista.mostrar(vehi);
        }else{
        Object mensaje[]={"ERROR","NO HAY RESULTADO"};
        this.vista.notificar(mensaje);
        
        }
    }
    
}

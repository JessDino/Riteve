/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controlador;

import BaseDatos.MariaDB;
import MVC.Modelo.Dao.UsuariosDao;
import Vistas.Vista;
import MVC.Modelo.Usuarios;

/**
 *
 * @author usuario
 */
public class ControlUsuarios implements Control<Usuarios>{
private Usuarios usuarios;
    private Vista vista;
    private MariaDB bd;
    private UsuariosDao dao;

    public ControlUsuarios( Vista vista) {
        this.vista = vista;
        this.bd=new MariaDB("127.0.0.1", "rtv", "root","");
        this.usuarios=new Usuarios ();
        this.dao=new UsuariosDao(this.bd);
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    
    @Override
    public void guardar(Usuarios clase) {
        
        if (dao.insertar(clase)) {
             this.usuarios=null;
             Object []mensaje={"OK","Registro agregado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al agregar :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void modificar(Usuarios clase) {
          if (dao.modificar(clase)) {
             this.usuarios=null;
             Object []mensaje={"OK","Registro modificado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al modificar registro :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void eliminar(Usuarios clase) {
          if (dao.eliminar(clase)) {
             this.usuarios=null;
             Object []mensaje={"OK","Registro eliminado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al eliminar :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void cancelar() {
          if (this.usuarios != null) {
            Object[] valores = new Object[1];
            valores[0] = this.usuarios;
            vista.mostrar(valores);
        }
    }

 
    @Override
    public void filtrar(String des) {
         Usuarios usua[]=this.dao.filtrar(des);
        if (usua !=null) {
            this.vista.mostrar(usua);
        }else{
        Object mensaje[]={"ERROR","NO HAY RESULTADO"};
        this.vista.notificar(mensaje);
        
        }
    }

    @Override
    public void listar() {
         Usuarios usua[]=this.dao.listar();
        if (usua !=null) {
            this.vista.mostrar(usua);
        }else{
        Object mensaje[]={"ERROR","NO HAY RESULTADO"};
        this.vista.notificar(mensaje);
    }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controlador;

import BaseDatos.MariaDB;

import MVC.Modelo.Dao.RevisionesDao;
import MVC.Modelo.Revisiones;
import Vistas.Vista;

/**
 *
 * @author usuario
 */
public class ControlRevisiones implements Control<Revisiones>{
private Revisiones revision;
    private Vista vista;
    private MariaDB bd;
    private RevisionesDao dao;

    public ControlRevisiones(Revisiones revision, Vista vista, MariaDB bd, RevisionesDao dao) {
          this.vista = vista;
        this.bd=new MariaDB("127.0.0.1", "padron", "root","");
        this.revision=new Revisiones();
        this.dao=new RevisionesDao(this.bd);
    }
    
    
    
    @Override
    public void guardar(Revisiones clase) {
         if (dao.insertar(clase)) {
             this.revision=clase;
             Object []mensaje={"OK","Registro agregado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al agregar :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void modificar(Revisiones clase) {
          if (dao.modificar(clase)) {
             this.revision=clase;
             Object []mensaje={"OK","Registro modificado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al modificar registro :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void eliminar(Revisiones clase) {
       if (dao.eliminar(clase)) {
             this.revision=null;
             Object []mensaje={"OK","Registro eliminado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al eliminar :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void cancelar() {
         Object []valores =new Object[1];
        valores[0]=this.revision;
        vista.mostrar(valores);
    }

   

    @Override
    public void filtrar(String des) {
          Revisiones revi[]=this.dao.filtrar(des);
        if (revi !=null) {
            this.vista.mostrar(revi);
        }else{
        Object mensaje[]={"ERROR","NO HAY RESULTADO"};
        this.vista.notificar(mensaje);
        
        }
    }

    @Override
    public void listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

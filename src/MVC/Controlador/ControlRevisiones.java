/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controlador;

import BaseDatos.MariaDB;
import MVC.Modelo.Citas;

import MVC.Modelo.Dao.RevisionesDao;
import MVC.Modelo.Revisiones;
import Vistas.Vista;
import XML.CrearXML;

/**
 *
 * @author usuario
 */
public class ControlRevisiones implements Control<Revisiones>{
private Revisiones revision;
    private Vista vista;
    public MariaDB bd;
    private RevisionesDao dao;

    public ControlRevisiones( Vista vista) {
          this.vista = vista;
        this.bd=new MariaDB("127.0.0.1", "rtv", "root","");
        this.revision=new Revisiones();
        this.dao=new RevisionesDao(this.bd);
    }

    public void setRevision(Revisiones revision) {
        this.revision = revision;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
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
        if (this.revision!=null) {
            Object []valores =new Object[1];
        valores[0]=this.revision;
        vista.mostrar(valores);
        }
         
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
         Revisiones revi[]=this.dao.listar();
        if (revi !=null) {
            this.vista.mostrar(revi);
        }else{
        Object mensaje[]={"ERROR","NO HAY RESULTADO"};
        this.vista.notificar(mensaje);
        
        }
    }
    public void expor(Revisiones re){
     Revisiones revi[]=this.dao.exportar(re);
     CrearXML xml= new CrearXML(revi);
        if (xml.crear()) {
            Object mensaje[]={"OK","SE HAN EXPORTADO LOS REGISTRO A C:\\Users\\usuario\\Documents\\"+re.getVehiculo().getNumeroDePlaca() + ".xml\""};
        this.vista.notificar(mensaje);
        }else{
        Object mensaje[]={"ERROR","NO SE HAN EXPORTADO LOS REGISTROS"};
        this.vista.notificar(mensaje);
        }
    
    }
    public Citas[] mostrarCitas() {
        
        Citas cita[] =  this.dao.citasHoy();
        if (cita != null) {
            return this.dao.citasHoy();
        } else {
           return null;
        }
        
        

    }
    
}

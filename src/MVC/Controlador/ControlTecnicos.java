/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controlador;

import BaseDatos.MariaDB;
import MVC.Modelo.Dao.TecnicosDao;
import MVC.Modelo.Tecnicos;
import Vistas.Vista;

/**
 *
 * @author usuario
 */
public class ControlTecnicos implements Control<Tecnicos> {
private Tecnicos tecnico;
    private Vista vista;
    private MariaDB bd;
    private TecnicosDao dao;

    public ControlTecnicos(Tecnicos tecnico, Vista vista, MariaDB bd, TecnicosDao dao) {
          this.vista = vista;
        this.bd=new MariaDB("127.0.0.1", "padron", "root","");
        this.tecnico=new Tecnicos();
        this.dao=new TecnicosDao(this.bd);
    }
    
    
    
    @Override
    public void guardar(Tecnicos clase) {
         
        if (dao.insertar(clase)) {
             this.tecnico=clase;
             Object []mensaje={"OK","Registro agregado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al agregar :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void modificar(Tecnicos clase) {
        if (dao.modificar(clase)) {
             this.tecnico=clase;
             Object []mensaje={"OK","Registro modificado"};
            vista.notificar(mensaje);
        }else{
          Object []mensaje={"ERROR","Error al modificar registro :"+this.bd.getError()};  
        vista.notificar(mensaje);
        }
    }

    @Override
    public void eliminar(Tecnicos clase) {
        if (dao.eliminar(clase)) {
             this.tecnico=null;
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
        valores[0]=this.tecnico;
        vista.mostrar(valores);
    }

    @Override
    public void buscar(Tecnicos clase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void filtrar(String des) {
         Tecnicos tec[]=this.dao.filtrar(des);
        if (tec !=null) {
            this.vista.mostrar(tec);
        }else{
        Object mensaje[]={"ERROR","NO HAY RESULTADO"};
        this.vista.notificar(mensaje);
        
        }
    }
    
}

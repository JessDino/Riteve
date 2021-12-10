/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controlador;

import BaseDatos.MariaDB;
import MVC.Modelo.Citas;
import MVC.Modelo.Dao.CitasDao;
import Vistas.Vista;

/**
 *
 * @author usuario
 */
public class ControlCitas implements Control<Citas> {

    private Citas cita;
    private Vista vista;
    private MariaDB bd;
    private CitasDao dao;

    public ControlCitas(Citas cita, Vista vista, MariaDB bd, CitasDao dao) {
        this.vista = vista;
        this.bd = new MariaDB("127.0.0.1", "padron", "root", "");
        this.cita = new Citas();
        this.dao = new CitasDao(this.bd);
    }

    @Override
    public void guardar(Citas clase) {
        if (dao.insertar(clase)) {
            this.cita = clase;
            Object[] mensaje = {"OK", "Registro agregado"};
            vista.notificar(mensaje);
        } else {
            Object[] mensaje = {"ERROR", "Error al agregar :" + this.bd.getError()};
            vista.notificar(mensaje);
        }
    }

    @Override
    public void modificar(Citas clase) {
        if (dao.modificar(clase)) {
            this.cita = clase;
            Object[] mensaje = {"OK", "Registro modificado"};
            vista.notificar(mensaje);
        } else {
            Object[] mensaje = {"ERROR", "Error al modificar registro :" + this.bd.getError()};
            vista.notificar(mensaje);
        }
    }

    @Override
    public void eliminar(Citas clase) {
        if (dao.eliminar(clase)) {
            this.cita = null;
            Object[] mensaje = {"OK", "Registro eliminado"};
            vista.notificar(mensaje);
        } else {
            Object[] mensaje = {"ERROR", "Error al eliminar :" + this.bd.getError()};
            vista.notificar(mensaje);
        }
    }

    @Override
    public void cancelar() {
        Object[] valores = new Object[1];
        valores[0] = this.cita;
        vista.mostrar(valores);
    }

   
    @Override
    public void filtrar(String des) {
        Citas cita[] = this.dao.filtrar(des);
        if (cita != null) {
            this.vista.mostrar(cita);
        } else {
            Object mensaje[] = {"ERROR", "NO HAY RESULTADO"};
            this.vista.notificar(mensaje);

        }
    }

    @Override
    public void listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

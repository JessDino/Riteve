/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo.Dao;

/**
 *
 * @author usuario
 */
public interface Dao<Clase> {
    public boolean insertar(Clase ob);
    public boolean modificar(Clase ob);
    public boolean eliminar(Clase ob);
    public Clase buscar (Clase ob);
    public Clase [] listar();
    public Clase [] filtrar(String fil);
    public boolean validarPK(Clase ob);
    public boolean validarUnicos(Clase ob);
    public boolean validarFK(Clase ob);
}

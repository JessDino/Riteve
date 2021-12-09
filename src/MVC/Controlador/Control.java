/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Controlador;

/**
 *
 * @author usuario
 */
public interface Control <Clase>{
    public void guardar(Clase clase);
    public void modificar(Clase clase);
    public void eliminar(Clase clase);
    public void cancelar();
    public void buscar(Clase clase);
    public void filtrar(String des);
}

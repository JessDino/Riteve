/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

/**
 *
 * @author usuario
 */
public interface Vista {
    public void cambiarEstados();
     public void limpiar();
      public void notificar(Object[] msj);
       public void mostrar(Object[] msj);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo;

import java.util.Date;

/**
 *
 * @author dinar
 */
public class Usuarios {
    private int cedula;
    private String nombre;
    private Date FechaNacimiento;
    private int telefono;
    private String correo;
    private String nombreUsuario;
    private String contrasena;
    private String tipoUsuario;

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
       
        if (cedula<999999999) {
            this.cedula = cedula;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
 
        if (nombre!= null && nombre.length()<=20) {
            this.nombre = nombre;
        }
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
        
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
       
        if (telefono < 100000000) {
            this.telefono = telefono;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
  
        if (correo!= null && correo.length()<=30) {
            this.correo = correo;
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        
        if (nombreUsuario!=null && nombreUsuario.length()<=10) {
            this.nombreUsuario = nombreUsuario;
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
    
        if (contrasena !=null && contrasena.length()<=12) {
            this.contrasena = contrasena;
        }
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        
        if (tipoUsuario !=null && tipoUsuario.length()<=11) {
           this.tipoUsuario = tipoUsuario;
        }
    }

    public Usuarios(int cedula, String nombre, Date FechaNacimiento, int telefono, String correo, String nombreUsuario, String contrasena, String tipoUsuario) {
        this.setCedula(cedula);
        this.setNombre(nombre);
        this.setFechaNacimiento(FechaNacimiento);
        this.setTelefono(telefono);
        this.setCorreo(correo);
        this.setNombreUsuario(nombreUsuario);
        this.setContrasena(contrasena);
        this.setTipoUsuario(tipoUsuario);
    }
public Usuarios(int cedula) {
        this(cedula,null,null,0,null,null,null,null);
    }
public Usuarios() {
        this(0,null,null,0,null,null,null,null);
    }
    @Override
    public String toString() {
        return cedula + " - " + nombre + " - " + FechaNacimiento + " - " + telefono + " - " + correo + " - " + nombreUsuario + " - " + contrasena + " - " + tipoUsuario;
    }
    
    public boolean requeridos(){
       return cedula != 0 && nombre != null && FechaNacimiento != null && telefono != 0 && correo != null && nombreUsuario!= null && contrasena != null && tipoUsuario != null;
    }
}

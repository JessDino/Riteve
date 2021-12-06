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
public class Tecnicos {
    private int cedula;
    private String nombre;
    private Date FechaNacimiento;
    private int telefono;
    private String correo;
    private int salario;

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

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        
        if (salario > 0 && salario < 999999999) {
            this.salario = salario;
        }
    }

    public Tecnicos(int cedula, String nombre, Date FechaNacimiento, int telefono, String correo, int salario) {
        this.setCedula(cedula);
        this.setNombre(nombre);
        this.setFechaNacimiento(FechaNacimiento);
        this.setTelefono(telefono);
        this.setCorreo(correo);
        this.setSalario(salario);
    }

    @Override
    public String toString() {
        return cedula + " - " + nombre + " - " + FechaNacimiento + " - " + telefono + " - " + correo + " - " + salario;
    }
    
    public boolean requeridos(){
       return cedula != 0 && nombre != null && FechaNacimiento != null && telefono != 0 && correo != null && salario !=0;
    }
}

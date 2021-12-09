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
public class Vehiculos {
    private int numeroDePlaca;
    private String marca;
    private String modelo;
    private int anio;
    private Date fechaInscripcion;
    private int cedulaPropietario;
    private String nombrePropietario;

    public int getNumeroDePlaca() {
        return numeroDePlaca;
    }

    public void setNumeroDePlaca(int numeroDePlaca) {
        
         if (this.numeroDePlaca<999999) {
            this.numeroDePlaca = numeroDePlaca;
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
     
        if (marca!= null && marca.length()<=12) {
            this.marca = marca;
        }
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        
        if (modelo!= null && modelo.length()<=15) {
            this.modelo = modelo;
        }
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        
        if (this.anio < 2050 && anio > 1960) {
            this.anio = anio;
        }
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getCedulaPropietario() {
        return cedulaPropietario;
    }

    public void setCedulaPropietario(int cedulaPropietario) {
        
        if (cedulaPropietario<999999999) {
            this.cedulaPropietario = cedulaPropietario;
        }
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        
         if (nombrePropietario!= null && nombrePropietario.length()<=30) {
            this.nombrePropietario = nombrePropietario;
        }
    }

    public Vehiculos(int numeroDePlaca, String marca, String modelo, int anio, Date fechaInscripcion, int cedulaPropietario, String nombrePropietario) {
        this.setNumeroDePlaca(numeroDePlaca);
        this.setMarca(marca);
        this.setModelo(modelo);
        this.setAnio(anio);
        this.setFechaInscripcion(fechaInscripcion);
        this.setCedulaPropietario(cedulaPropietario);
        this.setNombrePropietario(nombrePropietario);
    }
public Vehiculos(int numeroDePlaca) {
        this(numeroDePlaca,null,null,1960,null,0,null);
    }
    @Override
    public String toString() {
        return  numeroDePlaca + " - " + marca + " - " + modelo + " - " + anio + " - " + fechaInscripcion + " - " + cedulaPropietario + " - " + nombrePropietario;
    }
  
    public boolean requeridos(){
       return numeroDePlaca != 0 && marca != null && modelo != null && anio != 0 && fechaInscripcion != null && cedulaPropietario !=0 && nombrePropietario != null;
    }
}

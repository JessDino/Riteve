/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 *
 * @author usuario
 */
public class Citas {
    private int idCita;
    private LocalDate fecha;
    private LocalTime hora;
    private Vehiculos vehiculo;

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        if (idCita!=0) {
            this.idCita = idCita;
        }
        
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {

        return hora;
    }

    public void setHora(LocalTime hora) {
        if (hora!=null) {
          this.hora = hora;  
        }
        
    }

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Citas(int idCita, LocalDate fecha, LocalTime hora, Vehiculos vehiculo) {
        this.setIdCita(idCita);
        this.setFecha(fecha);
        this.setHora(hora);
        this.setVehiculo(vehiculo);
    }
    public Citas() {
        this(0,null,null,null);
    }
    
    public boolean requeridos(){
  return idCita!=0 && fecha!=null && hora!=null && vehiculo!=null&& vehiculo.requeridos();
    }

    @Override
    public String toString() {
        return idCita + "-" + fecha + "-" + hora + "-" + vehiculo ;
    }
    
}

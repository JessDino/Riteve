/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC.Modelo;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Revisiones {
    private Date fecha;
    private String hora;
    private Tecnicos tecnico;
    private String tipoRevision;
    private String observacion;
    private String estado;
private Vehiculos vehiculo;

    public Vehiculos getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculos vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        if (hora!=null) {
            this.hora = hora;
        }
        
    }

    public Tecnicos getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnicos tecnico) {
        this.tecnico = tecnico;
    }

    public String getTipoRevision() {
        return tipoRevision;
    }

    public void setTipoRevision(String tipoRevision) {
        if (tipoRevision!=null && tipoRevision.length()<=12) {
             this.tipoRevision = tipoRevision;
        }
       
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        if (observacion!=null&&observacion.length()<=100 ) {
           this.observacion = observacion; 
        }
        
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado!=null &&estado.length()<=9) {
            this.estado = estado;
        }
        
    }

    public Revisiones(Date fecha, String hora, Tecnicos tecnico, String tipoRevision, String observacion, String estado,Vehiculos vehiculo) {
        this.setFecha(fecha);
        this.setHora(hora);
        this.setTecnico(tecnico);
        this.setTipoRevision(tipoRevision);
        this.setObservacion(observacion);
        this.setEstado(estado);
        this.setVehiculo(vehiculo);
    }
    public Revisiones() {
        this(null,null,null,null,null,null,null);
        
    }
    public boolean requeridos(){
    return fecha!=null && hora!=null && tecnico!=null&&tecnico.requeridos()&& tipoRevision!=null&& observacion!=null&& estado!=null && vehiculo!=null && vehiculo.requeridos();
    }

    @Override
    public String toString() {
        return fecha + "-" + hora + "-" + tecnico + "-" + tipoRevision + "-" + observacion + "-" + estado+"-"+vehiculo ;
    }
    
}

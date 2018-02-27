/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Area")
public class Area {
    @Id
    @Column(name = "idArea")
    private int IdCargo;
    
    @Column(name = "NombreArea")
    private String NombreCargo;
    
    @Column(name = "Descripcion")
    private String Descripcion;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Canal")
    private Canal Canal;

    public Area() {
    }

    public Area(int IdCargo, String NombreCargo, String Descripcion, Canal Canal) {
        this.IdCargo = IdCargo;
        this.NombreCargo = NombreCargo;
        this.Descripcion = Descripcion;
        this.Canal = Canal;
    }

    public int getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(int IdCargo) {
        this.IdCargo = IdCargo;
    }

    public String getNombreCargo() {
        return NombreCargo;
    }

    public void setNombreCargo(String NombreCargo) {
        this.NombreCargo = NombreCargo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Canal getCanal() {
        return Canal;
    }

    public void setCanal(Canal Canal) {
        this.Canal = Canal;
    }
    
    
}

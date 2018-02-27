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
    private String NombreArea;
    
    @Column(name = "Descripcion")
    private String Descripcion;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Canal")
    private Canal Canal;

    public Area() {
    }

    public Area(int IdCargo, String NombreArea, String Descripcion, Canal Canal) {
        this.IdCargo = IdCargo;
        this.NombreArea = NombreArea;
        this.Descripcion = Descripcion;
        this.Canal = Canal;
    }

    public int getIdCargo() {
        return IdCargo;
    }

    public void setIdCargo(int IdCargo) {
        this.IdCargo = IdCargo;
    }

    public String getNombreArea() {
        return NombreArea;
    }

    public void setNombreArea(String NombreArea) {
        this.NombreArea = NombreArea;
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

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
    
    @Column(name = "Estado")
    private String Estado;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Canal")
    private Canal Canal;

    public Area() {
    }

    public Area(String NombreArea, String Descripcion, String Estado, Canal Canal) {
        this.NombreArea = NombreArea;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
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

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Canal getCanal() {
        return Canal;
    }

    public void setCanal(Canal Canal) {
        this.Canal = Canal;
    }
}

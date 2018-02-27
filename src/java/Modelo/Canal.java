package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Canal")
public class Canal {
    
    @Id
    @Column(name = "idCanal")
    private int IdCargo;
    
    @Column(name = "NombreCanal")
    private String NombreCargo;
    
    @Column(name = "Descripcion")
    private String Descripcion;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sector")
    private Sector Sector;

    public Canal() {
    }

    public Canal(int IdCargo, String NombreCargo, String Descripcion, Sector Sector) {
        this.IdCargo = IdCargo;
        this.NombreCargo = NombreCargo;
        this.Descripcion = Descripcion;
        this.Sector = Sector;
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

    public Sector getSector() {
        return Sector;
    }

    public void setSector(Sector Sector) {
        this.Sector = Sector;
    }
    
}

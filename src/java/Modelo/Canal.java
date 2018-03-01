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
    
    @Column(name = "Estado")
    private String Estado;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sector")
    private Sector Sector;

    public Canal() {
    }

    public Canal(String NombreCargo, String Descripcion, String Estado, Sector Sector) {
        this.NombreCargo = NombreCargo;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
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

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Sector getSector() {
        return Sector;
    }

    public void setSector(Sector Sector) {
        this.Sector = Sector;
    }

}

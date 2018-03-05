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
    private int IdCanal;
    
    @Column(name = "NombreCanal")
    private String NombreCanal;
    
    @Column(name = "Descripcion")
    private String Descripcion;
    
    @Column(name = "Estado")
    private String Estado;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sector")
    private Sector Sector;

    public Canal() {
    }

    public Canal(String NombreCanal, String Descripcion, String Estado, Sector Sector) {
        this.NombreCanal = NombreCanal;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
        this.Sector = Sector;
    }

    public int getIdCanal() {
        return IdCanal;
    }

    public void setIdCanal(int IdCanal) {
        this.IdCanal = IdCanal;
    }

    public String getNombreCanal() {
        return NombreCanal;
    }

    public void setNombreCargo(String NombreCargo) {
        this.NombreCanal = NombreCargo;
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

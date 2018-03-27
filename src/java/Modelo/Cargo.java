package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cargo")
public class Cargo {
 
    @Id
    @Column(name = "IdCargo")
    private int IdCargo;
    
    @Column(name = "NombreCargo")
    private String NombreCargo;
    
    @Column(name = "Descripcion")
    private String Descripcion;
    
    @Column(name = "Tipo")
    private String Tipo;
    
    @Column(name = "Estado")
    private String Estado;
    
    @Column(name = "Sector")
    private String Sector;
    
    @Column(name = "Canal")
    private String Canal;
    
    @Column(name = "Area")
    private String Area;
    
    public Cargo() {
    }

    public Cargo(String NombreCargo, String Descripcion, String Tipo, String Estado, String Sector, String Canal, String Area) {
        this.NombreCargo = NombreCargo;
        this.Descripcion = Descripcion;
        this.Tipo = Tipo;
        this.Estado = Estado;
        this.Sector = Sector;
        this.Canal = Canal;
        this.Area = Area;
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

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getSector() {
        return Sector;
    }

    public void setSector(String Sector) {
        this.Sector = Sector;
    }

    public String getCanal() {
        return Canal;
    }

    public void setCanal(String Canal) {
        this.Canal = Canal;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

}

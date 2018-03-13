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
    @Column(name = "IdArea")
    private int IdArea;
    
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

<<<<<<< HEAD
    public Area(String NombreArea, String Descripcion, String Estado, Canal Canal) {
        this.NombreArea = NombreArea;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
        this.Canal = Canal;
    }

=======
>>>>>>> master
    public int getIdArea() {
        return IdArea;
    }

<<<<<<< HEAD
    public void setIdArea(int IdCargo) {
        this.IdArea = IdCargo;
=======
    public void setIdArea(int IdArea) {
        this.IdArea = IdArea;
>>>>>>> master
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

    public Area(String NombreArea, String Descripcion, String Estado, Canal Canal) {
        this.NombreArea = NombreArea;
        this.Descripcion = Descripcion;
        this.Estado = Estado;
        this.Canal = Canal;
    }

}

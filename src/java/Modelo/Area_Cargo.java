package Modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "area_cargo")
public class Area_Cargo {
    
    @Id
    private int idArea_Cargo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cargo")
    private Cargo Cargo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Area")
    private Area Area;

    public Area_Cargo() {
    }

    public Area_Cargo(Cargo Cargo, Area Area) {
        this.Cargo = Cargo;
        this.Area = Area;
    }

    public int getIdArea_Cargo() {
        return idArea_Cargo;
    }

    public void setIdArea_Cargo(int idArea_Cargo) {
        this.idArea_Cargo = idArea_Cargo;
    }

    public Cargo getCargo() {
        return Cargo;
    }

    public void setCargo(Cargo Cargo) {
        this.Cargo = Cargo;
    }

    public Area getArea() {
        return Area;
    }

    public void setArea(Area Area) {
        this.Area = Area;
    }
    
}

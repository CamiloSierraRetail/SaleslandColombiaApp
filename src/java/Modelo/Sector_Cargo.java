package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Sector_Cargo")
public class Sector_Cargo {
    
    @Id
    @Column(name = "idSector_Cargo")
    private int idSector_Cargo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cargo")
    private Cargo Cargo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sector")
    private Sector Sector;

    public Sector_Cargo() {
    }

    public Sector_Cargo(Cargo Cargo, Sector Sector) {
        this.Cargo = Cargo;
        this.Sector = Sector;
    }

    public int getIdSector_Cargo() {
        return idSector_Cargo;
    }

    public void setIdSector_Cargo(int idSector_Cargo) {
        this.idSector_Cargo = idSector_Cargo;
    }

    public Cargo getCargo() {
        return Cargo;
    }

    public void setCargo(Cargo Cargo) {
        this.Cargo = Cargo;
    }

    public Sector getSector() {
        return Sector;
    }

    public void setSector(Sector Sector) {
        this.Sector = Sector;
    }
    
}

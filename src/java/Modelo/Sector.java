package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sector")
public class Sector {
    
    @Id
    @Column(name = "idSector")
    private int IdSector;
    
    @Column(name = "NombreSector")
    private String NombreSector;
    
    @Column(name = "Descripcion")
    private String DescripcionSector;

    public Sector() {
    }

    public Sector(String NombreSector, String DescripcionSector) {
        this.NombreSector = NombreSector;
        this.DescripcionSector = DescripcionSector;
    }

    public int getIdSector() {
        return IdSector;
    }

    public void setIdSector(int IdSector) {
        this.IdSector = IdSector;
    }

    public String getNombreSector() {
        return NombreSector;
    }

    public void setNombreSector(String NombreSector) {
        this.NombreSector = NombreSector;
    }

    public String getDescripcionSector() {
        return DescripcionSector;
    }

    public void setDescripcionSector(String DescripcionSector) {
        this.DescripcionSector = DescripcionSector;
    }
    
    
    
}

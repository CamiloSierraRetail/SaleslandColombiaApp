package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cargo")
public class Cargo {
 
    @Id
    @Column(name = "idCargo")
    private int IdCargo;
    
    @Column(name = "NombreCargo")
    private String NombreCargo;
    
    @Column(name = "Descripcion")
    private String Descripcion;
    
    @Column(name = "Salario")
    private String Salario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Sector")
    private Canal Sector;

    public Cargo() {
    }

    public Cargo(String NombreCargo, String Descripcion, String Salario, Canal Sector) {
        this.NombreCargo = NombreCargo;
        this.Descripcion = Descripcion;
        this.Salario = Salario;
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

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String Salario) {
        this.Salario = Salario;
    }

    public Canal getSector() {
        return Sector;
    }

    public void setSector(Canal Sector) {
        this.Sector = Sector;
    }
    
}

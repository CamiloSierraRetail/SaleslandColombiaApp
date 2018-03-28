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
    
    public Cargo() {
    }

    public Cargo(String NombreCargo, String Descripcion, String Tipo, String Estado) {
        this.NombreCargo = NombreCargo;
        this.Descripcion = Descripcion;
        this.Tipo = Tipo;
        this.Estado = Estado;
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

}

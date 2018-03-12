package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Administrador")
public class Administrador {

    @Id
    @Column(name = "idAdministrador")
    private int IdAdministrador;
    
    @Column(name = "Sector")
    private String Sector;
    
    @Column(name = "Canal")
    private String Canal;
    
    @Column(name = "Area")
    private String Area;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario")
    private Usuario Usuario;

    public Administrador() {
    }

    public Administrador(String Sector, String Canal, String Area, Usuario Usuario) {
        this.Sector = Sector;
        this.Canal = Canal;
        this.Area = Area;
        this.Usuario = Usuario;
    }

    public int getIdAdministrador() {
        return IdAdministrador;
    }

    public void setIdAdministrador(int IdAdministrador) {
        this.IdAdministrador = IdAdministrador;
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

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    
}

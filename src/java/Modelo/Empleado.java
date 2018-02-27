
package Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Empleado")
public class Empleado {
    @Id
    @Column(name = "idEmpleado")
    private int IdEmpleado;
        
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Area")
    private Area Area;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario")
    private Usuario Usuario;

    public Empleado() {
    }

    public Empleado(int IdEmpleado, Area Area, Usuario Usuario) {
        this.IdEmpleado = IdEmpleado;
        this.Area = Area;
        this.Usuario = Usuario;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public Area getArea() {
        return Area;
    }

    public void setArea(Area Area) {
        this.Area = Area;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }
    
    
}

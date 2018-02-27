package Modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ingreso")
public class Ingreso {

    @Id
    @Column(name = "idIngreso")
    private int IdIngreso;
    
    @Column(name = "FechaIngreso")
    private Date FechaIngreso;
    
    @Column(name = "FechaSalida")
    private Date FechaSalida;
    
    @Column(name = "Tipo")
    private String Tipo;
    
    @Column(name = "Observacion")
    private String Observacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario")
    private Usuario Usuario;

    public Ingreso() {
    }

    public Ingreso(Date FechaIngreso, Date FechaSalida, String Tipo, String Observacion, Usuario Usuario) {
        this.FechaIngreso = FechaIngreso;
        this.FechaSalida = FechaSalida;
        this.Tipo = Tipo;
        this.Observacion = Observacion;
        this.Usuario = Usuario;
    }

    public int getIdIngreso() {
        return IdIngreso;
    }

    public void setIdIngreso(int IdIngreso) {
        this.IdIngreso = IdIngreso;
    }

    public Date getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(Date FechaIngreso) {
        this.FechaIngreso = FechaIngreso;
    }

    public Date getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(Date FechaSalida) {
        this.FechaSalida = FechaSalida;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }
    
}

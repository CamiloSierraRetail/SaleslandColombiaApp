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
    
    @Column(name = "Fecha")
    private Date Fecha;
    
    @Column(name = "Hora")
    private Date Hora;
    
    @Column(name = "Tipo")
    private String Tipo;
    
    @Column(name = "Modalidad")
    private String Modalidad;
    
    @Column(name = "Observacion")
    private String Observacion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario")
    private Usuario Usuario;

    public Ingreso() {
    }

    public Ingreso(Date Fecha, Date Hora, String Tipo, String Modalidad, String Observacion, Usuario Usuario) {
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.Tipo = Tipo;
        this.Modalidad = Modalidad;
        this.Observacion = Observacion;
        this.Usuario = Usuario;
    }

    public int getIdIngreso() {
        return IdIngreso;
    }

    public void setIdIngreso(int IdIngreso) {
        this.IdIngreso = IdIngreso;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Date getHora() {
        return Hora;
    }

    public void setHora(Date Hora) {
        this.Hora = Hora;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getModalidad() {
        return Modalidad;
    }

    public void setModalidad(String Modalidad) {
        this.Modalidad = Modalidad;
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

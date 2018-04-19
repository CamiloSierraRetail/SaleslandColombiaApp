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
    
    @Column(name = "Dia")
    private String Dia;
    
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
    
    @Column(name = "Horario")
    private String Horario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario")
    private Usuario Usuario;

    public Ingreso() {
    }

    public Ingreso(String Dia, Date Fecha, Date Hora, String Tipo, String Modalidad, String Observacion, String Horario, Usuario Usuario) {
        this.Dia = Dia;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.Tipo = Tipo;
        this.Modalidad = Modalidad;
        this.Observacion = Observacion;
        this.Horario = Horario;
        this.Usuario = Usuario;
    }

    public int getIdIngreso() {
        return IdIngreso;
    }

    public void setIdIngreso(int IdIngreso) {
        this.IdIngreso = IdIngreso;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String Dia) {
        this.Dia = Dia;
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

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String Horario) {
        this.Horario = Horario;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

   
    
}

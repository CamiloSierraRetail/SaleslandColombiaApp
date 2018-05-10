package Modelo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Permiso")
public class Permiso {

    @Id
    @Column(name = "idPermiso")
    private int idPermiso;
    
    @Column(name = "Motivo")
    private String Motivo;
    
    @Column(name = "Descripcion")
    private String Descripcion;
    
    @Column(name = "FechaInicio")
    private Date FechaInicio;
    
    @Column(name = "HoraInicio")
    private Date HoraInicio;
    
    @Column(name = "FechaFin")
    private Date FechaFin;
    
    @Column(name = "HoraFin")
    private Date HoraFin;
    
    @Column(name = "DocAnexo")
    private String DocAnexo;
    
    @Column(name = "Estado")
    private String Estado;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsuarioEnvia")
    private Usuario UsuarioEnvia;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsuarioRecibe")
    private Usuario UsuarioRecibe;

    public Permiso() {
    }

    public Permiso(String Motivo, String Descripcion, Date FechaInicio, Date HoraInicio, Date FechaFin, Date HoraFin, String DocAnexo, String Estado, Usuario UsuarioEnvia, Usuario UsuarioRecibe) {
        this.Motivo = Motivo;
        this.Descripcion = Descripcion;
        this.FechaInicio = FechaInicio;
        this.HoraInicio = HoraInicio;
        this.FechaFin = FechaFin;
        this.HoraFin = HoraFin;
        this.DocAnexo = DocAnexo;
        this.Estado = Estado;
        this.UsuarioEnvia = UsuarioEnvia;
        this.UsuarioRecibe = UsuarioRecibe;
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public Date getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(Date HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }

    public Date getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(Date HoraFin) {
        this.HoraFin = HoraFin;
    }

    public String getDocAnexo() {
        return DocAnexo;
    }

    public void setDocAnexo(String DocAnexo) {
        this.DocAnexo = DocAnexo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Usuario getUsuarioEnvia() {
        return UsuarioEnvia;
    }

    public void setUsuarioEnvia(Usuario UsuarioEnvia) {
        this.UsuarioEnvia = UsuarioEnvia;
    }

    public Usuario getUsuarioRecibe() {
        return UsuarioRecibe;
    }

    public void setUsuarioRecibe(Usuario UsuarioRecibe) {
        this.UsuarioRecibe = UsuarioRecibe;
    }        
}

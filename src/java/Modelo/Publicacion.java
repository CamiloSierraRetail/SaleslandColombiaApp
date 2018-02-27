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
@Table(name = "Publicacion")
public class Publicacion {

    @Id
    @Column(name = "idPublicacion")
    private int IdPublicacion;
    
    @Column(name = "Titulo")
    private String Titulo;
    
    @Column(name = "Archivo")
    private String Archivo;
    
    @Column(name = "Contenido")
    private String Contenido;
    
    @Column(name = "Fecha")
    private Date Fecha;
    
    @Column(name = "Hora")
    private String Hora;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario")
    private Usuario usuario;

    public Publicacion() {
    }

    public Publicacion(String Titulo, String Archivo, String Contenido, Date Fecha, String Hora, Usuario usuario) {
        this.Titulo = Titulo;
        this.Archivo = Archivo;
        this.Contenido = Contenido;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.usuario = usuario;
    }

    public int getIdPublicacion() {
        return IdPublicacion;
    }

    public void setIdPublicacion(int IdPublicacion) {
        this.IdPublicacion = IdPublicacion;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getArchivo() {
        return Archivo;
    }

    public void setArchivo(String Archivo) {
        this.Archivo = Archivo;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

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
@Table(name = "Permiso")
public class Permiso {

    @Id
    @Column(name = "idPermiso")
    private int idPermiso;
    
    @Column(name = "Motivo")
    private String Motivo;
    
    @Column(name = "Descripcion")
    private String Descripcion;
    
    @Column(name = "Inicio")
    private Date Inicio;
    
    @Column(name = "Fin")
    private Date Fin;
    
    @Column(name = "DocAnexo")
    private String DocAnexo;
    
    @Column(name = "Estado")
    private String Estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Usuario")
    private Usuario Usuario;

    public Permiso() {
    }

    public Permiso(String Motivo, String Descripcion, Date Inicio, Date Fin, String DocAnexo, String Estado, Usuario Usuario) {
        this.Motivo = Motivo;
        this.Descripcion = Descripcion;
        this.Inicio = Inicio;
        this.Fin = Fin;
        this.DocAnexo = DocAnexo;
        this.Estado = Estado;
        this.Usuario = Usuario;
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

    public Date getInicio() {
        return Inicio;
    }

    public void setInicio(Date Inicio) {
        this.Inicio = Inicio;
    }

    public Date getFin() {
        return Fin;
    }

    public void setFin(Date Fin) {
        this.Fin = Fin;
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

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }
    
}

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
@Table(name = "Usuario")
public class Usuario {
  
    @Id
    @Column(name = "idUsuario")
    private int IdUsuario;
    
    @Column(name = "Documento")
    private String Documento;
    
    @Column(name = "Nombre")
    private String Nombre;
    
    @Column(name = "Apellido")
    private String Apellido;
    
    @Column(name = "Direccion")
    private String Direccion;
    
    @Column(name = "Telefono")
    private String Telefono;
    
    @Column(name = "Celular")
    private String Celular;
    
    @Column(name = "Genero")
    private String Genero;
    
    @Column(name = "Email")
    private String Email;
    
    @Column(name = "FechaNacimiento")
    private Date FechaNacimiento;
    
    @Column(name = "Contrasenia")
    private String Contrasenia;
    
    @Column(name = "Foto")
    private String Foto;
    
    @Column(name = "Estado")
    private String Estado;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cargo")
    private Cargo Cargo;

    public Usuario() {
    }

    public Usuario(String Documento, String Nombre, String Apellido, String Direccion, String Telefono, String Celular, String Genero, String Email, Date FechaNacimiento, String Contrasenia, String Foto, String Estado, Cargo Cargo) {
        this.Documento = Documento;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Celular = Celular;
        this.Genero = Genero;
        this.Email = Email;
        this.FechaNacimiento = FechaNacimiento;
        this.Contrasenia = Contrasenia;
        this.Foto = Foto;
        this.Estado = Estado;
        this.Cargo = Cargo;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Cargo getCargo() {
        return Cargo;
    }

    public void setCargo(Cargo Cargo) {
        this.Cargo = Cargo;
    }
    
}

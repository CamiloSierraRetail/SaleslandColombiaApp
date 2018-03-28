package Modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "canal_cargo")
public class Canal_Cargo {
    
    
    @Id
    private int idCanal_Cargo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cargo")
    private Cargo Cargo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Canal")
    private Canal Canal;

    public Canal_Cargo() {
    }

    public Canal_Cargo(Cargo Cargo, Canal Canal) {
        this.Cargo = Cargo;
        this.Canal = Canal;
    }    
    
    public int getIdCanal_Cargo() {
        return idCanal_Cargo;
    }

    public void setIdCanal_Cargo(int idCanal_Cargo) {
        this.idCanal_Cargo = idCanal_Cargo;
    }

    public Cargo getCargo() {
        return Cargo;
    }

    public void setCargo(Cargo Cargo) {
        this.Cargo = Cargo;
    }

    public Canal getCanal() {
        return Canal;
    }

    public void setCanal(Canal Canal) {
        this.Canal = Canal;
    }
    
}

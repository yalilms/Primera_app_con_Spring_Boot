package dam.saruman.entity;

import jakarta.persistence.*;

@Entity
@Table(name="enemigosdelestado")
public class Enemigo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String afiliacionpolitica;

    @Column
    private String pais;

    public Enemigo() {
    }

    public Enemigo(Long id, String nombre, String afiliacionpolitica, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.afiliacionpolitica = afiliacionpolitica;
        this.pais = pais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAfiliacionpolitica() {
        return afiliacionpolitica;
    }

    public void setAfiliacionpolitica(String afiliacionpolitica) {
        this.afiliacionpolitica = afiliacionpolitica;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}

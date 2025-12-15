package dam.saruman.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Document marca esta clase como un documento de MongoDB (equivalente a @Entity en JPA)
// "enemigosdelestado" es el nombre de la colección en MongoDB (equivalente a una tabla en SQL)
@Document(collection = "enemigosdelestado")
public class Enemigo {
    @Id // En MongoDB, el _id es de tipo String por defecto
    private String id;

    // En MongoDB no necesitamos @Column, los campos se guardan automáticamente
    private String nombre;
    private String afiliacionpolitica;
    private String pais;

    public Enemigo() {
    }

    // Constructor con todos los parámetros - id ahora es String
    public Enemigo(String id, String nombre, String afiliacionpolitica, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.afiliacionpolitica = afiliacionpolitica;
        this.pais = pais;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

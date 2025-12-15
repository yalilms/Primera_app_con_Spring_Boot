package dam.saruman.service;

import dam.saruman.entity.Enemigo;
import dam.saruman.repository.EnemigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemigoService {
    @Autowired
    private EnemigoRepository enemigoRepository;

    public List<Enemigo> obtenerEnemigos (){
        List<Enemigo> enemigos = enemigoRepository.findAll();
        if(enemigos.isEmpty()){
            System.out.println("No enemigos");
        } else {
            System.out.println("Sí hay enemigos");
            enemigos.forEach(enemigo -> System.out.println(enemigo.getNombre()));
        }
        return enemigos;
    }

    public Enemigo crearEnemigo(Enemigo enemigo) {
        // Compruebo si ya hay un enemigo con ese nombre
        List<Enemigo> enemigoExistente = enemigoRepository.findByNombre(enemigo.getNombre());
        if(!enemigoExistente.isEmpty()) {
            throw new IllegalArgumentException("Ya existe un enemigo con el nombre: " + enemigo.getNombre());
        }
        return enemigoRepository.save(enemigo);
    }

    // Cambiado Long a String porque el id en MongoDB es String
    public Enemigo actualizarEnemigo(String id, Enemigo enemigo) {
        Enemigo enemigoExistente = enemigoRepository.findById(id).orElse(null);
        if(enemigoExistente != null) {
            enemigoExistente.setNombre(enemigo.getNombre());
            enemigoExistente.setAfiliacionpolitica(enemigo.getAfiliacionpolitica());
            enemigoExistente.setPais(enemigo.getPais());
            return enemigoRepository.save(enemigoExistente);
        }
        return null;
    }

    // Cambiado Long a String
    public void eliminarEnemigo(String id) {
        enemigoRepository.deleteById(id);
    }

    public List<Enemigo> buscarPorNombre(String nombre) {
        return enemigoRepository.findByNombre(nombre);
    }

    public List<Enemigo> obtenerEnemigosOrdenados() {
        List<Enemigo> enemigos = enemigoRepository.findAll();
        // Los ordeno por nombre alfabéticamente
        enemigos.sort((e1, e2) -> e1.getNombre().compareToIgnoreCase(e2.getNombre()));
        return enemigos;
    }
}

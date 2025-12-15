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
        return enemigoRepository.save(enemigo);
    }

    public Enemigo actualizarEnemigo(Long id, Enemigo enemigo) {
        Enemigo enemigoExistente = enemigoRepository.findById(id).orElse(null);
        if(enemigoExistente != null) {
            enemigoExistente.setNombre(enemigo.getNombre());
            enemigoExistente.setAfiliacionpolitica(enemigo.getAfiliacionpolitica());
            enemigoExistente.setPais(enemigo.getPais());
            return enemigoRepository.save(enemigoExistente);
        }
        return null;
    }

    public void eliminarEnemigo(Long id) {
        enemigoRepository.deleteById(id);
    }
}

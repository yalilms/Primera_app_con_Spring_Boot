package dam.saruman.repository;

/*
Esto va a ser para operaciones CRUD con MongoDB
MongoRepository nos da los métodos básicos: save, findAll, findById, deleteById, etc.
*/

import dam.saruman.entity.Enemigo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

// Cambio de JpaRepository a MongoRepository
// El id ahora es String en lugar de Long
public interface EnemigoRepository extends MongoRepository<Enemigo, String> {

    // Spring Data MongoDB crea automáticamente la consulta por el nombre del método
    List<Enemigo> findByNombre(String nome);


}

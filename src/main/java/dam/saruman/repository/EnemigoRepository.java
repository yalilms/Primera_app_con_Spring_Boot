package dam.saruman.repository;

/*
Esto va a ser para operaciones CRUD
*/

import dam.saruman.entity.Enemigo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface EnemigoRepository extends JpaRepository<Enemigo, Long> {

    List<Enemigo> findByNombre(String nome);

    
}

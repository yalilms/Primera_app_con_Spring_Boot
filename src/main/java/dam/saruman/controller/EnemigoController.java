package dam.saruman.controller;

import dam.saruman.entity.Enemigo;
import dam.saruman.service.EnemigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnemigoController {
    @Autowired
    private EnemigoService enemigoService;

    @GetMapping("/enemigo")
    public List<Enemigo> getEnemigos(){
        return enemigoService.obtenerEnemigos();
    }

    @PostMapping("/enemigo")
    public Enemigo crearEnemigo(@RequestBody Enemigo enemigo) {
        return enemigoService.crearEnemigo(enemigo);
    }

    @PutMapping("/enemigo/{id}")
    public Enemigo actualizarEnemigo(@PathVariable Long id, @RequestBody Enemigo enemigo) {
        return enemigoService.actualizarEnemigo(id, enemigo);
    }

    @DeleteMapping("/enemigo/{id}")
    public void eliminarEnemigo(@PathVariable Long id) {
        enemigoService.eliminarEnemigo(id);
    }
}

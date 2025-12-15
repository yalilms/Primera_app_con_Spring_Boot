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

    // Cambiado Long a String para MongoDB
    @PutMapping("/enemigo/{id}")
    public Enemigo actualizarEnemigo(@PathVariable String id, @RequestBody Enemigo enemigo) {
        return enemigoService.actualizarEnemigo(id, enemigo);
    }

    // Cambiado Long a String para MongoDB
    @DeleteMapping("/enemigo/{id}")
    public void eliminarEnemigo(@PathVariable String id) {
        enemigoService.eliminarEnemigo(id);
    }
}

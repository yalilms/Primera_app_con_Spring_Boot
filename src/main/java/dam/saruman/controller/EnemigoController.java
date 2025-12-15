package dam.saruman.controller;

import dam.saruman.entity.Enemigo;
import dam.saruman.service.EnemigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> crearEnemigo(@Valid @RequestBody Enemigo enemigo) {
        try {
            Enemigo nuevoEnemigo = enemigoService.crearEnemigo(enemigo);
            return ResponseEntity.ok(nuevoEnemigo);
        } catch (IllegalArgumentException e) {
            // Si el nombre ya existe devuelvo error 400
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @GetMapping("/enemigo/buscar")
    public List<Enemigo> buscarPorNombre(@RequestParam String nombre) {
        return enemigoService.buscarPorNombre(nombre);
    }

    @GetMapping("/enemigo/ordenados")
    public List<Enemigo> getEnemigosOrdenados() {
        return enemigoService.obtenerEnemigosOrdenados();
    }

    @GetMapping("/enemigo/csv")
    public ResponseEntity<String> descargarCSV() {
        List<Enemigo> enemigos = enemigoService.obtenerEnemigos();

        // Construyo el CSV
        StringBuilder csv = new StringBuilder();
        csv.append("ID,Nombre,Afiliacion Politica,Pais\n");

        for (Enemigo enemigo : enemigos) {
            csv.append(enemigo.getId()).append(",")
               .append(enemigo.getNombre()).append(",")
               .append(enemigo.getAfiliacionpolitica()).append(",")
               .append(enemigo.getPais()).append("\n");
        }

        // Para que se descargue como archivo
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=enemigos.csv");
        headers.add("Content-Type", "text/csv");

        return new ResponseEntity<>(csv.toString(), headers, HttpStatus.OK);
    }
}

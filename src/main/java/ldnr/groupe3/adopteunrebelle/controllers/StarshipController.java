package ldnr.groupe3.adopteunrebelle.controllers;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.Starship;
import ldnr.groupe3.adopteunrebelle.services.StarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Maintenant je peux connecter au controlleur avec http://localhost:8080
@RequestMapping("/starship")// je veux acceder à ce controlleur depuis http://localhost:8080/starship
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StarshipController {
    private final StarshipService starshipService;

    @PostMapping("/") // POST sur http://localhost/starship/
    public ResponseEntity<Integer> createStarship(@RequestBody Starship starship) {
        return ResponseEntity.ok(starshipService.save(starship));
    }

    @GetMapping("/") // GET sur http://localhost/starship/
    public ResponseEntity<List<Starship>> getAllStarship() {
        return ResponseEntity.ok(starshipService.findAll());
    }

    @GetMapping("/{starship-id}") // GET sur http://localhost/starship/ID
    public ResponseEntity<Starship> GetStarshipById(@PathVariable("starship-id") Integer starshipId) {
        return ResponseEntity.ok(starshipService.findById(starshipId));
    }

    @DeleteMapping("/{starship-id}") // DELETE sur http://localhost/starship/ID
    public ResponseEntity<Void> DeleteStarship(@PathVariable("starship-id") Integer starshipId) {
        starshipService.delete(starshipId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/affect/{starship-id}")
    public ResponseEntity<Void> affectPilot(@PathVariable("starship-id") Integer starshipId, @RequestBody Pilot pilot) {
        starshipService.affectPilot(pilot, starshipId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/desaffect/{starship-id}")
    public ResponseEntity<Void> desaffectPilot(@PathVariable("starship-id") Integer starshipId) {
        starshipService.desaffectPilot(starshipId);
        return ResponseEntity.accepted().build();
    }


}

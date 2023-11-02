package ldnr.groupe3.adopteunrebelle.controllers;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.Starship;
import ldnr.groupe3.adopteunrebelle.services.PilotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pilot")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:'4200")

public class PilotController {
    private final PilotService pilotService;

    @PostMapping("/")
    public ResponseEntity<Integer> createPilot(@RequestBody Pilot pilot){
        return ResponseEntity.ok(pilotService.save(pilot));
    }

    @GetMapping("/")
    public ResponseEntity<List<Pilot>> getAllPilot(){return ResponseEntity.ok(pilotService.findAll());}

    @GetMapping("/{pilot-id}")
    public ResponseEntity<Pilot> GetPilot(@PathVariable("pilot-id") Integer pilotId){
        return ResponseEntity.ok(pilotService.findById(pilotId));
    }

    @DeleteMapping("/{pilot-id}")
    public ResponseEntity<Void> DeletePilot(@PathVariable("pilot-id") Integer pilotId){
        pilotService.delete(pilotId);
        return ResponseEntity.accepted().build();
    }
}

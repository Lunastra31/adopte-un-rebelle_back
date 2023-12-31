package ldnr.groupe3.adopteunrebelle.controllers;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.Starship;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotStatus;
import ldnr.groupe3.adopteunrebelle.services.PilotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pilot")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping("/{pilot-id}")
    public ResponseEntity<Pilot> changePilotStatus(@PathVariable("pilot-id") Integer id, @RequestBody PilotStatus pilotStatus) {
        return ResponseEntity.ok(pilotService.changePilotStatus(id, pilotStatus));
    }

//    @PutMapping("/affect/{pilot-id}")
//    public ResponseEntity<Void> affectStarship(@PathVariable("pilot-id") Integer pilotId, @RequestBody Starship starship) {
//        pilotService.affectStarship(starship, pilotId);
//        return ResponseEntity.accepted().build();
//    }
//
//    @PutMapping("/desaffect/{pilot-id}")
//    public ResponseEntity<Void> desaffectStarship(@PathVariable("pilot-id") Integer pilotId) {
//        pilotService.desaffectStarship(pilotId);
//        return ResponseEntity.accepted().build();
//    }
}

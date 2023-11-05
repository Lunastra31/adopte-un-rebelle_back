package ldnr.groupe3.adopteunrebelle.controllers;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:'4200")

public class MissionController {
    private final MissionService missionService;

    @PostMapping("/")
    public ResponseEntity<Integer> createMission(@RequestBody Mission mission){
        return ResponseEntity.ok(missionService.save(mission));
    }

    @GetMapping("/")
    public ResponseEntity<List<Mission>> getAllMission(){return ResponseEntity.ok(missionService.findAll());}

    @GetMapping("/{mission-id}")
    public ResponseEntity<Mission> GetMission(@PathVariable("mission-id") Integer missionId){
        return ResponseEntity.ok(missionService.findById(missionId));
    }

    @DeleteMapping("/{mission-id}")
    public ResponseEntity<Void> DeleteMission(@PathVariable("mission-id") Integer missionId){
        missionService.delete(missionId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping(path = "/affect/{mission-id}")
    public ResponseEntity<Void> editMission (@PathVariable("missionId") Integer id, @RequestBody List<Pilot> pilots) throws EntityNotFoundException{
    missionService.affectPilot(id, pilots);
    return ResponseEntity.accepted().build();
    }
    //le fait de typer un void avec un return permet de ne pas renvoyer l'objet lorsqu'on run la fonction
    //Cela évite d'afficher l'objet et ses paramètres (sécurité)
}

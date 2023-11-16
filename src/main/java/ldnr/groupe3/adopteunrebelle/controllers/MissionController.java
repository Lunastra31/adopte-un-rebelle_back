package ldnr.groupe3.adopteunrebelle.controllers;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.enums.MissionStatus;
import ldnr.groupe3.adopteunrebelle.services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class MissionController {
    private final MissionService missionService;

    @PostMapping("/")
    public ResponseEntity<Integer> createMission(@RequestBody Mission mission){
        return ResponseEntity.ok(missionService.save(mission));
    }

    @GetMapping("/")
    public ResponseEntity<List<Mission>> getAllMission(){return ResponseEntity.ok(missionService.findAll());}

    @GetMapping("/{mission-id}")
    public ResponseEntity<Mission> getMission(@PathVariable("mission-id") Integer missionId){
        return ResponseEntity.ok(missionService.findById(missionId));
    }

//    @GetMapping("/{mission-id}")
//    public ResponseEntity<List<Mission>> getMissionsByPilotsId(@PathVariable("pilot-id") Integer pilotId){
//        return ResponseEntity.ok(missionService.findMissionsByPilotId(pilotId));
//    }

    @DeleteMapping("/{mission-id}")
    public ResponseEntity<Void> deleteMission(@PathVariable("mission-id") Integer missionId){
        missionService.delete(missionId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/{mission-id}")
    public ResponseEntity<Mission> endMission(@PathVariable("mission-id") Integer missionId, @RequestBody Mission mission) {
        return ResponseEntity.ok(missionService.endMission(missionId, mission));
    }

    @PutMapping("/affect/{mission-id}")
    public ResponseEntity<Mission> affectPilot (@PathVariable("mission-id") Integer id, @RequestBody List<Pilot> pilots) {
        return ResponseEntity.ok(missionService.affectPilot(id, pilots));
    }

    //On utilise l'objet ResponseEntity pour de ne pas renvoyer l'objet lorsqu'on run la fonction
    //Cela évite d'afficher l'objet et ses paramètres (sécurité)
    // On met Void dans le diamant car il n'y aucun type d'objet
}

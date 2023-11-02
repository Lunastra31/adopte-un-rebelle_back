package ldnr.groupe3.adopteunrebelle.controllers;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

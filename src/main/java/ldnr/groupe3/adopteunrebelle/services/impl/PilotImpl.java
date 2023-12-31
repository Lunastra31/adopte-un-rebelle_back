package ldnr.groupe3.adopteunrebelle.services.impl;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotRank;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotStatus;
import ldnr.groupe3.adopteunrebelle.repositories.PilotRepository;
import ldnr.groupe3.adopteunrebelle.repositories.StarshipRepository;
import ldnr.groupe3.adopteunrebelle.services.PilotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PilotImpl implements PilotService {

    private final PilotRepository pilotRepository;
    private final StarshipRepository starshipRepository;
    private boolean Trainee;

    @Override
    public Integer save(Pilot entity) {
        Pilot pilot = entity;
        return pilotRepository.save(pilot).getId();
    }

    @Override
    public List<Pilot> findAll() {
        return pilotRepository.findAll();
    }

    @Override
    public Pilot findById(Integer id) {
        return pilotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No pilot has been found with the given ID :" + id));
    }

    @Override
    public void delete(Integer id) {
        pilotRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Pilot changePilotStatus(Integer id, PilotStatus pilotStatus) {
        Pilot currentPilot = pilotRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No pilot has been found with the provided id :" + id));
        currentPilot.setPilotStatus(pilotStatus);
        return pilotRepository.save(currentPilot);
    }

    @Override
    public void setTrainee(boolean Trainee) {
        this.Trainee = Trainee;
    }


//    @Override
//    public void affectStarship(Starship starship, Integer pilotId) {
//        Pilot pilot = pilotRepository.findById(pilotId)
//                .orElseThrow(() -> new EntityNotFoundException("No pilot has been found with the provided id: " + pilotId));
//
//        if (pilot != null) {
//            // Assign the starship to the pilot
//            pilot.setStarship(starship);
//            starship.setPilot(pilot);
//
//            // Update the database
//            pilotRepository.save(pilot);
//            starshipRepository.save(starship);
//        }
//    }
//    @Override
//    public void desaffectStarship(Integer pilotId){
//        Pilot pilot = pilotRepository.findById(pilotId)
//                .orElseThrow(() -> new EntityNotFoundException("No pilot has been found with the provided id: " + pilotId));
//
//        Starship starship = pilot.getStarship();
//        if (starship != null) {
//            pilot.setStarship(null);
//            starship.setPilot(null);
//            starshipRepository.save(starship);
//        }
//   }
}

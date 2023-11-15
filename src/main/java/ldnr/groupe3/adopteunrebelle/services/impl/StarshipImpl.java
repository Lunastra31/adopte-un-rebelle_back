package ldnr.groupe3.adopteunrebelle.services.impl;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.Starship;
import ldnr.groupe3.adopteunrebelle.models.enums.StarshipStatus;
import ldnr.groupe3.adopteunrebelle.repositories.PilotRepository;
import ldnr.groupe3.adopteunrebelle.repositories.StarshipRepository;
import ldnr.groupe3.adopteunrebelle.services.StarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StarshipImpl implements StarshipService {

    private final StarshipRepository starshipRepository;
    private final PilotRepository pilotRepository;

    @Override
    public Integer save(Starship entity) {
        Starship starship = entity;
        return starshipRepository.save(starship).getId();
    }

    @Override
    public List<Starship> findAll() {
        return starshipRepository.findAll();
    }

    @Override
    public Starship findById(Integer id) {
        return starshipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No starship was found with the provided ID" + id));
    }

    @Override
    public void delete(Integer id) {
        starshipRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Starship changeStarshipStatus(Integer id, StarshipStatus starshipStatus) {
        Starship currentStarship = starshipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No starship has been found with the provided id :" + id));
        currentStarship.setStarshipStatus(starshipStatus);
        return starshipRepository.save(currentStarship);
    }

    @Override
    public Starship affectPilot(Integer starshipId, Pilot pilot) {
        Starship starship = starshipRepository.findById(starshipId)
                .orElseThrow(() -> new EntityNotFoundException("No starship has been found with the provided id: " + starshipId));

        starship.setPilot(pilot);
        pilot.setHasStarship(true);
        pilotRepository.save(pilot);
        starshipRepository.save(starship);
        return starship;
    }

    @Override
    public Starship desaffectPilot(Integer starshipId) {
        Starship starship = starshipRepository.findById(starshipId)
                .orElseThrow(() -> new EntityNotFoundException("No starship has been found with the provided id: " + starshipId));

        if (starship.getPilot() != null) {
            Pilot pilot = starship.getPilot();
            starship.setPilot(null);
            pilot.setHasStarship(false);
            pilotRepository.save(pilot);
        }

        starshipRepository.save(starship);
        return starship;
    }
}


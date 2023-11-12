package ldnr.groupe3.adopteunrebelle.services.impl;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.Starship;
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
    public void changeStarshipStatus(Integer id, Starship starship) {
        Starship currentStarship = starshipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No starship has been found with the provided id :" + id));
        currentStarship.setStarshipStatus(starship.getStarshipStatus());
    }

    @Override
    public void affectPilot(Pilot pilot, Integer starshipId) {
        Starship starship = starshipRepository.findById(starshipId)
                .orElseThrow(() -> new EntityNotFoundException("No starship has been found with the provided id: " + starshipId));

        if (starship != null) {
            // Assign the starship to the pilot
            pilot.setStarship(starship);
            starship.setPilot(pilot);

            // Update the database
            pilotRepository.save(pilot);
            starshipRepository.save(starship);
    }
    }
    @Override
    public void desaffectPilot(Integer starshipId) {
        Starship starship = starshipRepository.findById(starshipId)
                .orElseThrow(() -> new EntityNotFoundException("No pilot has been found with the provided id: " + starshipId));

        Pilot pilot = starship.getPilot();
        if (starship != null) {
            pilot.setStarship(null);
            starship.setPilot(null);
            starshipRepository.save(starship);
        }
    }
}

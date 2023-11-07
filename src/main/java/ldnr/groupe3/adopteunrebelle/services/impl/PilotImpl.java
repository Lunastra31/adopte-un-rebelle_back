package ldnr.groupe3.adopteunrebelle.services.impl;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.Starship;
import ldnr.groupe3.adopteunrebelle.repositories.PilotRepository;
import ldnr.groupe3.adopteunrebelle.repositories.StarshipRepository;
import ldnr.groupe3.adopteunrebelle.services.PilotService;
import ldnr.groupe3.adopteunrebelle.services.StarshipService;
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
    public void affectStarship(Starship starship, Integer pilotId) {
        Pilot pilot = pilotRepository.findById(pilotId)
                .orElseThrow(() -> new EntityNotFoundException("No pilot has been found with the provided id: " + pilotId));

        Starship currentStarship = pilot.getStarship();
        if (currentStarship != null) {
            currentStarship.setPilot(pilot);
        }

        pilot.setStarship(currentStarship);
        starship.setPilot(pilot);

        // Mettez à jour le pilote et le vaisseau dans la même transaction
        pilotRepository.save(pilot);
        starshipRepository.save(starship);
    }
    @Override
    @Transactional
    public void desaffectStarship(Integer pilotId) {
        Pilot pilot = pilotRepository.findById(pilotId)
                .orElseThrow(() -> new EntityNotFoundException("No pilot has been found with the provided id: " + pilotId));

        Starship starship = pilot.getStarship();
        if (starship != null) {
            pilot.setStarship(null);
            starship.setPilot(null);
            starshipRepository.save(starship);
        }
    }

}

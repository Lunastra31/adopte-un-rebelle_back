package ldnr.groupe3.adopteunrebelle.services.impl;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.repositories.PilotRepository;
import ldnr.groupe3.adopteunrebelle.repositories.StarshipRepository;
import ldnr.groupe3.adopteunrebelle.services.PilotService;
import ldnr.groupe3.adopteunrebelle.services.StarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PilotImpl implements PilotService {

    private final PilotRepository pilotRepository;

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
    public void createPilot() {

    }

    @Override
    public String getAllApprenticePilot() {
        return null;
    }

    @Override
    public String getAllPilot() {
        return null;
    }

    @Override
    public String getAllAvailablePilot() {
        return null;
    }

    @Override
    public String getAllWoundedPilot() {
        return null;
    }

    @Override
    public String getAllDeadPilot() {
        return null;
    }
}

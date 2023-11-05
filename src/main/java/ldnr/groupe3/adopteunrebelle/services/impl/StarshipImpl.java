package ldnr.groupe3.adopteunrebelle.services.impl;

import ldnr.groupe3.adopteunrebelle.models.Starship;
import ldnr.groupe3.adopteunrebelle.models.enums.StarshipStatus;
import ldnr.groupe3.adopteunrebelle.repositories.StarshipRepository;
import ldnr.groupe3.adopteunrebelle.services.StarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StarshipImpl implements StarshipService {

    private final StarshipRepository starshipRepository;
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
    public void addStarship() {
        save(new Starship());
    }

    @Override
    @Transactional
    public void changeStarshipStatus(Integer id, Starship starship) {
        Starship myStarship = starshipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No starship has been found with the provided id :" + id));
        starship.setStarshipStatus(starship.getStarshipStatus());
    }
}

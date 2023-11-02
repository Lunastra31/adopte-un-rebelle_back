package ldnr.groupe3.adopteunrebelle.services.impl;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.repositories.MissionRepository;
import ldnr.groupe3.adopteunrebelle.services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionImpl implements MissionService {
    private final MissionRepository missionRepository;

    @Override
    public Integer save(Mission entity) {
        Mission mission = entity;
        return missionRepository.save(mission).getId();
    }

    @Override
    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    @Override
    public Mission findById(Integer id) {
        return missionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No mission has been found with the given ID :" + id));
    }

    @Override
    public void delete(Integer id) {
        missionRepository.deleteById(id);
    }
}

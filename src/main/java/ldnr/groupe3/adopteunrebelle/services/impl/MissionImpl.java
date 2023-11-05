package ldnr.groupe3.adopteunrebelle.services.impl;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.enums.MissionStatus;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotRank;
import ldnr.groupe3.adopteunrebelle.repositories.MissionRepository;
import ldnr.groupe3.adopteunrebelle.services.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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

    @Override
    @Transactional
    public Integer endMission(Integer missionId, Integer flighHours, MissionStatus missionStatus) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new EntityNotFoundException("No mission has been found with the given ID :" + missionId));
        mission.setFlightHours(flighHours);
        mission.setMissionStatus(missionStatus);
        List<Pilot> pilotsInThisMission = mission.getPilots();
        for (Pilot pilot : pilotsInThisMission) {
            pilot.setFlightHours(pilot.getFlightHours() + flighHours);
            this.rankChecker(pilot);
        }
        return missionRepository.save(mission).getId();
    }

    @Override
    public void rankChecker(Pilot pilot) {
        if (pilot.getFlightHours() >= 500) {
            pilot.setPilotRank(PilotRank.OFFICIER_DE_VOL);
        } else if (pilot.getFlightHours() >= 1500) {
            pilot.setPilotRank(PilotRank.COMMANDANT);
        } else if (pilot.getFlightHours() >= 4000) {
            pilot.setPilotRank(PilotRank.CAPITAINE);
        } else if (pilot.getFlightHours() >= 5000) {
            pilot.setPilotRank(PilotRank.CAPITAINE);
        }
    }
}

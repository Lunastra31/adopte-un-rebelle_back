package ldnr.groupe3.adopteunrebelle.services.impl;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.enums.MissionStatus;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotRank;
import ldnr.groupe3.adopteunrebelle.repositories.MissionRepository;
import ldnr.groupe3.adopteunrebelle.repositories.PilotRepository;
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
    private final PilotRepository pilotRepository;

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
    public Integer endMission(Integer missionId, Integer flightHours, MissionStatus missionStatus) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new EntityNotFoundException("No mission has been found with the given ID :" + missionId));
        mission.setFlightHours(flightHours);
        mission.setMissionStatus(missionStatus);
        List<Pilot> pilotsInThisMission = mission.getPilots();
        pilotsInThisMission.forEach(pilot -> {
            pilot.setFlightHours(pilot.getFlightHours() + flightHours);
            pilot.setEndedMissionCount(pilot.getEndedMissionCount() + 1);
            pilot.setMission(null);
            rankChecker(pilot);
        });

        return missionRepository.save(mission).getId();
    }

    @Override
    public void rankChecker(Pilot pilot) {
        int flightHours = pilot.getFlightHours();
        if (flightHours >= 500) {
            pilot.setPilotRank(PilotRank.OFFICIER_DE_VOL);
        } else if (flightHours >= 1500) {
            pilot.setPilotRank(PilotRank.COMMANDANT);
        } else if (flightHours >= 4000) {
            pilot.setPilotRank(PilotRank.CAPITAINE);
        } else if (flightHours >= 5000) {
            pilot.setPilotRank(PilotRank.LIEUTENANT); // Ajusté pour éviter la redondance
        }
    }
    @Transactional
    public void affectPilot(Integer missionId, List<Pilot> pilots){
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found with id: " + missionId));

        for (Pilot pilot : pilots) {
            pilot.setMission(mission);
            pilotRepository.save(pilot);
        }

        mission.setPilots(pilots);
        missionRepository.save(mission);
    }

    public List<Pilot> getPilotsByMissionId(int missionId) {
        return missionRepository.findPilotsByPilotsMissionId(missionId);
    }
}

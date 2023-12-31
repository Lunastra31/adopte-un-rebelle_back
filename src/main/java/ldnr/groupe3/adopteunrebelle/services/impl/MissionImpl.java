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
    public Mission endMission(Integer missionId, Mission missionToClosed) {
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new EntityNotFoundException("No mission has been found with the given ID :" + missionId));
        mission.setFlightHours(missionToClosed.getFlightHours());
        mission.setMissionStatus(missionToClosed.getMissionStatus());
        List<Pilot> pilotsInThisMission = mission.getPilots();
        pilotsInThisMission.forEach(pilot -> {
            pilot.setFlightHours(pilot.getFlightHours() + missionToClosed.getFlightHours());
            pilot.setEndedMissionCount(pilot.getEndedMissionCount() + 1);
            pilot.setPilotRank(rankChecker(pilot.getFlightHours(), pilot.getEndedMissionCount(), pilot));
            pilot.setTrainee(traineeChecker(pilot.getFlightHours()));
        });

        return missionRepository.save(mission);
    }

    @Override
    public PilotRank rankChecker(Integer flightHours, Integer endedMissionCount, Pilot pilot) {
        if (flightHours >= 1500 && flightHours <= 4000) {
            return PilotRank.COMMANDANT;
        } else if (flightHours >= 4000 && endedMissionCount < 3) {
            return PilotRank.CAPITAINE;
        } else if (flightHours >= 4000 && endedMissionCount >= 3) {
            return PilotRank.LIEUTENANT;
        } else {
            return PilotRank.OFFICIER_DE_VOL;
        }
    }

    @Override
    public Boolean traineeChecker(Integer flightHours) {
        if (flightHours <= 500) {
            return true;
        }
        return false;
    }



    @Transactional
    public Mission affectPilot(Integer missionId, List<Pilot> pilots){
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new EntityNotFoundException("Mission not found with id: " + missionId));

        for (Pilot pilot : pilots) {
            pilot.setMission(mission);
            pilotRepository.save(pilot);
        }

        mission.setPilots(pilots);
        return missionRepository.save(mission);
    }

//    @Override
//    public List<Mission> findMissionsByPilotId(Integer pilotId) {
//        return missionRepository.findMissionsByPilotId(pilotId);
//    }

    public List<Pilot> getPilotsByMissionId(Integer missionId) {
        return missionRepository.findPilotsByPilotsMissionId(missionId);
    }


}

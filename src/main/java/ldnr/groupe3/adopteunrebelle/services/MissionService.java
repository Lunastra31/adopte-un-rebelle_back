package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.enums.MissionStatus;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotRank;

import javax.transaction.Transactional;
import java.util.List;

public interface MissionService extends AbstractService<Mission> {
    @Transactional
    public Mission endMission(Integer missionId, Mission mission);

    public PilotRank rankChecker(Integer flightHours, Integer endedMissionCount);

    @Transactional
    public Mission affectPilot(Integer id, List<Pilot> pilots);

//    List<Mission> findMissionsByPilotId(Integer pilotId);


}

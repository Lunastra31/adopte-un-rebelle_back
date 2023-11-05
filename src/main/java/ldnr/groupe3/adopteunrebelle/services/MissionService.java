package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.enums.MissionStatus;

import javax.transaction.Transactional;
import java.util.List;

public interface MissionService extends AbstractService<Mission>{
   @Transactional
    public Integer endMission(Integer missionId, Integer flightHours, MissionStatus missionStatus);

    public void rankChecker(Pilot pilot);

    public void affectPilot(Integer id, List<Pilot> pilots);

}

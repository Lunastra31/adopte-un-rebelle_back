package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.models.Pilot;

import java.util.List;

public interface MissionService extends AbstractService<Mission>{

    public void endMission();

    public void affectPilot(Integer id, List<Pilot> pilots);

}

package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Mission;

public interface MissionService extends AbstractService<Mission>{

    public void createMission();
    public void endMission();
    public String getAllEndMission();

}

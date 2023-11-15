package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotStatus;

public interface PilotService extends AbstractService<Pilot> {

    public Pilot changePilotStatus(Integer id, PilotStatus pilotStatus);
}

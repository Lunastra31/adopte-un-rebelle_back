package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Pilot;

public interface PilotService extends AbstractService<Pilot> {

    public void createPilot();
    public String getAllApprenticePilot();
    public String getAllPilot();
    public String getAllAvailablePilot();
    public String getAllWoundedPilot();
    public String getAllDeadPilot();

}

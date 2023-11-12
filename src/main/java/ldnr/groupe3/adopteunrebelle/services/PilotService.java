package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.Starship;

public interface PilotService extends AbstractService<Pilot> {
    public void affectStarship(Starship starship, Integer id);
    public void desaffectStarship(Integer id);

}

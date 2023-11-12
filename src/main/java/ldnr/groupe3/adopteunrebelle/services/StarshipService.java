package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.Starship;

public interface StarshipService extends AbstractService<Starship> {
    public void changeStarshipStatus(Integer id, Starship starship);

    public Starship affectPilot(Integer id, Pilot pilot);
    public Starship desaffectPilot(Integer id);
}

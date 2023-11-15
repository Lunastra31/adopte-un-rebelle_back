package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Pilot;
import ldnr.groupe3.adopteunrebelle.models.Starship;
import ldnr.groupe3.adopteunrebelle.models.enums.StarshipStatus;

public interface StarshipService extends AbstractService<Starship> {
    public Starship changeStarshipStatus(Integer id, StarshipStatus starshipStatus);

    public Starship affectPilot(Integer id, Pilot pilot);
    public Starship desaffectPilot(Integer id);
}

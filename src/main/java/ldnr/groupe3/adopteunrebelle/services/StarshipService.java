package ldnr.groupe3.adopteunrebelle.services;

import ldnr.groupe3.adopteunrebelle.models.Starship;

public interface StarshipService extends AbstractService<Starship> {
    public void changeStarshipStatus(Integer id, Starship starship);
}

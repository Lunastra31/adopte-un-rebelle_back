package ldnr.groupe3.adopteunrebelle.repositories;

import ldnr.groupe3.adopteunrebelle.models.Mission;
import ldnr.groupe3.adopteunrebelle.models.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Integer> {
    List<Pilot> findPilotsByPilotsMissionId(int missionId);
}

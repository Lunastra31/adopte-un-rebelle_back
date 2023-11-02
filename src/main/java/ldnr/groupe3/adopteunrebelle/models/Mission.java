package ldnr.groupe3.adopteunrebelle.models;

import ldnr.groupe3.adopteunrebelle.models.enums.MissionStatus;
import ldnr.groupe3.adopteunrebelle.models.enums.MissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mission")
public class Mission extends AbstractEntity{

    private String name;

    @Enumerated(EnumType.STRING)
    private MissionType missionType;

    @ManyToMany(mappedBy = "missions")
    private Set<Pilot> pilots = new HashSet<Pilot>();

    private Integer flightHours;

    @Enumerated(EnumType.STRING)
    private MissionStatus missionStatus;

}

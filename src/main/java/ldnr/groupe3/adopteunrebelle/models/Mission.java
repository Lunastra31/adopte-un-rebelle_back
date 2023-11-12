package ldnr.groupe3.adopteunrebelle.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ldnr.groupe3.adopteunrebelle.models.enums.MissionStatus;
import ldnr.groupe3.adopteunrebelle.models.enums.MissionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int selectPilotCount;

    @Enumerated(EnumType.STRING)
    private MissionType missionType;

    @JsonManagedReference
    @OneToMany(mappedBy = "mission")
    private List<Pilot> pilots;

    private Integer flightHours;

    @Enumerated(EnumType.STRING)
    private MissionStatus missionStatus;

}

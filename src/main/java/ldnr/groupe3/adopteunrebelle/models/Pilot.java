package ldnr.groupe3.adopteunrebelle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotBreed;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotRank;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pilot")
public class Pilot extends AbstractEntity{
    private String name;

    private String surname;

    private boolean isTrainee;

    @Enumerated(EnumType.STRING)
    private PilotBreed pilotBreed;

    private LocalDate insDate;

    private Integer age;

    private Integer flightHours;

    private int EndedMissionCount;

    @Enumerated(EnumType.STRING)
    private PilotStatus pilotStatus;

    @Enumerated(EnumType.STRING)
    private PilotRank pilotRank;

    @OneToOne
    private Starship starship;

    @ManyToMany(mappedBy = "pilots")
    private List<Mission> missions;
}

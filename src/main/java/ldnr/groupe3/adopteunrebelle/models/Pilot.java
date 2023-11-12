package ldnr.groupe3.adopteunrebelle.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "pilot")
public class Pilot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private boolean isTrainee;

    @Enumerated(EnumType.STRING)
    private PilotBreed pilotBreed;

//    private LocalDate insDate;

    private Integer age;

    private Integer flightHours;

    private Integer endedMissionCount;

    @Enumerated(EnumType.STRING)
    private PilotStatus pilotStatus;

    @Enumerated(EnumType.STRING)
    private PilotRank pilotRank;

    private boolean hasStarship;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;
}

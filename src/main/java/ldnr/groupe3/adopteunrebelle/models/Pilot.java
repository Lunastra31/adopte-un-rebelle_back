package ldnr.groupe3.adopteunrebelle.models;

import ldnr.groupe3.adopteunrebelle.models.enums.PilotBreed;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotRank;
import ldnr.groupe3.adopteunrebelle.models.enums.PilotStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pilot")
public class Pilot extends AbstractEntity{
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private PilotBreed pilotBreed;

    private LocalDate insDate;
    private int age;

    @Enumerated(EnumType.STRING)
    private PilotStatus pilotStatus;

    @Enumerated(EnumType.STRING)
    private PilotRank pilotRank;

    @OneToOne
    private Starship starship;


}

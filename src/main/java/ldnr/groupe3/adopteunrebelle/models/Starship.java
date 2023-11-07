package ldnr.groupe3.adopteunrebelle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ldnr.groupe3.adopteunrebelle.models.enums.StarshipStatus;
import ldnr.groupe3.adopteunrebelle.models.enums.StarshipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "starship")
public class Starship extends AbstractEntity{

    private String name;

    @Enumerated(EnumType.STRING)
    private StarshipType starshipType;

    @Enumerated(EnumType.STRING)
    private StarshipStatus starshipStatus;

    @OneToOne
    @JoinColumn(name = "id_starship")
    private Pilot pilot;
}

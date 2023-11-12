package ldnr.groupe3.adopteunrebelle.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ldnr.groupe3.adopteunrebelle.models.enums.StarshipStatus;
import ldnr.groupe3.adopteunrebelle.models.enums.StarshipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "starship")
public class Starship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private StarshipType starshipType;

    @Enumerated(EnumType.STRING)
    private StarshipStatus starshipStatus;

    @OneToOne
    @JoinColumn(name = "pilot_id")
    @JsonBackReference
    private Pilot pilot;
}

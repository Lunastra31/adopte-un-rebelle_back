package ldnr.groupe3.adopteunrebelle.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data // LOMBOK création des getters et setters
@SuperBuilder // Lombok création du design patterns BUILDER avec un super constructeur
@NoArgsConstructor // LOMBOK création d'un constructeur sans argument
@MappedSuperclass // LOMBOK déclaration d'une classe super
public class AbstractEntity {

    @Id // SPRING déclaration JPA ID
    @GeneratedValue // SPRING déclaration incrémentation de l'ID par la BDD
    private  Integer id;

}

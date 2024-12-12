package tn.esprit.examenrevision.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Entity
@Table(name = "Bibliotheque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)




public class Bibliotheque {

    @Id
    @Column(name = "identifiant")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idBiblio;

    String nom;
    String adresse;
    long telephone;

    @OneToMany(cascade = CascadeType.ALL)
    List<Livre> livres;

}

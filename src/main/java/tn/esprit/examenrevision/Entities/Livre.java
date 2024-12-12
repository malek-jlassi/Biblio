package tn.esprit.examenrevision.Entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "Livre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)


public class Livre {

    @Id
    @Column(name = "identifiant")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idLivre;

    String nom;
    LocalDate dateEmission;
    boolean reserve;
    LocalDate dateReservation;

    @Enumerated(EnumType.STRING)
    TypeLivre typeLivre;

    @OneToOne(mappedBy = "livre")
    Utilisateur lecteur;

    @ManyToOne
    Utilisateur auteur;
}

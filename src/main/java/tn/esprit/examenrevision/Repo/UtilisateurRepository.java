package tn.esprit.examenrevision.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examenrevision.Entities.Utilisateur;

import java.time.LocalDate;
import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

   Utilisateur findByNomAndPrenom(String nom, String Prenom);
   List<Utilisateur> findByDateFinAbonnementBefore(LocalDate dateFinAbonnement);

}

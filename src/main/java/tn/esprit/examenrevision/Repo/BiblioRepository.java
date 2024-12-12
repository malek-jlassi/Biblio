package tn.esprit.examenrevision.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examenrevision.Entities.Bibliotheque;

public interface BiblioRepository extends JpaRepository<Bibliotheque, Long> {

    Bibliotheque findByNom(String nom);
}

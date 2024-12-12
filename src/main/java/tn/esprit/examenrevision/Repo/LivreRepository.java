package tn.esprit.examenrevision.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.examenrevision.Entities.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}

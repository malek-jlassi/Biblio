package tn.esprit.examenrevision.Service;


import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.examenrevision.Entities.Bibliotheque;
import tn.esprit.examenrevision.Entities.Livre;
import tn.esprit.examenrevision.Entities.Utilisateur;
import tn.esprit.examenrevision.Repo.BiblioRepository;

import lombok.AllArgsConstructor;
import tn.esprit.examenrevision.Repo.LivreRepository;
import tn.esprit.examenrevision.Repo.UtilisateurRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@AllArgsConstructor
@org.springframework.stereotype.Service
public class Service implements IService{



    BiblioRepository biblioRepository;
      UtilisateurRepository utilisateurRepository;
    LivreRepository livreRepository;
    @Override
    public Bibliotheque ajouterBibliotheque(Bibliotheque bibliotheque) {
        return biblioRepository.save(bibliotheque);
    }

    @Override
    public List<Utilisateur> ajouterLecteurs(List<Utilisateur> lecteurs) {
        return utilisateurRepository.saveAll(lecteurs);
    }

    @Override
    public Bibliotheque ajouterLivreEtAuteurEtAffecterABiblio(Livre l, String nomBibliotheque) {

       // search by name for existing bib
        Bibliotheque bib = biblioRepository.findByNom(nomBibliotheque);

        if (bib == null) {
            throw new RuntimeException("La bibliothèque avec le nom " + nomBibliotheque + " n'existe pas.");
        }



        // If necessary, associate an Auteur with the Livre
        Utilisateur auteur = utilisateurRepository.findById(l.getAuteur().getIdUser())
                .orElseThrow(() -> new EntityNotFoundException("Auteur not found"));
        l.setAuteur(auteur);

       // add new livre
        Livre livre = livreRepository.save(l);
       bib.getLivres().add(livre);  // associate bib with livre


        System.out.println("Livre: " + livre);
        System.out.println("Nom de la bibliothèque: " + nomBibliotheque);

        return biblioRepository.save(bib);

    }

    @Override
    public String affecterLivreALecteur(long idLivre, long idLecteur) {
        String msg = "";
        Livre liv = livreRepository.findById(idLivre).orElseThrow(() -> new EntityNotFoundException("Livre not found"));
        Utilisateur lecteur = utilisateurRepository.findById(idLecteur).orElseThrow(() -> new EntityNotFoundException("Lecteur not found"));

        lecteur.getLivres().add(liv);
        System.out.println("Livre: " + liv);
        if(liv.isReserve()) {
          log.info("le livre:" + liv.getNom() + " est réserve");

            msg = "le livre:" + liv.getNom() + " est réserve";

        }
        else {
           // lecteur.setLivre(liv);
          //  utilisateurRepository.save(lecteur);
           log.info("le livre :" + liv.getNom() + " est affecter au lecteur:" + lecteur.getNom());

           msg = "le livre :" + liv.getNom() + " est affecter au lecteur:" + lecteur.getNom();

        }

        return msg;
    }


    @Override
    public void modifierDateReservation(LocalDate date, long idLecteur) {

        Utilisateur user = utilisateurRepository.findById(idLecteur).orElseThrow(() -> new EntityNotFoundException("Lecteur not found"));
        Livre liv = user.getLivre();
        liv.setDateReservation(date);
        livreRepository.save(liv);

    }

    @Override
    public String rendreLivre(String nom, String prenom) {
        int j = 5;
        String msg;

        // Récupérer le lecteur
        Utilisateur lecteur = utilisateurRepository.findByNomAndPrenom(nom, prenom);
        if (lecteur == null) {
            throw new EntityNotFoundException("Lecteur non trouvé avec le nom: " + nom + " et le prénom: " + prenom);
        }

        // Récupérer le livre associé
        Livre liv = lecteur.getLivre();
        if (liv == null) {
            throw new IllegalStateException("Aucun livre n'est associé à ce lecteur.");
        }

        // Vérifier si la date de réservation existe
        LocalDate dateReservation = liv.getDateReservation();
        if (dateReservation == null) {
            throw new IllegalStateException("La date de réservation est absente pour le livre: " + liv.getNom());
        }

        // Calculer la date limite
        LocalDate dateMaxReserve = dateReservation.plusDays(j);

        // Comparer avec la date actuelle
        if (liv.getDateEmission().isAfter(dateMaxReserve)) {
            liv.setDateReservation(null);
            liv.setReserve(false);
            msg = "Le lecteur " + nom + " " + prenom + " est bloqué. La désaffectation est effectuée avec succès.";
        } else {
            liv.setDateReservation(null);
            liv.setReserve(true);
            msg = "Le lecteur " + nom + " " + prenom + " a respecté la durée ! La désaffectation est effectuée avec succès.";
        }

        // Sauvegarder les modifications si nécessaire (par exemple, via un repository)
        livreRepository.save(liv);

        return msg;
    }

    @Override
    public void listelecteurfinabon() {


        LocalDate limite = LocalDate.now().minusDays(2);

           List<Utilisateur> lecteurs = utilisateurRepository.findByDateFinAbonnementBefore(limite);
        if (lecteurs.isEmpty()) {
            log.info("Aucun lecteur dont l'abonnement expire dans au moins 2 jours.");
        } else {
            log.info("Liste des lecteurs dont l'abonnement expire dans au moins 2 jours :");
            lecteurs.forEach(lecteur ->
                    log.info("Lecteur: " + lecteur.getNom() + ", Fin abonnement: " + lecteur.getDateFinAbonnement())
            );
        }
    }

}

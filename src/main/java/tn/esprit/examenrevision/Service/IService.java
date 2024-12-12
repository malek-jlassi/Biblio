package tn.esprit.examenrevision.Service;

import tn.esprit.examenrevision.Entities.Bibliotheque;
import tn.esprit.examenrevision.Entities.Livre;
import tn.esprit.examenrevision.Entities.Utilisateur;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IService {

    Bibliotheque ajouterBibliotheque(Bibliotheque bibliotheque);
    List<Utilisateur> ajouterLecteurs(List<Utilisateur> lecteurs) ;
    Bibliotheque ajouterLivreEtAuteurEtAffecterABiblio(Livre l, String nomBibliotheque);
    String affecterLivreALecteur(long idLivre, long idLecteur);
    void modifierDateReservation(LocalDate date, long idLecteur);
    String rendreLivre(String nom, String prenom);
    void listelecteurfinabon();
}

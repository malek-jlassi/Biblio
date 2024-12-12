package tn.esprit.examenrevision.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.examenrevision.Entities.Bibliotheque;
import tn.esprit.examenrevision.Entities.Livre;
import tn.esprit.examenrevision.Entities.Utilisateur;
import tn.esprit.examenrevision.Service.IService;

import java.time.LocalDate;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("AllRestController")
@Tag(name = "All APIs", description = "Manage all operations like CRUD")



public class TestController {

    IService service;

    @Operation(summary = "Add a new biblio", description = "Create a new biblio")
    @PostMapping("ajouterBibliotheque")
    public Bibliotheque ajouterBibliotheque(@RequestBody Bibliotheque biblio) {

        return service.ajouterBibliotheque(biblio);
    }

    @Operation(summary = "Add a new lecteur", description = "Create a new lecteur")
    @PostMapping("ajouterLecteurs")
    public List<Utilisateur> ajouterLecteurs(@RequestBody List<Utilisateur> lecteurs) {
       // System.out.println("Received payload: " + lecteurs);
        return service.ajouterLecteurs(lecteurs);
    }

    @Operation(summary = "Add a new livre au bib", description = "Create a new livre au bib")
    @PostMapping("ajouterLivreEtAuteurEtAffecterABiblio")
    public Bibliotheque ajouterLivreEtAuteurEtAffecterABiblio(@RequestBody Livre l, @RequestParam String nomBibliotheque){
        //System.out.println("Received payload: " + l );
      //  System.out.println("Received payload: "+ nomBibliotheque);

        return service.ajouterLivreEtAuteurEtAffecterABiblio(l, nomBibliotheque);
    }



    @Operation(summary = "Add a new livre au lecteur", description = "Create a new livre au lecteur")
    @PostMapping("affecterLivreALecteur")
    public String affecterLivreALecteur(@RequestParam long idLivre, @RequestParam long idLecteur){


        return service.affecterLivreALecteur(idLivre, idLecteur);
    }


//        @Operation(summary = "Add a new modifier date reservation", description = "Create a new modifier date reservation")
//        @PutMapping("modifierDateReservation")
//        public void modifierDateReservation(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam long idLecteur) {
//             service.modifierDateReservation(date, idLecteur);
//           // return service.modifierDateReservation(date, idLecteur);
//
//
//         }


    @Operation(summary = "rendre Livre", description = "Create a new livre au lecteur")
    @PostMapping("rendreLivre")
    public String rendreLivre(@RequestParam String nom, @RequestParam String prenom){

     return service.rendreLivre(nom, prenom);
  }



//    @Operation(summary = "get fin abon", description = "Create a new modifier date reservation")
//    @GetMapping("listelecteurfinabon")
//    public void listelecteurfinabon(){
//    service.listelecteurfinabon();
//        // return service.modifierDateReservation(date, idLecteur);
//
//
//    }
}

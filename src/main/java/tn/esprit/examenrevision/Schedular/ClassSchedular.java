package tn.esprit.examenrevision.Schedular;

import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.examenrevision.Service.IService;

public class ClassSchedular {

    IService service;

    @Scheduled(fixedRate = 60000)
    public void listeEventParCategorie() {
        service.listelecteurfinabon();
    }
}

package tn.esprit.examenrevision.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(infoAPI());
    }
    public Info infoAPI() {
        return new Info().title("SpringDoc-Test")
                .description("Test étude de cas")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("Equipe ASI II")
                .email("*************@esprit.tn")
                .url("https://www.linkedin.com/in/**********/");
        return contact;
    }

    @Bean
    public GroupedOpenApi AllRevPublicApi() {
        return GroupedOpenApi.builder()
                .group("All APIs")
                .pathsToMatch("/**")
                .pathsToExclude("**")
                .build();
    }

//  @Bean
//    public GroupedOpenApi BookPublicApi() {
//        return GroupedOpenApi.builder()
//                .group("Book APIs")
//                .pathsToMatch("/BookRestController/**")
//                .pathsToExclude("**")
//                .build();
//    }

//    @Bean
//    public GroupedOpenApi CategoryPublicApi() {
//        return GroupedOpenApi.builder()
//                .group("Category APIs")
//                .pathsToMatch("/CategoryRestController/**")
//                .pathsToExclude("**")
//                .build();
//    }

//    @Bean
//    public GroupedOpenApi InternautePublicApi() {
//        return GroupedOpenApi.builder()
//                .group("Internaute APIs")
//                .pathsToMatch("/InternauteRestController/**")
//                .pathsToExclude("**")
//                .build();
//    }

//    @Bean
//    public GroupedOpenApi ChambrePublicApi() {
//        return GroupedOpenApi.builder()
//                .group("Chambre APIs")
//                .pathsToMatch("/ChambreRestController/**")
//                .pathsToExclude("**")
//                .build();
//    }
//
//
//  @Bean
//    public GroupedOpenApi EtudiantPublicApi() {
//        return GroupedOpenApi.builder()
//                .group("Etudiant APIs")
//                .pathsToMatch("/EtudiantRestController/**")
//                .pathsToExclude("**")
//                .build();
//    }
//
//
//
//  @Bean
//    public GroupedOpenApi FoyerPublicApi() {
//        return GroupedOpenApi.builder()
//                .group("Foyer APIs")
//                .pathsToMatch("/FoyerRestController/**")
//                .pathsToExclude("**")
//                .build();
//    }
//
//
//  @Bean
//    public GroupedOpenApi ReservationPublicApi() {
//        return GroupedOpenApi.builder()
//                .group("Reservation APIs")
//                .pathsToMatch("/ReservationRestController/**")
//                .pathsToExclude("**")
//                .build();
//    }
//
//
//  @Bean
//    public GroupedOpenApi UniversitePublicApi() {
//        return GroupedOpenApi.builder()
//                .group("Universite APIs")
//                .pathsToMatch("/UniversiteRestController/**")
//                .pathsToExclude("**")
//                .build();
//    }
}


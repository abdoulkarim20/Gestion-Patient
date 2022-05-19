package cours.springboot.simplon.boostrapspringboot;

import cours.springboot.simplon.boostrapspringboot.entities.Patient;
import cours.springboot.simplon.boostrapspringboot.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BoostrapspringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoostrapspringbootApplication.class, args);
    }
    //Les codes ci-dessous nous permet de lancer notre application
//    @Bean
//    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
//        return args -> {
//            for (int i=0;i<=14;i++){
//                patientRepository.save(new Patient(null,"Arturo VIDAL",new Date(),2346,false));
//            }
//            patientRepository.save(new Patient(null,"Kouwakou KOUY",new Date(),2346,true));
//        };
//    }
}

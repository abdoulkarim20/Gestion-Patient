package cours.springboot.simplon.boostrapspringboot.repository;

import cours.springboot.simplon.boostrapspringboot.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    /*je declare une methode me permetant de faire la recherche
    * mais puisque ici j'ai une pagination c'est a dire la recherche se trouve dans une page
    * donc je vais declarer une methode de type page */
    Page<Patient> findByNomCompletContains(String kw, Pageable pageable);
}

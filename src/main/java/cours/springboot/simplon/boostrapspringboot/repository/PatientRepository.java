package cours.springboot.simplon.boostrapspringboot.repository;

import cours.springboot.simplon.boostrapspringboot.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}

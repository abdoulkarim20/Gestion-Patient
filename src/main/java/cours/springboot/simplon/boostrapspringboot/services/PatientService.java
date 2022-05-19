package cours.springboot.simplon.boostrapspringboot.services;

import cours.springboot.simplon.boostrapspringboot.entities.Patient;
import cours.springboot.simplon.boostrapspringboot.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {
//    @Autowired
    //il n'est pas recommender d'utiliser @Autowired mais plus un constructeur avek argument
    PatientRepository patientRepository;
//    public PatientService(PatientRepository patientRepository){
//        this.patientRepository=patientRepository;
//    }
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }
    public void addPatient(Patient patient){
        patientRepository.save(patient);
    }
    public void deletePatient(Long id){
        patientRepository.deleteById(id);
    }
}

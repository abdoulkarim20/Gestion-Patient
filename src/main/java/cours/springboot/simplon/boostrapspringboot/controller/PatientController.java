package cours.springboot.simplon.boostrapspringboot.controller;

import cours.springboot.simplon.boostrapspringboot.entities.Patient;
import cours.springboot.simplon.boostrapspringboot.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    //@Autowired
    PatientService patientService;

    @GetMapping("/")
    public String patients(Model model){
        List<Patient> patients=patientService.getAllPatient();
        model.addAttribute("patients",patients);
        return "index";
    }
    @GetMapping("/formulaire")
    public String formulaire(Model model){
        Patient patient=new Patient();
        model.addAttribute("patient",patient);
        return "formulaire";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        patientService.deletePatient(id);
        return "redirect:/";
    }

}

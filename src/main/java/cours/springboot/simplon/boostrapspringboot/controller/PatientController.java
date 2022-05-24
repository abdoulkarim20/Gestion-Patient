package cours.springboot.simplon.boostrapspringboot.controller;

import cours.springboot.simplon.boostrapspringboot.entities.Patient;
import cours.springboot.simplon.boostrapspringboot.repository.PatientRepository;
import cours.springboot.simplon.boostrapspringboot.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
//    @Autowired // pas recommender d'utiliser mais plutot constructeur avek paramtre ou @AllArgsConstructor
//    PatientService patientService;
    private PatientRepository patientRepository;
    /*je veux faire la pagination donc
    * ma methode doit avoir de parametre supplementaire voir la methode
    * puisque nous devons utiliser la pagination donc on peut remplacer List<Patient> par Page<Patient>*/
    @GetMapping("/user/index")
    public String patients(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword
    ){
        Page<Patient> pagePatients=patientRepository.findByNomCompletContains(keyword,PageRequest.of(page,size));
        model.addAttribute("patients",pagePatients.getContent());
        /*pour stoker mes nombres de page et pouvoir naviger voir code ci-dessous*/
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        //je vais selectionner la page courante avek la methode ci-dessous
        model.addAttribute("currentPage",page);
        //pour stoker le keyword actuel
        model.addAttribute("keyword",keyword);
        return "patient";
    }
    @GetMapping("/admin/formulaire")
    public String formulaire(Model model){
//        Patient patient=new Patient();
        model.addAttribute("patient",new Patient());
        return "formulaire";
    }
    @PostMapping("/admin/save")
    public String save(Model model, @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword
    ){
        if (bindingResult.hasErrors()) return "/formulaire";
        patientRepository.save(patient);
        return "redirect:/admin/index?page="+page+"&keyword"+keyword;
    }

    @GetMapping("/admin/delete")
    //pourkw les trois argument dans la methode parce que je veux que si je supprime je reste dans la page courante avek la navigation
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/admin/index?page="+page+"&keyword="+keyword;
    }
    //la redirection dans la page accueil qui est index
    @GetMapping("/")
    public String home(){
        return "home";
    }

    //Supposons que je veux maintant travail coter client je peux proceder comme suit
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }

    //modification du patient
    /*afin de garder la page sur la quelle nous on se trouve c'est pourkw on
    * eut dans la function keyword et page comme argument suplementaire*/
    @GetMapping("/admin/editPatient")
    public String editPatient(Model model,Long id,String keyword ,int page){
        /*Patient patient=patientRepository.findById(id).orElse(null) // si ca existe pas returne null;
        ou
        Patient patient=patientRepository.findById(id).get() //signifie prend si ca existe*/

        Patient patient=patientRepository.findById(id).orElse(null);
        if (patient==null) throw new RuntimeException("Patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "editForme";
    }

}

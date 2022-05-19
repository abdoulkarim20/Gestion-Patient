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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @GetMapping("/index")
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
    @GetMapping("/formulaire")
    public String formulaire(Model model){
        Patient patient=new Patient();
        model.addAttribute("patient",patient);
        return "formulaire";
    }

    @GetMapping("/delete")
    //pourkw les trois argument dans la methode parce que je veux que si je supprime je reste dans la page courante avek la navigation
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    //la redirection dans la page accueil qui est index
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    //Supposons que je veux maintant travail coter client je peux proceder comme suit
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }

}

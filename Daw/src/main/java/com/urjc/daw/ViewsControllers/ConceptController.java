package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Concept.ConceptRepository;
import com.urjc.daw.Models.Concept.ConceptService;
import com.urjc.daw.Models.Item.ItemRepository;
import com.urjc.daw.Models.Question.QuestionRepository;
import com.urjc.daw.Models.Answer.AnswerRepository;
import com.urjc.daw.Models.User.User;
import com.urjc.daw.Models.User.UserRepository;
import com.urjc.daw.Models.Lessons.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class ConceptController {
    @Autowired
    ConceptService conceptService;

    @Autowired
    ConceptRepository conceptRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/deleteConcept/{id}")
    public String deleteConcept(Model model, @PathVariable long id) {
        conceptService.deleteConceptById(id);
        return "redirect:/MainPage";
    }

    @PostMapping("/saveConcept")
    public String saveLesson(Model model, Concept concept, @RequestParam("file")MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
        if(!multipartFile.isEmpty()){
            Path derectorioRecursos = Paths.get("src//main//resources//static//uploads");
            String rootPath = derectorioRecursos.toFile().getAbsolutePath();
            try {
                byte[] bytes = multipartFile.getBytes();
                Path rutaCompleta = Paths.get(rootPath + "//" + multipartFile.getOriginalFilename());
                Files.write(rutaCompleta, bytes);
                redirectAttributes.addFlashAttribute("info", "Has subido correctamente ' " + multipartFile.getOriginalFilename() + "'");
                concept.setPicture(multipartFile.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        conceptService.addConcept(concept);
        return "redirect:/MainPage";
    }




    @GetMapping("/StudentConcept/{idUser}/{idConcept}")
    public String showStudent(Model model, @PathVariable long idConcept, @PathVariable long idUser){
        Optional<Concept> concept = conceptRepository.findByIdConcept(idConcept);
        Optional<User> user = userRepository.findByIdUser(idUser);
        if(concept.isPresent()) {
            model.addAttribute("items", itemRepository.findItemByIdConcept(concept.get()));
            model.addAttribute("answer", answerRepository.findAnswerByIdUser(user.get()));
            model.addAttribute("question", questionRepository.findAll());
        }
        return "ConceptView/StudentConceptView";
    }
}

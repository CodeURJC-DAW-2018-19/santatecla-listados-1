package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Answer.AnswerRepository;
import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Concept.ConceptRepository;
import com.urjc.daw.Models.Concept.ConceptService;
import com.urjc.daw.Models.Item.ItemRepository;
import com.urjc.daw.Models.Item.ItemService;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Lessons.LessonRepository;
import com.urjc.daw.Models.Lessons.LessonService;
import com.urjc.daw.Models.Question.QuestionRepository;
import com.urjc.daw.Models.User.User;
import com.urjc.daw.Models.User.UserComponent;
import com.urjc.daw.Models.User.UserRepository;
import com.urjc.daw.Models.Question.QuestionService;
import com.urjc.daw.Models.Answer.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    ItemService itemService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @Autowired
    UserComponent userComponent;

    @Autowired
    LessonService lessonService;

    @Autowired
    ConceptRepository conceptRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;


    @ModelAttribute
    public void addUserToModel(Model model) {
        boolean logged = userComponent.getLoggedUser() != null;
        model.addAttribute("logged", logged);
        if(logged) {
            boolean teacher;
            boolean student;
            if(userComponent.getLoggedUser().getUserType().equals("ROLE_STUDENT")){
                student=true;
                teacher=false;
            }else{
                student=false;
                teacher=true;
            }
            model.addAttribute("admin",teacher);
            model.addAttribute("student",student);
            model.addAttribute("idUser", userComponent.getLoggedUser().getId());
        }
    }

    @GetMapping("/StudentConceptView/{idConcept}")
    public String showStudent(Model model, @PathVariable long idConcept){
        Optional<Concept> concept = conceptRepository.findByIdConcept(idConcept);
        Optional<User> user = userRepository.findByIdUser(userComponent.getLoggedUser().getId());
        if(concept.isPresent()) {
            model.addAttribute("items", itemRepository.findItemByIdConcept(concept.get()));
            model.addAttribute("answer", answerRepository.findAnswerByIdUser(user.get()));
            model.addAttribute("question", questionRepository.findAll());
        }
        return "ConceptView/StudentConceptView";
    }

    @RequestMapping(path = "/")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(path = "/sign_in")
    public String signin(Model model){
        return "sign_in";
    }



    @PostMapping(path = "/add")
    public @ResponseBody String add (Model model,User user) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        if(user.getName()==null || user.getpassword()==null){
            return "redirect:/sign_in";
        }else{
            user.setUserType("ROLE_STUDENT");
            userRepository.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/TeacherConcept_View/{idConcept}")
    public String showConcept(Model model, @PathVariable long idConcept){
        Optional<Concept> concept = conceptRepository.findByIdConcept(idConcept);
        if(concept.isPresent()) {
            model.addAttribute("items", itemRepository.findItemByIdConcept(concept.get()));
            model.addAttribute("questions",questionRepository.findByidConcept(concept.get()));
        }
        return "ConceptView/TeacherConcept_View";
    }

    @RequestMapping(path = "/MainPage")
    public String showMainPage(Model model, HttpServletRequest request) {
        model.addAttribute("lessons", lessonService.findAll());

        return "MainPage";
    }
}

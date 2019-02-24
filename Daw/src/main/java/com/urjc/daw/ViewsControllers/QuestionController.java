package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Concept.ConceptService;
import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Item.ItemService;
import com.urjc.daw.Models.Question.Question;
import com.urjc.daw.Models.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class QuestionController {

    @Autowired
    public QuestionService questionService;

    @Autowired
    public ConceptService conceptService;

    @Autowired
    public ItemService itemService;

    @GetMapping("/deleteQuestion/{id}")
    public String deleteConcept(Model model, @PathVariable long id) {
        questionService.deleteQuestionById(id);
        return "redirect:/TeacherConcept_View";
    }

    @PostMapping("/saveQuestion/{idQuestion}")
    public String saveQuestion(@Valid Question question, Model model, SessionStatus sessionStatus) {
        questionService.addQuestion(question);
        sessionStatus.setComplete();
        return "redirect:/TeacherConcept_View";
    }

    @GetMapping("/Concept/{id}/QuestionsType1/1/item/{idItem}/Questions/{idQuestion}")
    public String questiontypeone(Model model, @PathVariable(value = "id") long id, @PathVariable(value = "idQuestion") long idQuestion, @PathVariable(value = "idItem") long idItem){
        Optional<Concept> concept = conceptService.findByOneId(id);
        Optional<Item> item = itemService.findOne(idItem);
       // Question question = questionService.findByConceptAndId(concept, idQuestion);

        model.addAttribute("question", "¿ "+ item.get() + " es un elemento de " + concept.get());



        return "StundentConceptView";
    }

}

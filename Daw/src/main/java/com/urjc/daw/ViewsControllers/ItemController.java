package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Concept.ConceptService;
import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Item.ItemRepository;
import com.urjc.daw.Models.Item.ItemService;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    ConceptService conceptService;

    @Autowired
    ItemRepository itemRepository;


    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/deleteItem/{idItem}/{idConcept}")
    public String deleteItem(Model model, @PathVariable long idItem, @PathVariable long idConcept) {
        itemService.deleteItemById(idItem);

        return "redirect:/TeacherConcept_View/{idConcept}";
    }

    @PostMapping("/saveItem/{idConcept}")
    public String saveItem(Model model, Item item, @PathVariable long idConcept){
        Optional<Concept> c = conceptService.findByOneId(idConcept);
        if(c.isPresent()){
            Concept concept = c.get();
            concept.addItem(item);
            conceptService.addConcept(concept);
        }
        return "redirect:/TeacherConcept_View/{idConcept}";
    }


    @GetMapping (path = "/loadMoreItems")
    public String topicMoreButton(Model model,  @PageableDefault(size = 2) Pageable page){
        //Optional<Concept> c = conceptService.findByOneId(idConcept);
        //model.addAttribute("items", itemService.findItemByIdConcept(page,c.get()));
        return "Items";
    }
}

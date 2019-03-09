package com.urjc.daw.api_rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.concept.ConceptService;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.item.ItemService;
import com.urjc.daw.models.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping ("/api/items")
public class ItemRest extends OperationsRest<Item> {

    interface ItemsDetails extends Item.BasicInfo,Item.ConceptList,Item.QuestionList,
            Question.BasicInfo,Concept.BasicInfo {}

    @Autowired
    private ItemService itemService;
    @Autowired
    private ConceptService conceptService;

    @GetMapping(value = "/{id}")
    @JsonView(ItemsDetails.class)
    public ResponseEntity<Item> getItem (@PathVariable long id) {
        Optional<Item> item = itemService.findOne(id);
        return checkIfExist(item);
    }

    @PostMapping("/concept/{idConcept}")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(ItemsDetails.class)
    public ResponseEntity<Item> createItem(@PathVariable long idConcept,@RequestBody Item item){
        Optional<Concept> c = conceptService.findByOneId(idConcept);
        if (c.isPresent()) {
            Concept concept = c.get();
            concept.addItem(item);
            conceptService.addConcept(concept);
            item.setIdConcept(c.get());
        }
        return safeCreate(item,itemService.repository);
    }

    @DeleteMapping("/{id}")
    @JsonView(ItemsDetails.class)
    public Item deteleItem(@PathVariable long id){
        Item deleteItem = itemService.findByOneId(id).get();
        itemService.deleteItemById(id);
        return deleteItem;
    }
}

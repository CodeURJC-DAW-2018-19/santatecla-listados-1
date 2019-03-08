package com.urjc.daw.api_rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.concept.Concept;
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
public class ItemRest {

    interface ItemsDetails extends Item.BasicInfo,Item.ConceptList,Item.QuestionList,
            Question.BasicInfo,Concept.BasicInfo {}

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/{id}")
    @JsonView(ItemsDetails.class)
    public ResponseEntity<Item> getItem (@PathVariable long id) {
        Optional<Item> item = itemService.findOne(id);
        if(item.isPresent()){
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(ItemsDetails.class)
    public Item createItem(@RequestBody Item item){
        itemService.addItem(item);
        return item;
    }

    @PutMapping("/{id}")
    @JsonView(ItemsDetails.class)
    public Item updateItem(@PathVariable long id, @RequestBody Item updatedItem){
        itemService.findByOneId(id).get();
        updatedItem.setIdItem(id);
        itemService.addItem(updatedItem);
        return updatedItem;
    }

    @DeleteMapping("/{id}")
    @JsonView(ItemsDetails.class)
    public Item deteleItem(@PathVariable long id){
        Item deleteItem = itemService.findByOneId(id).get();
        itemService.deleteItemById(id);
        return deleteItem;
    }
}

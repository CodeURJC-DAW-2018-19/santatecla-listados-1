package com.urjc.daw.api_rest;

import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;

@RestController
@RequestMapping ("/api/items")
public class ItemRest {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/{id}")
    public Item getItem(@PathVariable long id) {
        Item item = itemService.findOne(id).get();
        System.out.println(item.getIdItem());
        return item;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@RequestBody Item item){
        itemService.addItem(item);
        return item;
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable long id, @RequestBody Item updatedItem){
        itemService.findByOneId(id).get();
        updatedItem.setIdItem(id);
        itemService.addItem(updatedItem);
        return updatedItem;
    }

    @DeleteMapping("/{id}")
    public Item deteleItem(@PathVariable long id){
        Item deleteItem = itemService.findByOneId(id).get();
        itemService.deleteItemById(id);
        return deleteItem;
    }
}

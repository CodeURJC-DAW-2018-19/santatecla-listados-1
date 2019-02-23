package com.urjc.daw.ViewsControllers;

import com.urjc.daw.Models.Item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/deleteItem/{idItem}/{id}")
    public String deleteItem(Model model, @PathVariable long idItem, @PathVariable long idConcept) {
        itemService.deleteItemById(idItem);
        System.out.println("delete");
        return "redirect:TeacherConcept_View/{idConcept}";
    }
}

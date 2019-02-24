package com.urjc.daw.Models.Item;

import com.urjc.daw.Models.Concept.Concept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public void addItem(Item item){
        repository.save(item);
    }

    public Page<Item> findAll(Pageable page){
        return repository.findAll(page);
    }

    public Optional<Item> findOne(Long idItem) {
        return repository.findById(idItem);
    }

    public List<Item> findItemByState(boolean state){ return repository.findItemByState(state); }

    public void deleteItemById(long idItem){repository.deleteById(idItem);}

    public Page<Item> findItemByIdConcept(Pageable page, Concept IdConcept){
        return repository.findItemByIdConcept(page,IdConcept);
    }

    public List<Item> findItemByIdConceptIs(Concept IdConcept){
        return repository.findItemByIdConceptIs(IdConcept);
    }




}

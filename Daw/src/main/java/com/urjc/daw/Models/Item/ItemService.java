package com.urjc.daw.Models.Item;

import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Lessons.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> findAll(){
        return repository.findAll();
    }

    public Optional<Item> findOne(Integer id) {
        return repository.findById(id);
    }
}

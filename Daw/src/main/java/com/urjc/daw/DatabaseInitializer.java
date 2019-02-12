package com.urjc.daw;

import com.urjc.daw.Models.Concept;
import com.urjc.daw.Repository.ConceptRepository;
import com.urjc.daw.Repository.ItemRepository;
import com.urjc.daw.Repository.LessonRepository;
import com.urjc.daw.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ConceptRepository conceptRepository;

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {

	}

}

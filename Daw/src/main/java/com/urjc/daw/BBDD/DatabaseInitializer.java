package com.urjc.daw.BBDD;

import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.User.User;
import com.urjc.daw.Models.Concept.ConceptRepository;
import com.urjc.daw.Models.Item.ItemRepository;
import com.urjc.daw.Models.Lessons.LessonRepository;
import com.urjc.daw.Models.User.UserRepository;
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

		User mg = new User("Micael Gallego","12345","m.gallego@urjc.es","TEACHER");

		userRepository.save(new User("Ana Álvarez","12345","a.alvarezo.2016@alumnos.urjc.es","ROLE_STUDENT"));
		userRepository.save(new User("Héctor Mediero","12345","h.mediero.2016@alumnos.urjc.es","ROLE_STUDENT"));
		userRepository.save(new User("aitana","aitana","a.cerezo.2016@alumnos.urjc.es","ROLE_STUDENT"));
		userRepository.save(new User("Sandra Cañadas","12345","s.canadas.2016@alumnos.urjc.es","ROLE_STUDENT"));
		userRepository.save(new User("Ernesto Baltasar","12345","e.baltasar.2016@alumnos.urjc.es","ROLE_STUDENT"));

		userRepository.save(new User("Rauldilla Fernandez","12345","r.fdez.2016@alumnos.urjc.es","Visitante"));


		Lesson lesson1=new Lesson("TEMA 1");
		Lesson lesson2 = new Lesson("TEMA 2");


		Concept concept2 = new Concept("Concepto2",2);
		Concept concept3 = new Concept("Concepto3",3);
		Concept concept4 = new Concept("Concepto4",4);



		Item item1 = new Item("Pregunta 1",true);
		Item item3 = new Item("Pregunta 3",true);
		Item item4 = new Item("Pregunta 4",false);


		lesson1.addConcept(concept2);
		lesson2.addConcept(concept3);
		lesson1.addConcept(concept4);

		concept2.setIdLesson(lesson1);
		concept3.setIdLesson(lesson2);
		concept4.setIdLesson(lesson1);


		concept2.addItem(item1);
		concept3.addItem(item3);
		concept4.addItem(item4);

		item1.setIdConcept(concept2);
		item3.setIdConcept(concept3);
		item4.setIdConcept(concept4);


		lessonRepository.save(lesson1);
		lessonRepository.save(lesson2);

		conceptRepository.save(concept2);
		conceptRepository.save(concept3);
		conceptRepository.save(concept4);


		itemRepository.save(item1);
		itemRepository.save(item3);
		itemRepository.save(item4);
	}


}

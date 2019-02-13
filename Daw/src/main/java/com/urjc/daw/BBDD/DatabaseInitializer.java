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

		User mg = new User("Micael Gallego","12345","m.gallego@urjc.es","Profesor");

		userRepository.save(new User("Ana Álvarez","12345","a.alvarezo.2016@alumnos.urjc.es","Estudiante"));
		userRepository.save(new User("Héctor Mediero","12345","h.mediero.2016@alumnos.urjc.es","Estudiante"));
		userRepository.save(new User("Aitana Cerezo","12345","a.cerezo.2016@alumnos.urjc.es","Estudiante"));
		userRepository.save(new User("Sandra Cañadas","12345","s.canadas.2016@alumnos.urjc.es","Estudiante"));
		userRepository.save(new User("Ernesto Baltasar","12345","e.baltasar.2016@alumnos.urjc.es","Estudiante"));

		userRepository.save(new User("Rauldilla Fernandez","12345","r.fdez.2016@alumnos.urjc.es","Visitante"));


		Lesson lesson1=new Lesson("TEMA 1",0);
		Lesson lesson2 = new Lesson("TEMA 2",0);
		Lesson lesson3 = new Lesson("TEMA 3",0);
		Lesson lesson4 = new Lesson("TEMA 4",0);

		lessonRepository.save(lesson1);
		lessonRepository.save(lesson2);
		lessonRepository.save(lesson3);
		lessonRepository.save(lesson4);

		Concept concept1 = new Concept(lesson1,"Concepto1",1);
		Concept concept2 = new Concept(lesson1,"Concepto2",2);
		Concept concept3 = new Concept(lesson1,"Concepto2",3);
		Concept concept4 = new Concept(lesson2,"Concepto4",4);

		Item item1 = new Item(concept1,"Pregunta 1");
		Item item2 = new Item(concept1,"Pregunta 2");
		Item item3 = new Item(concept1,"Pregunta 3");
		Item item4 = new Item(concept1,"Pregunta 4");

	}

}

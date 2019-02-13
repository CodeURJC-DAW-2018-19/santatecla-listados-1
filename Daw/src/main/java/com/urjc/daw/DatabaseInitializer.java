package com.urjc.daw;

import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.User.User;
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
		User javier = new User("Javier Garzás","12345","j.garzas@urjc.es","Profesor");
		User mg = new User("Micael Gallego","12345","m.gallego@urjc.es","Profesor");
		User chema = new User("Chemita Alosnso","12345","c.alonso@urjc.es","Profesor");
		User bilGates= new User("Bil Gates","12345","b.gates@urjc.es","Profesor");

		userRepository.save(new User("Ana Álvarez","12345","a.alvarezo.2016@alumnos.urjc.es","Estudiante"));
		userRepository.save(new User("Héctor Mediero","12345","h.mediero.2016@alumnos.urjc.es","Estudiante"));
		userRepository.save(new User("Aitana Cerezo","12345","a.cerezo.2016@alumnos.urjc.es","Estudiante"));
		userRepository.save(new User("Sandra Cañadas","12345","s.canadas.2016@alumnos.urjc.es","Estudiante"));
		userRepository.save(new User("Ernesto Baltasar","12345","e.baltasar.2016@alumnos.urjc.es","Estudiante"));

		userRepository.save(new User("Rauldilla Fernandez","12345","r.fdez.2016@alumnos.urjc.es","Visitante"));

		lessonRepository.save(new Lesson(javier,"TEMA 1","0"));
		lessonRepository.save(new Lesson(chema,"TEMA 1","0"));
		lessonRepository.save(new Lesson(mg,"TEMA 1","0"));
		lessonRepository.save(new Lesson(bilGates,"TEMA 1","0"));


	}

}

package com.urjc.daw.BBDD;

import com.urjc.daw.Models.Answer.AnswerRepository;
import com.urjc.daw.Models.Answer.Answer;
import com.urjc.daw.Models.Answer.AnswerService;
import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Question.Question;
import com.urjc.daw.Models.Question.QuestionRepository;
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

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@PostConstruct
	public void init() {

		User mg = new User("micael","micael","m.gallego@urjc.es","ROLE_TEACHER");
        User aa = new User("Ana Álvarez","12345","a.alvarezo.2016@alumnos.urjc.es","ROLE_STUDENT");
        User hm = new User("Héctor Mediero","12345","h.mediero.2016@alumnos.urjc.es","ROLE_STUDENT");
        User ac = new User("aitana","aitana","a.cerezo.2016@alumnos.urjc.es","ROLE_STUDENT");
		User sc = new User("Sandra Cañadas","12345","s.canadas.2016@alumnos.urjc.es","ROLE_STUDENT");
        User eb = new User("Ernesto Baltasar","12345","e.baltasar.2016@alumnos.urjc.es","ROLE_STUDENT");

        userRepository.save(mg);
        userRepository.save(aa);
        userRepository.save(hm);
        userRepository.save(ac);
        userRepository.save(sc);
        userRepository.save(eb);


		Lesson lesson1=new Lesson("TEMA 1");
		Lesson lesson2 = new Lesson("TEMA 2");
		Lesson lesson3 = new Lesson("TEMA 3");
		Lesson lesson4 = new Lesson("TEMA 4");
		Lesson lesson5 = new Lesson("TEMA 5");
		Lesson lesson6 = new Lesson("TEMA 6");
		Lesson lesson7 = new Lesson("TEMA 7");
		Lesson lesson8 = new Lesson("TEMA 8");
		Lesson lesson9 = new Lesson("TEMA 9");
		Lesson lesson10 = new Lesson("TEMA 10");
		Lesson lesson11 = new Lesson("TEMA 11");
		Lesson lesson12 = new Lesson("TEMA 12");
		Lesson lesson13 = new Lesson("TEMA 13");
		Lesson lesson14 = new Lesson("TEMA 14");
		Lesson lesson15 = new Lesson("TEMA 15");
		Lesson lesson16 = new Lesson("TEMA 16");
		Lesson lesson17 = new Lesson("TEMA 17");
		Lesson lesson18 = new Lesson("TEMA 18");
		Lesson lesson19 = new Lesson("TEMA 19");
		Lesson lesson20 = new Lesson("TEMA 20");



		Concept concept2 = new Concept("Concepto2: Las disciplinas del software",2, "mundo.png");
		Concept concept3 = new Concept("Concepto3: Los patrones de diseño creacionales",3, "mundo.pgn");
		Concept concept4 = new Concept("Concepto4: Las disciplinas del software",4, "mundo.png");
		Concept concept5 = new Concept("Concepto5",5);
		Concept concept6 = new Concept("Concepto6",6);
		Concept concept7 = new Concept("Concepto7",7);
		Concept concept8 = new Concept("Concepto8",8);
		Concept concept9 = new Concept("Concepto9",9);
		Concept concept10 = new Concept("Concepto10",10);
		Concept concept11 = new Concept("Concepto11",11);
		Concept concept12 = new Concept("Concepto12",12);
		Concept concept13 = new Concept("Concepto13",13);
		Concept concept14 = new Concept("Concepto14",14);
		Concept concept15 = new Concept("Concepto15",15);
		Concept concept16 = new Concept("Concepto16",16);
		Concept concept17 = new Concept("Concepto17",17);
		Concept concept18 = new Concept("Concepto18",18);
		Concept concept19 = new Concept("Concepto19",19);
		Concept concept20 = new Concept("Concepto20",20);



/** initialization of real data **/
		Item item1 = new Item("Requisitos",true);
		Item item3 = new Item("Prototype",true);
		Item item4 = new Item("Diseño",true);
		Item item5 = new Item("Programación",true);
		Item item6 = new Item("Pruebas",true);
		Item item17 = new Item("Despliegue",true);
		Item item7 = new Item("Comprensión",false);
		Item item8 = new Item("Ofuscación",false);
		Item item16 = new Item("Análisis de impacto",false);

		Item item9 = new Item("Abstract factory",true);
		Item item10 = new Item("Builder",true);
		Item item11 = new Item("Factory Method",true);
		Item item12 = new Item("Prototype",true);
		Item item13 = new Item("Singleton",true);
		Item item14 = new Item("Proxy",true);
		Item item15 = new Item("Flyweight",true);
		Item item18 = new Item("Bridge",false);
		Item item19 = new Item("Composite",false);
/***************************************************************/



		Item item20 = new Item("Compresión",false);



		lesson1.addConcept(concept2);
		lesson2.addConcept(concept3);
		lesson1.addConcept(concept4);
		lesson1.addConcept(concept5);
		lesson1.addConcept(concept6);
		lesson1.addConcept(concept7);
		lesson1.addConcept(concept8);
		lesson1.addConcept(concept9);
		lesson1.addConcept(concept10);
		lesson1.addConcept(concept11);
		lesson1.addConcept(concept12);
		lesson1.addConcept(concept13);
		lesson1.addConcept(concept14);
		lesson1.addConcept(concept15);
		lesson1.addConcept(concept16);
		lesson1.addConcept(concept17);
		lesson1.addConcept(concept18);
		lesson1.addConcept(concept19);
		lesson1.addConcept(concept20);


		concept2.setIdLesson(lesson1);
		concept3.setIdLesson(lesson2);
		concept4.setIdLesson(lesson1);
		concept5.setIdLesson(lesson1);
		concept6.setIdLesson(lesson1);
		concept7.setIdLesson(lesson1);
		concept8.setIdLesson(lesson1);
		concept9.setIdLesson(lesson1);
		concept10.setIdLesson(lesson1);
		concept11.setIdLesson(lesson1);
		concept12.setIdLesson(lesson1);
		concept13.setIdLesson(lesson1);
		concept14.setIdLesson(lesson1);
		concept15.setIdLesson(lesson1);
		concept16.setIdLesson(lesson1);
		concept17.setIdLesson(lesson1);
		concept18.setIdLesson(lesson1);
		concept19.setIdLesson(lesson1);
		concept20.setIdLesson(lesson1);


/** initialization of real data **/
		concept2.addItem(item1);
		concept2.addItem(item3);
		concept2.addItem(item4);
		concept2.addItem(item3);
		concept2.addItem(item4);
		concept2.addItem(item5);
		concept2.addItem(item6);
		concept2.addItem(item7);
		concept2.addItem(item8);
		concept2.addItem(item16);
		concept2.addItem(item17);

		concept3.addItem(item9);
		concept3.addItem(item10);
		concept3.addItem(item11);
		concept3.addItem(item12);
		concept3.addItem(item13);
		concept3.addItem(item14);
		concept3.addItem(item15);
		concept3.addItem(item18);
		concept3.addItem(item19);
/************************************************/


		concept3.addItem(item3);
		concept4.addItem(item4);
		concept5.addItem(item5);
		concept6.addItem(item6);
		concept7.addItem(item7);
		concept8.addItem(item8);
		concept9.addItem(item9);
		concept10.addItem(item10);
		concept11.addItem(item11);
		concept12.addItem(item12);
		concept13.addItem(item13);
		concept14.addItem(item14);
		concept15.addItem(item15);
		concept16.addItem(item16);
		concept17.addItem(item17);
		concept18.addItem(item18);
		concept19.addItem(item19);
		concept20.addItem(item20);



		item1.setIdConcept(concept2);
		item3.setIdConcept(concept3);
		item4.setIdConcept(concept4);
		item4.setIdConcept(concept4);
		item5.setIdConcept(concept5);
		item6.setIdConcept(concept6);
		item7.setIdConcept(concept7);
		item8.setIdConcept(concept8);
		item9.setIdConcept(concept9);
		item10.setIdConcept(concept10);
		item11.setIdConcept(concept11);
		item12.setIdConcept(concept12);
		item13.setIdConcept(concept13);
		item14.setIdConcept(concept14);
		item15.setIdConcept(concept15);
		item16.setIdConcept(concept16);
		item17.setIdConcept(concept17);
		item18.setIdConcept(concept18);
		item19.setIdConcept(concept19);
		item20.setIdConcept(concept20);




		lessonRepository.save(lesson1);
		lessonRepository.save(lesson2);
		lessonRepository.save(lesson3);
		lessonRepository.save(lesson4);
		lessonRepository.save(lesson5);
		lessonRepository.save(lesson6);
		lessonRepository.save(lesson7);
		lessonRepository.save(lesson8);
		lessonRepository.save(lesson9);
		lessonRepository.save(lesson10);
		lessonRepository.save(lesson11);
		lessonRepository.save(lesson12);
		lessonRepository.save(lesson13);
		lessonRepository.save(lesson14);
		lessonRepository.save(lesson15);
		lessonRepository.save(lesson16);
		lessonRepository.save(lesson17);
		lessonRepository.save(lesson18);
		lessonRepository.save(lesson19);
		lessonRepository.save(lesson20);


		conceptRepository.save(concept2);
		conceptRepository.save(concept3);
		conceptRepository.save(concept4);
		conceptRepository.save(concept5);
		conceptRepository.save(concept6);
		conceptRepository.save(concept7);
		conceptRepository.save(concept8);
		conceptRepository.save(concept9);
		conceptRepository.save(concept10);
		conceptRepository.save(concept11);
		conceptRepository.save(concept12);
		conceptRepository.save(concept13);
		conceptRepository.save(concept14);
		conceptRepository.save(concept15);
		conceptRepository.save(concept16);
		conceptRepository.save(concept17);
		conceptRepository.save(concept18);
		conceptRepository.save(concept19);
		conceptRepository.save(concept20);



		itemRepository.save(item1);
		itemRepository.save(item3);
		itemRepository.save(item4);
		itemRepository.save(item5);
		itemRepository.save(item6);
		itemRepository.save(item7);
		itemRepository.save(item8);
		itemRepository.save(item9);
		itemRepository.save(item10);
		itemRepository.save(item11);
		itemRepository.save(item12);
		itemRepository.save(item13);
		itemRepository.save(item14);
		itemRepository.save(item15);
		itemRepository.save(item16);
		itemRepository.save(item17);
		itemRepository.save(item18);
		itemRepository.save(item19);
		itemRepository.save(item20);


		Answer answer1 = new Answer("Respuesta cerrada");
		Answer answer2 = new Answer("Respuesta cerrada");

		Question question1 = new Question(1,"¿Qué elemento falta en la lista análisis, programación, pruebas y diseño para completar las disciplinas del software?");

        hm.addAnswer(answer1);
        aa.addAnswer(answer2);

        answer1.setIdUser(hm);
        answer2.setIdUser(aa);

		question1.addAnswer(answer1);
		question1.addAnswer(answer2);

        question1.setConcept(concept2);

        concept2.addQuestion(question1);

		answer1.setQuestion(question1);
		answer2.setQuestion(question1);

        userRepository.save(hm);
        userRepository.save(aa);


		questionRepository.save(question1);

		conceptRepository.save(concept2);

        answerRepository.save(answer1);
        answerRepository.save(answer2);



	}


}

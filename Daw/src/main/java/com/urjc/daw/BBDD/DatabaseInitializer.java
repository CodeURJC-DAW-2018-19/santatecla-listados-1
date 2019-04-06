package com.urjc.daw.bbdd;

import com.urjc.daw.models.answer.AnswerRepository;
import com.urjc.daw.models.answer.Answer;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.lessons.Lesson;
import com.urjc.daw.models.question.Question;
import com.urjc.daw.models.question.QuestionRepository;
import com.urjc.daw.models.user.User;
import com.urjc.daw.models.concept.ConceptRepository;
import com.urjc.daw.models.item.ItemRepository;
import com.urjc.daw.models.lessons.LessonRepository;
import com.urjc.daw.models.user.UserRepository;
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

        User mg = new User("micael", "micael", "m.gallego@urjc.es", "ROLE_TEACHER");
        User aa = new User("ana", "ana", "a.alvarezo.2016@alumnos.urjc.es", "ROLE_STUDENT");
        User hm = new User("hector", "hector", "h.mediero.2016@alumnos.urjc.es", "ROLE_STUDENT");
        User ac = new User("aitana", "aitana", "a.cerezo.2016@alumnos.urjc.es", "ROLE_STUDENT");
        User sc = new User("sandra", "sandra", "s.canadas.2016@alumnos.urjc.es", "ROLE_STUDENT");
        User eb = new User("ernesto", "ernesto", "e.baltasar.2016@alumnos.urjc.es", "ROLE_STUDENT");

        userRepository.save(mg);
        userRepository.save(aa);
        userRepository.save(hm);
        userRepository.save(ac);
        userRepository.save(sc);
        userRepository.save(eb);

/** initialization of real data **/

        Lesson lesson1 = new Lesson("TEMA 1");
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


        Concept concept1 = new Concept("Introducción", 1, "ima.png");
        Concept concept2 = new Concept("Las disciplinas del software", 2, "mundo.png");
        Concept concept3 = new Concept("Los patrones de diseño creacionales", 3, "php.png");
        Concept concept4 = new Concept("Maquetación: HTML y CSSo", 4, "ima.png");
        Concept concept5 = new Concept("JavaScript", 5, "foto.png");
        Concept concept6 = new Concept("APIs REST", 6, "php.png");
        Concept concept7 = new Concept("Desarrollo web con Java y Spring", 7, "mundo.png");
        Concept concept8 = new Concept("Seguridad", 8, "ima.png");
        Concept concept9 = new Concept("Testing", 9, "mundo.png");
        Concept concept10 = new Concept("Encriptar", 10, "foto.png");
        Concept concept11 = new Concept("APIs REST con Spring", 11, "php.png");
        Concept concept12 = new Concept("Bases de datos con Spring", 12, "ima.png");
        Concept concept13 = new Concept("Seguridad con Spring", 13, "mundo.png");
        Concept concept14 = new Concept("Despliegue con Spring", 14, "php.png");
        Concept concept15 = new Concept("Virtualización, cloud computing y contenedores", 15, "foto.png");
        Concept concept16 = new Concept("Docker", 16, "ima.png");
        Concept concept17 = new Concept("Introducción a Angular: TypeScript y Herramientas", 17, "php.png");
        Concept concept18 = new Concept("Componentes en Angular", 18, "foto.png");
        Concept concept19 = new Concept("REST y Servicios en Angular", 19, "mundo.png");
        Concept concept20 = new Concept("Router en Angular", 20, "ima.png");


        Item item1 = new Item("Requisitos", true);
        Item item2 = new Item("Adapter", false);
        Item item3 = new Item("Prototype", true);
        Item item4 = new Item("Diseño", true);
        Item item5 = new Item("Programacion", true);
        Item item6 = new Item("Pruebas", true);
        Item item7 = new Item("Comprension", false);
        Item item8 = new Item("Ofuscacion", false);
        Item item9 = new Item("Abstract factory", true);
        Item item10 = new Item("Builder", true);
        Item item11 = new Item("Factory Method", true);
        Item item12 = new Item("Prototype", true);
        Item item13 = new Item("Singleton", true);
        Item item14 = new Item("Proxy", true);
        Item item15 = new Item("Flyweight", true);
        Item item16 = new Item("Analisis de impacto", false);
        Item item17 = new Item("Despliegue", true);
        Item item18 = new Item("Bridge", false);
        Item item19 = new Item("Composite", false);
        Item item20 = new Item("Compresión", false);
        Item item21 = new Item("Command", true);
        Item item22 = new Item("Iterator", true);
        Item item23 = new Item("Memento", true);
        Item item24 = new Item("Observer", true);

/***************************************************************/

        lesson1.addConcept(concept1);
        lesson1.addConcept(concept2);
        lesson1.addConcept(concept3);
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


        concept1.setIdLesson(lesson1);
        concept2.setIdLesson(lesson1);
        concept3.setIdLesson(lesson1);
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


        concept1.addItem(item1);
        concept1.addItem(item2);
        concept1.addItem(item3);
        concept1.addItem(item4);
        concept1.addItem(item5);
        concept1.addItem(item6);
        concept1.addItem(item7);
        concept1.addItem(item8);
        concept1.addItem(item9);
        concept1.addItem(item10);
        concept1.addItem(item11);
        concept1.addItem(item12);
        concept1.addItem(item13);
        concept1.addItem(item14);
        concept1.addItem(item15);
        concept1.addItem(item16);
        concept1.addItem(item17);
        concept1.addItem(item18);
        concept1.addItem(item19);
        concept1.addItem(item20);
        concept1.addItem(item21);
        concept1.addItem(item22);
        concept1.addItem(item23);
        concept1.addItem(item24);


        item1.setIdConcept(concept1);
        item2.setIdConcept(concept1);
        item3.setIdConcept(concept1);
        item4.setIdConcept(concept1);
        item4.setIdConcept(concept1);
        item5.setIdConcept(concept1);
        item6.setIdConcept(concept1);
        item7.setIdConcept(concept1);
        item8.setIdConcept(concept1);
        item9.setIdConcept(concept1);
        item10.setIdConcept(concept1);
        item11.setIdConcept(concept1);
        item12.setIdConcept(concept1);
        item13.setIdConcept(concept1);
        item14.setIdConcept(concept1);
        item15.setIdConcept(concept1);
        item16.setIdConcept(concept1);
        item17.setIdConcept(concept1);
        item18.setIdConcept(concept1);
        item19.setIdConcept(concept1);
        item20.setIdConcept(concept1);
        item21.setIdConcept(concept1);
        item22.setIdConcept(concept1);
        item23.setIdConcept(concept1);
        item24.setIdConcept(concept1);


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


        conceptRepository.save(concept1);
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
        itemRepository.save(item2);
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
        itemRepository.save(item21);
        itemRepository.save(item22);
        itemRepository.save(item23);
        itemRepository.save(item24);



        Question question1 = new Question(1, "¿Qué elemento falta en la lista análisis, programación, pruebas y diseño para completar las disciplinas del software?");
        Question question2 = new Question(1, "¿Requisitos es un elemento de las disciplinas del software?");
        Question question3 = new Question(1, "¿Análisis es un elemento de las disciplinas del software");


        Answer answer1 = new Answer("Respuesta cerrada");
        Answer answer2 = new Answer("Respuesta cerrada");
        Answer answer3 = new Answer("Respuesta cerrada");



        answer1.setState("wrong");
        answer3.setState("right");
        answer1.setCorrect(true);
        answer3.setCorrect(true);

        ac.addAnswer(answer1);
        ac.addAnswer(answer2);
        ac.addAnswer(answer3);

        answer1.setIdUser(ac);
        answer2.setIdUser(ac);
        answer3.setIdUser(ac);

        question1.addAnswer(answer1);
        question2.addAnswer(answer2);
        question3.addAnswer(answer3);

        question1.setConcept(concept2);
        question2.setConcept(concept2);
        question3.setConcept(concept2);

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

        concept2.addQuestion(question1);
        concept2.addQuestion(question2);
        concept2.addQuestion(question3);

        answer1.setQuestion(question1);
        answer2.setQuestion(question2);
        answer3.setQuestion(question3);


        answerRepository.save(answer1);
        answerRepository.save(answer2);
        answerRepository.save(answer3);

        userRepository.save(ac);


        conceptRepository.save(concept2);


    }


}

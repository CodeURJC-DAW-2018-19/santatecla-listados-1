package com.urjc.daw.models.lessons;

import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.question.Question;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lesson {

/**             Atributos           **/
    @Id
    @Column(name="ID_LESSON")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "NAME")
    private String title;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Concept> conceptSet;

/******************************************/


/**         CONTRUCTOR              **/
    public Lesson(){}

    public Lesson(String title) {
        this.title = title;
        conceptSet=new HashSet<>();
    }
    /******************************************/



/**             GETTER & SETTER             **/
    public long getIdLesson() {
        return id;
    }

    public void setIdLesson(Integer idLesson) {
        this.id = idLesson;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

/******************************************/

    public int getAnswerCorrectOfConcepts(){
        int dev=0;
        for (Concept concept : conceptSet) {
            for (Question question:concept.getSetQuestion()) {
                dev+=question.getAnswerCorrect();
            }
        }
        return dev;
    }

    public int getAnswerIncorrectOfConcepts(){
        int dev=0;
        for (Concept concept : conceptSet) {
            for (Question question:concept.getSetQuestion()) {
                dev+=question.getAnswerIncorrect();
            }
        }
        return dev;
    }

    public int getAnswerPendingOfConcepts(){
        int dev=0;
        for (Concept concept : conceptSet) {
            for (Question question:concept.getSetQuestion()) {
                dev+=question.getAnswerPending();
            }
        }
        return dev;
    }
    public int getSizeOfAnswerPendingOfConcepts() {
        int dev = 0;
        for (Concept concept : conceptSet) {
            for (Question question : concept.getSetQuestion()) {
                dev += question.getSizeQuestions();
            }
        }
        return dev;
    }



    public void addConcept(Concept concept){
        conceptSet.add(concept);
    }
}

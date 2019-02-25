package com.urjc.daw.Models.Concept;

import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Lessons.Lesson;
import com.urjc.daw.Models.Question.Question;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Concept {
/**             Atributos           **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idConcept;

    @ManyToOne
    private Lesson idLesson;

    @Column
    private String title;

    @Column
    private Integer conceptNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Item> itemSet;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Question> setQuestion;

    @Column
    private String picture;

/**********************************/


/**         Constructores       **/
    public Concept( String title, Integer conceptNumber) {
        this.title = title;
        this.conceptNumber = conceptNumber;
        this.itemSet=new HashSet<>();
        this.setQuestion=new HashSet<>();
    }

    public Concept(){}

    public Concept(Lesson idLesson, String title) {
        this.idLesson = idLesson;
        this.title = title;
        this.itemSet=new HashSet<>();
        this.setQuestion=new HashSet<>();
    }

    public Concept(String title, Integer conceptNumber, String pictures){
        this.title = title;
        this.conceptNumber = conceptNumber;
        this.itemSet=new HashSet<>();
        this.setQuestion=new HashSet<>();
        this.picture = pictures;
    }
/**********************************/


/********************   GETTER & SETTER ***********************/
    public long getIdConcept() {
        return idConcept;
    }

    public void setIdConcept(long idConcept) {
        this.idConcept = idConcept;
    }

    public long getIdLesson() {
        return idLesson.getIdLesson();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getConceptNumber() {
        return conceptNumber;
    }

    public void setConceptNumber(Integer conceptNumber) {
        this.conceptNumber = conceptNumber;
    }

    public Set getItemTreeMap() {
        return itemSet;
    }

    public void setIdLesson(Lesson idLesson) {
        this.idLesson = idLesson;
    }

    public Set<Item> getItemSet() {
        return itemSet;
    }

    public Set<Question> getSetQuestion() {
        return setQuestion;
    }

    public String getPicture() {
        return picture;
    }

    public void setSetQuestion(Set<Question> setQuestion) {
        this.setQuestion = setQuestion;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**************************************************************/

    public void addItem(Item item){
        itemSet.add(item);
    }

    public void addQuestion(Question question){ setQuestion.add(question); }

    public String toStringMetrics(){
        int right = 0;
        int wrong = 0;
        int pending = 0;
        int size = 0;
        for (Question q: setQuestion) {
            right += q.getAnswerCorrect();
            wrong += q.getAnswerIncorrect();
            pending += q.getAnswerPending();
            size += q.getSizeQuestions();
        }

        return (right + " : " + wrong + " : " + pending + " / " + pending);
    }

}

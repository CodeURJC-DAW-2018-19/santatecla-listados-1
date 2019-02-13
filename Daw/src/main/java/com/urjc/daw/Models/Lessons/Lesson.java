package com.urjc.daw.Models.Lessons;

import com.urjc.daw.Models.Concept.Concept;

import javax.persistence.*;

@Entity
public class Lesson {
    @Id
    @Column(name="ID_LESSON")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "NAME")
    private String title;

    @Column(name="NUMBER_OF_CONCEPTS")
    private int conceptNumber;

    public Lesson(String title, int conceptNumber) {
        this.title = title;
        this.conceptNumber = conceptNumber;
    }

    public Lesson(){}

    //region ---------GETTER & SETTER-------------
    public Integer getIdLesson() {
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

    public int getConceptNumber() {
        return conceptNumber;
    }

    public void setConceptNumber(int conceptNumber) {
        this.conceptNumber = conceptNumber;
    }


    //endregion

    public void addConcept(Concept concept){
        //conceptTreeMap.put(concept.getIdConcept(),concept);
        setConceptNumber(conceptNumber+1);
    }
}

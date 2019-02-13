package com.urjc.daw.Models.Lessons;

import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.User.User;

import javax.persistence.*;
import java.util.TreeMap;

@Entity
public class Lesson {
    @Id
    @Column(name="ID_LESSON")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLesson;

    @Column(name = "NAME")
    private String lessonName;

    @Column(name="NUMBER_OF_CONCEPTS")
    private int conceptNumber;

    private TreeMap<Integer, Concept> conceptTreeMap;

    public Lesson( String lessonName, int conceptNumber) {
        this.lessonName = lessonName;
        this.conceptNumber = conceptNumber;
    }

    //region ---------GETTER & SETTER-------------
    public Integer getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(Integer idLesson) {
        this.idLesson = idLesson;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public int getConceptNumber() {
        return conceptNumber;
    }

    public void setConceptNumber(int conceptNumber) {
        this.conceptNumber = conceptNumber;
    }

    public TreeMap<Integer, Concept> getConceptTreeMap() {
        return conceptTreeMap;
    }

    public void setConceptTreeMap(TreeMap<Integer, Concept> conceptTreeMap) {
        this.conceptTreeMap = conceptTreeMap;
    }
    //endregion

    public void addConcept(Concept concept){
        conceptTreeMap.put(concept.getIdConcept(),concept);
        setConceptNumber(conceptNumber+1);
    }
}

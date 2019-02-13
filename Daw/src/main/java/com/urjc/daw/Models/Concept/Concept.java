package com.urjc.daw.Models.Concept;

import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Lessons.Lesson;

import javax.persistence.*;
import java.util.TreeMap;


@Entity
public class Concept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConcept;

    @ManyToOne(cascade = CascadeType.ALL)
    private Lesson idLesson;

    @Column
    private String title;

    @Column
    private Integer conceptNumber;

    @Column
    private TreeMap<Integer, Item> itemTreeMap;

    public Concept(Lesson idLesson, String title, Integer conceptNumber) {
        this.idLesson = idLesson;
        this.title = title;
        this.conceptNumber = conceptNumber;
    }
//region ---------GETTER & SETTER-------------
    public Integer getIdConcept() {
        return idConcept;
    }

    public void setIdConcept(Integer idConcept) {
        this.idConcept = idConcept;
    }

    public int getIdLesson() {
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

    public TreeMap<Integer, Item> getItemTreeMap() {
        return itemTreeMap;
    }

//endregion

    public void addItem(Item item){
        itemTreeMap.put(item.getIdConcept(),item);
    }
}

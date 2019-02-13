package com.urjc.daw.Models.Concept;

import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Lessons.Lesson;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.TreeMap;


@Entity
public class Concept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConcept;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotEmpty
    private Lesson idLesson;

    @Column
    @NotEmpty
    private String title;

    @Column
    @NotEmpty
    private Integer conceptNumber;

    @Column
    @NotEmpty
    private TreeMap<Integer, Item> itemTreeMap;

    private String photo;

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

    public int getIdLesson() { return idLesson.getIdLesson(); }

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

    public void setPhoto(String photo){
        this.photo = photo;
    }

    public String getPhoto(){
        return photo;
    }
//endregion

    //public void addItem(Item item){ itemTreeMap.put(item.getIdConcept(),item); }
}

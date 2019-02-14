package com.urjc.daw.Models.Concept;

import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Lessons.Lesson;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Concept {
/**             Atributos           **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idConcept;

    @ManyToOne(cascade = CascadeType.ALL)
    private Lesson idLesson;

    @Column
    private String title;

    @Column
    private Integer conceptNumber;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Item> itemSet;

/**********************************/


/**         Constructores       **/
    public Concept( String title, Integer conceptNumber) {
        this.title = title;
        this.conceptNumber = conceptNumber;
        this.itemSet=new HashSet<>();
    }

    public Concept(){}
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

/**************************************************************/

    public void addItem(Item item){
        itemSet.add(item);
    }

}

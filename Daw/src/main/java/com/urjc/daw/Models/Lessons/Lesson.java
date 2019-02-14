package com.urjc.daw.Models.Lessons;

import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Item.Item;

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



    public void addConcept(Concept concept){
        conceptSet.add(concept);
    }
}

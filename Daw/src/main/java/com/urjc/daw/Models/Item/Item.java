package com.urjc.daw.Models.Item;

import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Question.Question;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
/**             Atributos               **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idItem;

    @ManyToOne
    private Concept idConcept;

    @Column
    private Boolean state;

    @ManyToMany
    private Set<Question> setQuestion;

    @Column
    private String info;
/*****************************************/


/**             Constructor             **/
    public Item( String info,boolean state) {
        this.info = info;
        this.state = state;
        this.setQuestion = new HashSet<>();
    }

    public Item(){}
/*************************************/



/**             GETTER & SETTER             **/
    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public long getIdConcept() {
        return idConcept.getIdConcept();
    }


    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setIdConcept(Concept idConcept) {
        this.idConcept = idConcept;
    }

/*************************************/

    public void addQuestion(Question question){this.setQuestion.add(question);}



}

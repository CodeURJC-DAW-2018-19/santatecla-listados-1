package com.urjc.daw.Models.Item;

import com.urjc.daw.Models.Concept.Concept;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItem;

    @OneToOne(cascade = CascadeType.ALL)
    private Concept idConcept;

    @Column
    private Boolean correct;

    @Column
    private String info;

    public Item(Concept idConcept, String info) {
        this.info=info;
        this.idConcept = idConcept;
    }
    //region ---------GETTER & SETTER-------------
    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public int getIdConcept() {
        return idConcept.getIdConcept();
    }


    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    //endregion
}

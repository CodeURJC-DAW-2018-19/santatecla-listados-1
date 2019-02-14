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
    private Boolean state;

    @Column
    private String info;

    public Item(Concept idConcept, String info) {
        this.info=info;
        this.idConcept = idConcept;
    }

    public Item(){}


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
    //endregion

}

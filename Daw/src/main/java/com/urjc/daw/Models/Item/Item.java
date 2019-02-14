package com.urjc.daw.Models.Item;

import com.urjc.daw.Models.Concept.Concept;

import javax.persistence.*;

@Entity
public class Item {
/**             Atributos               **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idItem;

    @ManyToOne(cascade = CascadeType.ALL)
    private Concept idConcept;


    @Column
    private Boolean state;

    @Column
    private String info;
/*****************************************/


/**             Constructor             **/
    public Item( String info,boolean state) {
        this.info = info;
        this.state = state;
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



}

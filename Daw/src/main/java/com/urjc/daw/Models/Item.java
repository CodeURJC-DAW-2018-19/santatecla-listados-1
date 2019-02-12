package com.urjc.daw.Models;

import com.urjc.daw.Models.Concept;

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
}

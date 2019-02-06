package com.urjc.daw;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @Column (name = "ID_ITEM")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItem;


    @Column
    @JoinColumn(name = "ID_CONCEPT")
    private Concept idConcept;

    @Column(name = "STATE")
    private Boolean correct;

    public Item(Boolean correct) {
        this.correct = correct;
    }
}

package com.urjc.daw;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItem;

    private Integer idConcept;
    @Column
    private Boolean correct;

    public Item(Boolean correct) {
        this.correct = correct;
    }
}

package com.urjc.daw.Models;

import com.urjc.daw.Models.Concept;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ID_CONCEPT")
    private Concept idConcept;

    @Column
    private Boolean correct;

    public Item(Concept idConcept) {
        this.idConcept = idConcept;
    }
}

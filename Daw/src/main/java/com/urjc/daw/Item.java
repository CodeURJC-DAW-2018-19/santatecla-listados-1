package com.urjc.daw;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItem;

    @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn
    private Concept idConcept;

    @Column
    private Boolean correct;

    public Item(Concept idConcept) {
        this.idConcept = idConcept;
    }
}

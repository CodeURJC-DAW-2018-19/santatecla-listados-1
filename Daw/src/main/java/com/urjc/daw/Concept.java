package com.urjc.daw;

import javax.persistence.*;
import java.util.TreeMap;

@Entity
public class Concept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConcept;


    private Integer idLesson;

    @Column
    private String title;
    @Column
    private Integer conceptNumber;
    @Column
    private TreeMap<Integer, Item> itemTreeMap;

    public Concept(String title) {
        this.title = title;
    }
}

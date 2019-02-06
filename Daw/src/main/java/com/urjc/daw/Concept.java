package com.urjc.daw;

import javax.persistence.*;
import java.util.TreeMap;

@Entity
public class Concept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConcept;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "ID_LESSON")
    private Lesson idLesson;

    @Column
    private String title;
    @Column
    private Integer conceptNumber;
    @Column
    private TreeMap<Integer, Item> itemTreeMap;

    public Concept(Lesson idLesson, String title) {
        this.idLesson = idLesson;
        this.title = title;
    }
}

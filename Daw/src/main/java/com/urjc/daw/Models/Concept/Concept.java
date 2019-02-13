package com.urjc.daw.Models.Concept;

import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Lessons.Lesson;

import javax.persistence.*;
import java.util.TreeMap;


@Entity
public class Concept {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConcept;

    @ManyToOne(cascade = CascadeType.ALL)
    private Lesson idLesson;

    @Column
    private String title;

    @Column
    private Integer conceptNumber;

    @Column
    private TreeMap<Integer, Item> itemTreeMap;

    public Concept(String title, Integer conceptNumber) {
        this.title = title;
        this.conceptNumber = conceptNumber;
    }
}

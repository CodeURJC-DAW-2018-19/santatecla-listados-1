package com.urjc.daw;

import com.urjc.daw.Concept;

import javax.persistence.*;
import java.util.TreeMap;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLesson;

    @ManyToOne(cascade = CascadeType.ALL)
    private User idUser;

    @Column
    private String lessonName;

    @Column
    private String conceptNumber;

    private TreeMap<Integer, Concept> conceptTreeMap;

    public Lesson(User idUser, String lessonName) {
        this.idUser = idUser;
        this.lessonName = lessonName;
    }
}

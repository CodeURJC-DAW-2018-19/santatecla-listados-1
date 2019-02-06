package com.urjc.daw;

import com.urjc.daw.Concept;

import javax.persistence.*;
import java.util.TreeMap;

@Entity
public class Lesson {
    @Id
    @Column(name="ID_LESSON")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLesson;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER")
    private User idUser;

    @Column(name = "NAME")
    private String lessonName;

    @Column(name="NUMBER_OF_CONCEPTS")
    private String conceptNumber;

    private TreeMap<Integer, Concept> conceptTreeMap;

    public Lesson(User idUser, String lessonName) {
        this.idUser = idUser;
        this.lessonName = lessonName;
    }
}

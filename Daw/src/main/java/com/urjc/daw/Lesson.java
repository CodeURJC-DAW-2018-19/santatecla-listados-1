package com.urjc.daw;

import com.urjc.daw.Concept;

import javax.persistence.*;
import java.util.TreeMap;

@Entity
public class Lesson {
    @Id
    @Column(name = "ID_LESSON")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLesson;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "ID_TEACHER")
    private User idUser;

    @Column(name ="LESSON_NAME")
    private String lessonName;

    @Column(name= "CONCEPTS_NUMBER")
    private String conceptNumber;

    private TreeMap<Integer, Concept> conceptTreeMap;

    public Lesson(User idUser, String lessonName) {
        this.idUser = idUser;
        this.lessonName = lessonName;
    }
}

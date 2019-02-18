package com.urjc.daw.Models.Question;

import com.urjc.daw.Models.Answer.Answer;
import com.urjc.daw.Models.Concept.Concept;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Question {

/**         ATTRIBUTES      **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idQuestion;

    @Column
    int type;

    @Column
    private String info;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Answer> setAnswer;

    @ManyToOne
    private Concept idConcept;


/**         CONSTRUCTOR         **/
    public Question(){}

    public Question(int type, String info){
        this.info=info;
        this.type=type;

    }


/**         GETTER & SETTER         **/

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Set<Answer> getIdAnswer() {
        return setAnswer;
    }

    public void setIdAnswer(Set<Answer> idAnswer) {
        this.setAnswer = idAnswer;
    }

    public long getIdConcept() {
        return idConcept.getIdConcept();
    }

    public void setConcept(Concept idConcept) {
        this.idConcept = idConcept;
    }


    public void addAnswer(Answer answer){
        this.setAnswer.add(answer);
    }
}

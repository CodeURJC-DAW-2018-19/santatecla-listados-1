package com.urjc.daw.Models.Answer;

import com.urjc.daw.Models.Question.Question;
import com.urjc.daw.Models.User.User;

import javax.persistence.*;


@Entity
public class Answer {

/**         ATTRIBUTES          **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idAnswer;

    @Column
    private String info;

    @Column
    private String state;

    @ManyToOne
    private User idUser;

    @ManyToOne
    private Question idQuestion;

    @Column
    private boolean correct;
/**         CONSTRUCTOR         **/

    public Answer(String info){
        this.correct=false;
        this.state="pending";
        this.info=info;
    }

    public Answer(){}

/**         GETTER & SETTER         **/
    public long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(long idAnswer) {
        this.idAnswer = idAnswer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getIdUser() {
        return idUser.getId();
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public long getIdQuestion() {
        return idQuestion.getIdQuestion();
    }

    public void setQuestion(Question idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Override
    public String toString() {
        return idQuestion.getInfo()+ ':' + info ;
    }

    public void corregir() {
        this.correct = true;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

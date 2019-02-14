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

    @ManyToOne(cascade = CascadeType.ALL)
    private User idUser;

    @ManyToOne(cascade = CascadeType.ALL)
    private Question idQuestion;

/**         CONSTRUCTOR         **/

    public Answer(String info){
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
}

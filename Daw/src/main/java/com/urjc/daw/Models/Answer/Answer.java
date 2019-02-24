package com.urjc.daw.Models.Answer;

import com.urjc.daw.Models.Item.Item;
import com.urjc.daw.Models.Question.Question;
import com.urjc.daw.Models.User.User;

import javax.persistence.*;
import java.util.Set;


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

    public Question accesToQuestion(){return idQuestion;}

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

    public Question getQuestion(){
        return idQuestion;
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

    public boolean getCorregir(){return correct;}

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getQuestionInfo(){
        return this.idQuestion.getInfo();
    }


    public void correct(){
        this.correct=true;
        idQuestion.metrics();
    }


    public void correctType1(boolean ans){
        this.setCorrect(true);
        Set<Item> items = idQuestion.getConcept().getItemSet();
        Item itemQuestion = null;
        for (Item i: items) {
            if(idQuestion.getInfo().contains(i.getInfo())){
                itemQuestion=i;
                break;
            }
        }
        if(itemQuestion!=null && itemQuestion.getState() && ans){
            this.setState("right");
        }else{
            this.setState("wrong");
        }
    }

    public long getIdConcept(){
        return this.idQuestion.getIdConcept();
    }

    public boolean isCorrect(){
        if(this.state.equals("right")){
            return true;
        }else{
            return false;
        }
    }
}

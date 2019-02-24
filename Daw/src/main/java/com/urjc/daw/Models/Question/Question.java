package com.urjc.daw.Models.Question;

import com.urjc.daw.Models.Answer.Answer;
import com.urjc.daw.Models.Concept.Concept;
import com.urjc.daw.Models.Item.Item;

import javax.persistence.*;
import java.util.HashSet;
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

    @Column
    private int [] arrayRespuestas={0,0,0};

    @ManyToOne
    private Concept idConcept;

    @ManyToMany
    private Set<Item> setItem;



/**         CONSTRUCTOR         **/
    public Question(){}

    public Question(int type, String info){
        this.info=info;
        this.type=type;
        this.setAnswer=new HashSet<>();
        this.setItem= new HashSet<>();
        metrics();
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

    public int getAnswerCorrect(){
        return arrayRespuestas[0];
    }
    public int getAnswerIncorrect(){
        return arrayRespuestas[1];
    }
    public int getAnswerPending(){
        return arrayRespuestas[2];
    }

    public int getSizeQuestions(){
        return setAnswer.size();
    }

    public void addAnswer(Answer answer){
        this.setAnswer.add(answer);
        if(answer.getState().equals("right")){
            arrayRespuestas[0]++;
        }else if(answer.getState().equals("wrong")){
            arrayRespuestas[1]++;
        }else{
            arrayRespuestas[2]++;
        }
    }

    public void addItem(Item item){this.setItem.add(item);}

    public void metrics(){
        int rigth=0;
        int wrong=0;
        int pending=0;

        for (Answer ans:setAnswer) {
            if(ans.getState().equals("right")){
                rigth++;
            }else if(ans.getState().equals("wrong")){
                wrong++;
            }else{
                pending++;
            }
        }
        arrayRespuestas[0]=rigth;
        arrayRespuestas[1]=wrong;
        arrayRespuestas[2]=pending;
    }
}

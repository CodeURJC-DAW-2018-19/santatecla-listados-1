package com.urjc.daw.models.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.urjc.daw.models.answer.Answer;
import com.urjc.daw.models.concept.Concept;
import com.urjc.daw.models.item.Item;
import com.urjc.daw.models.lessons.Lesson;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Question {

    public interface BasicInfo{}
    public interface AnswerList{}
    public interface ConceptDet{}


/**         ATTRIBUTES      **/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(BasicInfo.class)
    private long idQuestion;

    @Column
    @JsonView(BasicInfo.class)
    int type;

    @Column
    @JsonView(BasicInfo.class)
    private String info;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonView(AnswerList.class)
    private Set<Answer> setAnswer;

    @Column
    private int [] arrayRespuestas={0,0,0};

    @ManyToOne
    @JsonView(ConceptDet.class)
    private Concept idConcept;

    @ManyToMany
    @JsonView(BasicInfo.class)
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

    public Concept getConcept(){
        return idConcept;
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

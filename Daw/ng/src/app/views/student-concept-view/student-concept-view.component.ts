import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material";
import {Question} from "../../model/question.model";
import {QuestionService} from "../../service/question-service";
import {Answer} from "../../model/answer.model";
import {AnswerService} from "../../service/answer-service";
import {ActivatedRoute} from "@angular/router";
import {LoginService} from "../../auth/login.service";
import { ConceptService } from 'src/app/service/concept-service';
import { Concept } from 'src/app/model/concept.model';
import {PageConcept} from "../../model/page.concept";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
    selector: 'app-student-concept-view',
    templateUrl: './student-concept-view.component.html',
    styleUrls: ['./student-concept-view.component.css']
})
export class StudentConceptViewComponent implements OnInit {

    public question: Question [];
    public answer: Answer [];
    public idConcept:number;
    single: any[];
    answerAdd: Answer;
    id: number;
    stats: number[][];
    concepts: Concept[];
    numberPag: number;
    pageConcept: PageConcept;
    conceptsTitle: string[];
    picture: string[];
    random: Question;
    optionsChoosen:string[];


    view: any[] = [700, 400];


    @ViewChild('diagramDialog') diagramDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    @ViewChild('questionDialog') questionDialog: TemplateRef<any>;
    questionRef: MatDialogRef<any, any>;
    concept: Concept;


    constructor(public dialog: MatDialog,
                public loginService: LoginService,
                public answerService: AnswerService,
                public activatedRoute: ActivatedRoute,
                public conceptService: ConceptService,
                public questionService: QuestionService) {
        this.answerAdd = {info: "", state: "pending", correct: false};
        this.random = {info:""};
        this.optionsChoosen = [];
    }

    ngOnInit() {
        this.idConcept = this.activatedRoute.snapshot.params['id'];
        this.answerService.getAnswerByUser(this.loginService.user.idUser,this.idConcept).subscribe(
            (res : any) =>{
                console.log(res);
                this.answer=res;
            },
            error => console.log(error)
        )

        this.conceptService.getConcepts(this.idConcept).subscribe(
            (res: any)=> {
                this.concept = res;
            },
            error2 => console.log(error2)
        )
        this.stats=[[],[],[]];
        this.conceptPagination();
        this.reloadConcepts();

    }

    createQuestion() {
        this.questionService.createQuestion(this.idConcept).subscribe(
            (res : any) =>{
                this.random=res;
            },
            error1 => console.log(error1)
        );
    }

    newAnswer() {
        //const id = this.activatedRoute.snapshot.params['id'];
        const id = this.random.idQuestion;
        this.answerAdd.statement=this.random.info;
        this.answer.push(this.answerAdd);
        if(this.random.type==3){
            let opt: string;
            let total: string;
            this.optionsChoosen.forEach((value,index) =>{
                if(index==0){
                    opt=value;
                }else{
                    opt = opt + 'sss' + value;
                }
            });
            this.random.opt.forEach(value => {
                if(opt == null){
                    total =value.info;
                }else{
                    total  = opt + "sss" + value.info;
                }
            });
            this.answerService.correctionSpecial(opt, total, id,).subscribe();

        }else{
            this.answerService.addAnswer(this.answerAdd, id).subscribe();
        }
        this.answerAdd = {info: "", state: "pending", correct: false};
        this.dialog.closeAll();
    }



    openDiagramDialog() {
        this.dialogRef = this.dialog.open(this.diagramDialog, {
            width: '50%',
            height: '50%',
        });
    }

    openQuestionDialog() {
        this.createQuestion();
        this.dialogRef = this.dialog.open(this.questionDialog, {
            width: '50%',
            height: '50%',
        });
    }

    conceptPagination(){
        this.conceptService.getConceptsByPage(0, this.numberPag).subscribe(
            (res: any) => {
                this.pageConcept = res;
                this.concepts = (this.pageConcept.content);
                this.conceptsTitle = [];
                this.concepts.forEach((value, index) => {
                        this.conceptsTitle.push(value.title);
                        this.picture.push(value.picture);
                        this.stats[0].push(value.answerCorrect);
                        this.stats[1].push(value.answerPending);
                        this.stats[2].push(value.answerIncorrect);
                    }
                );
            },
            error => console.log(error)
        );
        console.log(this.stats);
    }

    reloadConcepts(){
        this.numberPag++;
        this.conceptService.getConceptsByPage(10, this.numberPag).subscribe(
            (res: any) => {
                this.pageConcept = res;
                this.pageConcept.content.forEach((value, index) =>
                    this.concepts.push(value));

                this.conceptsTitle = [];
                this.concepts.forEach(value => this.conceptsTitle.push(value.title));
                console.log(this.concepts);
            },
            error => console.log(error)
        );
    }


}

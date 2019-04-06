import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {MatDialog, MatDialogRef} from "@angular/material";
import { single } from './data-student-diagram';
import {Question} from "../../model/question.model";
import {QuestionService} from "../../service/question-service";
import {Answer} from "../../model/answer.model";
import {AnswerService} from "../../service/answer-service";
import {ActivatedRoute} from "@angular/router";
import {LoginService} from "../../auth/login.service";
import { ConceptService } from 'src/app/service/concept-service';
import { Concept } from 'src/app/model/concept.model';
import {PageConcept} from "../../model/page.concept";

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

    view: any[] = [700, 400];

    // options
    showXAxis = true;
    showYAxis = true;
    gradient = false;
    showLegend = true;
    showXAxisLabel = true;
    xAxisLabel = 'Concepto';
    showYAxisLabel = true;

    colorScheme = {
        domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
    };

    @ViewChild('diagramDialog') diagramDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    @ViewChild('questionDialog') questionDialog: TemplateRef<any>;
    questionRef: MatDialogRef<any, any>;
    concept: Concept;


    constructor(public dialog: MatDialog,
                public loginService: LoginService,
                public answerService: AnswerService,
                public activatedRoute: ActivatedRoute,
                public conceptService: ConceptService) {
        Object.assign(this, { single });
        this.answerAdd = {info: "", state: "pending", correct: false};
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

    }

    newAnswer() {
        const id = this.activatedRoute.snapshot.params['id'];
        this.answerService.addAnswer(this.answerAdd, id).subscribe();
        this.answer.push(this.answerAdd);
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
                        this.conceptsTitle.push(value.title)
                        this.picture.push(value.picture)
                        this.stats[0].push(value.answerCorrect);
                        this.stats[1].push(value.answerPending);
                        this.stats[2].push(value.answerIncorrect);
                    }
                );
            },
            error => console.log(error)
        );
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

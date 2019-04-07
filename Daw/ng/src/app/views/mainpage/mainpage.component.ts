import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {LessonService} from '../../service/lesson-service';
import {LoginService} from "../../auth/login.service";
import {Lesson} from "../../model/lesson.model";
import {PageLesson} from "../../model/page.lesson";
import {Router} from "@angular/router";
import {MatDialog, MatDialogRef} from "@angular/material";
import {ConceptService} from "../../service/concept-service";
import {ItemService} from "../../service/item-service";
import {PageConcept} from "../../model/page.concept";
import {Concept} from "../../model/concept.model";
import {Question} from "../../model/question.model";
import {TdDialogService} from "@covalent/core";

@Component({
    selector: 'app-mainpage',
    templateUrl: 'mainpage.component.html',
    styleUrls: ['mainpage.component.css']
})

export class MainpageComponent implements OnInit {
    pageLesson: PageLesson;
    lessons: Lesson[];
    lessonsSearch: Lesson[];
    stats: number[][];
    lessonAdd: Lesson;
    lessonsTitle: string[];
    numberPag: number;
    id: number;
    idLessonACrear: number;

    pageConcept: PageConcept;
    concepts: Concept[];
    conceptAdd: Concept;
    picture: string[];
    question: Question;

    questionEmpty: Question;

    @ViewChild('addLessonDialog') addLessonDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    @ViewChild('DiagramDialog') DiagramDialog: TemplateRef<any>;
    DiagramRef: MatDialogRef<any, any>;

    @ViewChild('addConceptDialog') addConceptDialog: TemplateRef<any>;
    dialogRefConcept: MatDialogRef<any, any>;


    constructor(public dialog: MatDialog, public dialog2: MatDialog, private router: Router, private lessonService: LessonService, public loginService: LoginService, private conceptService: ConceptService, private itemService: ItemService, private _dialogService: TdDialogService) {
    }

    ngOnInit() {
        this.numberPag = 0;
        this.stats = [[], [], []];
        this.lessonsSearch = [];
        this.conceptAdd={title: '', picture: '', setQuestion: this.question , answerIncorrect: 0, answerCorrect: 0,answerPending: 0};
        this.lessonPagination();
    }


    openAddLessonDialog() {
        this.dialogRef = this.dialog.open(this.addLessonDialog, {
            width: '50%',
            height: '50%',
        });
    }

    openAddConceptDialog(id:number) {
        this.idLessonACrear=id;
        this.dialogRefConcept = this.dialog.open(this.addConceptDialog, {
            width: '50%',
            height: '50%',
        });
    }

    openDiagramDialog() {
        this.DiagramRef = this.dialog2.open(this.DiagramDialog, {
            width: '75%',
            height: '75%',
        });
    }

    register(form) {
        console.log(form.value);
    }

    newLesson() {
        this.lessonService.saveLesson(this.lessonAdd).subscribe(
            (res: any) => {
                this.pageLesson = res;
                this.lessons = (this.pageLesson.content);
                console.log(this.lessons);
            },
            error => console.log(error)
        );
        this.dialog.closeAll();
        this.lessonPagination();

    }

    lessonPagination() {
        this.lessonAdd = {title: '', conceptSet: [], answerPending: 0, answerIncorrect: 0, answerCorrect: 0}
        this.lessonService.getLessonsByPage(0, this.numberPag).subscribe(
            (res: any) => {
                this.pageLesson = res;
                this.lessons = (this.pageLesson.content);
                this.lessonsTitle = [];
                this.lessons.forEach((value, index) => {
                        this.lessonsTitle.push(value.title)
                        this.stats[0].push(value.answerCorrect);
                        this.stats[1].push(value.answerPending);
                        this.stats[2].push(value.answerIncorrect);
                    }
                );
            },
            error => console.log(error)
        );
    }

    reloadLessons() {
        this.numberPag++;
        this.lessonService.getLessonsByPage(10, this.numberPag).subscribe(
            (res: any) => {
                this.pageLesson = res;
                this.pageLesson.content.forEach((value, index) =>
                    this.lessons.push(value));

                this.lessonsTitle = [];
                this.lessons.forEach(value => this.lessonsTitle.push(value.title));
                console.log(this.lessons);
            },
            error => console.log(error)
        );
    }

    findLesson(title: string) {
        this.lessonsSearch = [];
        if (title == "") {
            this.lessonPagination();
        } else {
            this.lessons.forEach((value) => {
                if (value.title.indexOf(title) != -1) {
                    this.lessonsSearch.push(value);
                }
            });
            if (this.lessonsSearch.length > 0)
                this.lessons = this.lessonsSearch;
        }

    }

    newConcept() {
        this.conceptService.addConcepts(this.conceptAdd, this.idLessonACrear).subscribe(
            (res: any) => {
                this.pageConcept = res;
                this.concepts = (this.pageConcept.content);
                console.log(this.concepts);
            },
            error1 => console.log(error1)
        );
        this.lessonPagination();
    }


    deleteLesson(id: number) {
        this._dialogService.openConfirm({
            message: '¿Estás seguro de que desea eliminarlo?',
            title: 'Confirmarción',
            width: '500px',
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.lessonService.deleteLesson(id).subscribe();
                let i = 0;
                this.lessons.forEach((value, index) => {
                    if (value.id == id) {
                        i = index;
                    }
                });
                this.lessons.splice(i, 1);
            }
        });
    }

    deleteConcept(id: number) {
        this._dialogService.openConfirm({
            message: '¿Estás seguro de que desea eliminarlo?',
            title: 'Confirmarción',
            width: '500px',
            height: '175px'
        }).afterClosed().subscribe((accept: boolean) => {
            if (accept) {
                this.conceptService.deleteConcepts(id).subscribe();
                let i = 0;
                this.lessons.forEach((value, index) => {
                    value.conceptSet.forEach((c, index) => {
                        if (c.idConcept == id) {
                            i = index;
                            value.conceptSet.splice(i, 1);
                        }
                    });
                });
            }
        });
    }
}

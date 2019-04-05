import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {LessonService} from '../../service/lesson-service';
import {LoginService} from "../../auth/login.service";
import {Lesson} from "../../model/lesson.model";
import {PageLesson} from "../../model/page.lesson";
import {Router} from "@angular/router";
import {MatDialog, MatDialogRef} from "@angular/material";
import {single} from './data';
import {multi} from './data';
import {ConceptService} from "../../service/concept-service";
import {ItemService} from "../../service/item-service";
import {PageConcept} from "../../model/page.concept";
import {Concept} from "../../model/concept.model";
import {PageItems} from "../../model/page.item";
import {Item} from "../../model/item.model";
import {Question} from "../../model/question.model";

@Component({
    selector: 'app-mainpage',
    templateUrl: 'mainpage.component.html',
    styleUrls: ['mainpage.component.css']
})

export class MainpageComponent implements OnInit {
    pageLesson: PageLesson;
    lessons: Lesson[];
    stats: number[][];
    lessonAdd: Lesson;
    lessonsTitle: string[];
    numberPag: number;
    id: number;

    pageConcept: PageConcept;
    concepts: Concept[];
    conceptAdd: Concept;
    conceptsTitle: string[];
    picture: string[];
    question: Question;

    pageItem: PageItems;
    items: Item[];
    itemAdd: Item;
    itemsInfo: string[];
    state: boolean;


    @ViewChild('addLessonDialog') addLessonDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    @ViewChild('DiagramDialog') DiagramDialog: TemplateRef<any>;
    DiagramRef: MatDialogRef<any, any>;

    @ViewChild('addConceptDialog') addConceptDialog: TemplateRef<any>;
    dialogRefConcept: MatDialogRef<any, any>;

    @ViewChild('addItemDialog') addItemDialog: TemplateRef<any>;
    dialogRefItem: MatDialogRef<any, any>;



    constructor(public dialog: MatDialog, public dialog2: MatDialog, private router: Router, private lessonService: LessonService, public loginService: LoginService, private conceptService: ConceptService, private itemService: ItemService) {
        Object.assign(this, {single})
    }

    ngOnInit() {
        this.numberPag = 0;
        this.stats=[[],[],[]];
        this.lessonPagination();
        this.conceptPagination();
        this.itemPagination();


        //console.log(this.lessons);

    }

    /*reloadPagination() {
        this.numberPag++;
        this.reloadLessons();
        this.reloadConcepts();
        this.reloadItems();
    }*/

    openAddLessonDialog() {
        this.dialogRef = this.dialog.open(this.addLessonDialog, {
            width: '50%',
            height: '50%',
        });
    }

    openAddConceptDialog(){
        this.dialogRefConcept = this.dialog.open(this.addConceptDialog, {
            width: '50%',
            height: '50%',
        });
    }

    openAddItemDialog() {
        this.dialogRefItem = this.dialog.open(this.addItemDialog, {
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

    }

    lessonPagination(){
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

    reloadLessons(){
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

    newConcept() {
        this.conceptService.addConcepts(this.conceptAdd, this.id).subscribe(
            (res: any) => {
                this.pageConcept = res;
                this.concepts = (this.pageConcept.content);
                console.log(this.concepts);
            },
            error1 => console.log(error1)
        );
    }

    conceptPagination(){
        this.conceptAdd = {title: '', setQuestion: this.question, picture: ''}
        this.conceptService.getConceptsByPage(0, this.numberPag).subscribe(
            (res: any) => {
                this.pageConcept = res;
                this.concepts = (this.pageConcept.content);
                this.conceptsTitle = [];
                this.concepts.forEach((value, index) => {
                        this.conceptsTitle.push(value.title)
                        this.picture.push(value.picture)
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

    newItem() {
        this.itemService.addItem(this.itemAdd, this.id).subscribe(
            (res: any) => {
                this.pageItem = res;
                this.items = (this.pageItem.content);
                console.log(this.items);
            },
            error1 => console.log(error1)
        );
    }

    itemPagination(){
        this.itemAdd = {info: '', state: this.state}
        this.itemService.getItemsByPage(0, this.numberPag).subscribe(
            (res: any) => {
                this.pageItem = res;
                this.items = (this.pageItem.content);
                this.itemsInfo = [];
                this.items.forEach((value, index) => {
                        this.itemsInfo.push(value.info)
                        this.state = true;
                    }
                );
            },
            error => console.log(error)
        );
    }

    reloadItems(){
        this.numberPag++;
        this.itemService.getItemsByPage(10, this.numberPag).subscribe(
            (res: any) => {
                this.pageItem = res;
                this.pageItem.content.forEach((value, index) =>
                    this.items.push(value));

                this.itemsInfo = [];
                this.items.forEach(value => this.itemsInfo.push(value.info));
                console.log(this.items);
            },
            error => console.log(error)
        );
    }
}

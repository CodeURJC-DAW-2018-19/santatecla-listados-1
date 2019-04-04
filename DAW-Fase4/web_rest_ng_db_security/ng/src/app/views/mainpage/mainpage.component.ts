import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {LessonService} from '../../service/lesson-service';
import {LoginService} from "../../auth/login.service";
import {Lesson} from "../../model/lesson.model";
import {PageLesson} from "../../model/PageLesson";
import {Router} from "@angular/router";
import {MatDialog, MatDialogRef} from "@angular/material";
import {single} from './data';
import {multi} from './data';

@Component({
    selector: 'app-mainpage',
    templateUrl: 'mainpage.component.html',
    styleUrls: ['mainpage.component.css']
})

export class MainpageComponent implements OnInit {
    page: PageLesson;
    lessons: Lesson[];
    stats: number[][];
    lessonAdd: Lesson;
    lessonsTitle: string[];
    numberPag: number;


    @ViewChild('addLessonDialog') addLessonDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    @ViewChild('DiagramDialog') DiagramDialog: TemplateRef<any>;
    DiagramRef: MatDialogRef<any, any>;

    constructor(public dialog: MatDialog, public dialog2: MatDialog, private router: Router, private lessonService: LessonService, public loginService: LoginService) {
        Object.assign(this, {single})
    }

    ngOnInit() {
        this.numberPag = 0;
        this.stats=[[],[],[]];
        this.lessonAdd = {title: '', conceptSet: [], answerPending: 0, answerIncorrect: 0, answerCorrect: 0}
        this.lessonService.getLessons(0, this.numberPag).subscribe(
            (res: any) => {
                this.page = res;
                this.lessons = (this.page.content);
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

        //console.log(this.lessons);

    }

    reloadPagination() {
        this.numberPag++;
        this.lessonService.getLessons(10, this.numberPag).subscribe(
            (res: any) => {
                this.page = res;
                this.page.content.forEach((value, index) =>
                    this.lessons.push(value));

                this.lessonsTitle = [];
                this.lessons.forEach(value => this.lessonsTitle.push(value.title));
                console.log(this.lessons);
            },
            error => console.log(error)
        );
    }

    openAddLessonDialog() {
        this.dialogRef = this.dialog.open(this.addLessonDialog, {
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
                this.page = res;
                this.lessons = (this.page.content);
                console.log(this.lessons);
            },
            error => console.log(error)
        );

    }


    newConcept() {
        this.router.navigate(['/concept/new']);
    }
}

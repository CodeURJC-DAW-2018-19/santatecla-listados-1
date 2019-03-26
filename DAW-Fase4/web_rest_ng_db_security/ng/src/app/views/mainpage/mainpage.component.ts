import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {LessonService} from '../../service/lesson-service';
import {LoginService} from "../../auth/login.service";
import {Lesson} from "../../model/lesson.model";
import {PageLesson} from "../../model/PageLesson";
import {Router} from "@angular/router";
import {MatDialog, MatDialogRef} from "@angular/material";

@Component({
    selector: 'app-mainpage',
    templateUrl: 'mainpage.component.html',
    styleUrls: ['mainpage.component.css']
})
export class MainpageComponent implements OnInit {
    page: PageLesson;
    lessons: Lesson[];
    lessonAdd: Lesson;

    @ViewChild('addLessonDialog') addLessonDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    constructor(public dialog: MatDialog,private router: Router,private lessonService: LessonService, public loginService: LoginService) {
    }

    ngOnInit() {
        this.lessonService.getLessons().subscribe(
            (res : any)=>{
                this.page=res;
                this.lessons=(this.page.content);
                console.log(this.lessons);
            },
            error => console.log(error)
        );
        //console.log(this.lessons);

    }


    openAddLessonDialog() {
        this.dialogRef = this.dialog.open(this.addLessonDialog, {
            width: '50%',
            height: '50%',
        });
    }

    register(form) {
        console.log(form.value);
    }
    newLesson(lesson:Lesson) {
        console.log(lesson);
        this.lessonService.saveLesson(this.lessonAdd).subscribe(
            (res : any)=>{
                this.page=res;
                this.lessons=(this.page.content);
                console.log(this.lessons);
            },
            error => console.log(error)
        );
    }

    newConcept() {
        this.router.navigate(['/concept/new']);
    }
}

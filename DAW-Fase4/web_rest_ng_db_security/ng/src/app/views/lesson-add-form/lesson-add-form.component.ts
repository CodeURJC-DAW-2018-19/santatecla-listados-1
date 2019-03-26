import {Component, OnInit} from '@angular/core';
import {Lesson} from "../../model/lesson.model";
import {ActivatedRoute, Router} from "@angular/router";
import {BookService} from "../../book.service";
import {LoginService} from "../../auth/login.service";
import {LessonService} from "../../service/lesson-service";
@Component({
    template: `
        <div>
            <h2>New lesson</h2>
            <div *ngIf="lesson.id">
                <label>Id: </label>{{lesson.id}}
            </div>
            <div>
                <label>Title: </label>
                <input [(ngModel)]="lesson.title" placeholder="title"/>
            </div>
            <p>
                <button (click)="cancel()">Cancel</button>
                <button (click)="save()">Save</button>
            </p>
        </div>`
})
export class LessonAddFormComponent implements OnInit {
    lesson: Lesson;

    constructor(private router: Router, activatedRoute: ActivatedRoute, public service: LessonService,
                public loginService: LoginService) {
        const id = activatedRoute.snapshot.params['id'];
        service.getBook(id).subscribe(
            (res : any)=>{
                this.lesson=res;
                console.log(this.lesson);
            },
            error => console.error(error)
        );
    }

    ngOnInit() {
    }

    cancel() {
        window.history.back();
    }

    save() {
        this.service.saveLesson(this.lesson).subscribe(
            lesson => { },
            error => console.error('Error creating new lesson: ' + error)
        );
        window.history.back();
    }

}

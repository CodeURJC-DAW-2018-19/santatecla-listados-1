import {Component, OnInit} from '@angular/core';
import {LessonService} from '../service/lesson-service';
import {Book} from "../book.service";
import {LoginService} from "../auth/login.service";
import {Lesson} from "../model/lesson.model";
import {PageLesson} from "../model/PageLesson";

@Component({
    selector: 'app-mainpage',
    templateUrl: 'mainpage.component.html',
    styleUrls: ['mainpage.component.css']
})
export class MainpageComponent implements OnInit {
    page: PageLesson;
    lessons: Lesson[];

    constructor(private lessonService: LessonService, public loginService: LoginService) {
    }

    ngOnInit() {
        this.lessonService.getLessons().subscribe(
            page => this.page=page,
            error => console.log(error)
        );
       this.lessons=this.page.content;
       console.log(this.page)

    }

    newLesson() {

    }
}

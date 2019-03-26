import {Component, OnInit} from '@angular/core';
import {Lesson, LessonService} from '../service/lesson-service';
import {Book} from "../book.service";
import {LoginService} from "../login/login.service";

@Component({
    selector: 'app-mainpage',
    templateUrl: './mainpage.component.html',
    styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

    lessons: Lesson[];

    constructor(private lessonService: LessonService, public loginService: LoginService) {
    }

    ngOnInit() {
        this.lessonService.getLessons().subscribe(
            books => this.lessons = books,
            error => console.log(error)
        );
    }

    newLesson() {

    }
}

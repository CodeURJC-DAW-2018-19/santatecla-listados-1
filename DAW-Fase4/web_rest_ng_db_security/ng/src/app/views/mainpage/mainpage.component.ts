import {Component, OnInit} from '@angular/core';
import {LessonService} from '../../service/lesson-service';
import {Book} from "../../book.service";
import {LoginService} from "../../auth/login.service";
import {Lesson} from "../../model/lesson.model";
import {PageLesson} from "../../model/PageLesson";
import {Router} from "@angular/router";

@Component({
    selector: 'app-mainpage',
    templateUrl: 'mainpage.component.html',
    styleUrls: ['mainpage.component.css']
})
export class MainpageComponent implements OnInit {
    page: PageLesson;
    lessons: Lesson[];

    constructor(private router: Router,private lessonService: LessonService, public loginService: LoginService) {
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

    newLesson() {
        this.router.navigate(['/lesson/new']);
    }

    newConcept() {
        this.router.navigate(['/concept/new']);
    }
}

import {Component, OnInit} from '@angular/core';
import {LessonService} from '../service/lesson-service';
import {Lesson} from '../lesson/lesson.service';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  constructor(private lessonService: LessonService) {
  }


  lessons: Lesson[];
  ngOnInit() {


    this.lessonService.getLessons().subscribe(response => {
      this.lessons = [];
      response.forEach(element => {
        this.lessons.push(element);
      });
      console.log(this.lessons);
    });
  }

}

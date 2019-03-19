import {Component, OnInit} from '@angular/core';
import {LessonService} from '../service/lesson-service';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  constructor(private lessonService: LessonService) {

  }

  ngOnInit() {
    this.lessonService.getLesson().subscribe(result => {
      console.log(result.title);
    });
  }

}

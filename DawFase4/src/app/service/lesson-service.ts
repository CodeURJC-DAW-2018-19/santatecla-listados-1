import { Injectable } from '@angular/core';
import {Lesson} from "../model/lesson.model";


@Injectable()
export class LessonService {

  private lessons = [
    new Lesson(11, "Angular"),
    new Lesson(12, "TypeScript"),
    new Lesson(13, "Router"),
    new Lesson(14, "API REST"),
     ];

  getLessons() {
    return this.lessons;
  }

  getLesson(id: number | string) {
    return this.lessons.find(lesson => lesson.id === +id);
  }
}

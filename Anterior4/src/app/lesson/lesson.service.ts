import { Injectable } from '@angular/core';

export class Lesson {
  constructor(public id: number, public title: string) { }
}

@Injectable()
export class LessonService {

  private lessons = [
    new Lesson(11, "Docker"),
    new Lesson(12, "Angular"),
    new Lesson(13, "API REST")
  ];

  getLessons() {
    return this.lessons;
  }

  getLesson(id: number | string) {
    return this.lessons.find(lesson => lesson.id === +id);
  }
}

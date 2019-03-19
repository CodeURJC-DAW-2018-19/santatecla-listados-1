import {Lesson} from "../lesson/lesson.service";

export class Concept{

  constructor(public id:number, public title:string, public idLesson: Lesson) { }

}

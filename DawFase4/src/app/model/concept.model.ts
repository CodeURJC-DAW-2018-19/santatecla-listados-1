import {Lesson} from "./lesson.model";

export class Concept{

  constructor(public id:number, public title:string, public idLesson: Lesson) { }

}

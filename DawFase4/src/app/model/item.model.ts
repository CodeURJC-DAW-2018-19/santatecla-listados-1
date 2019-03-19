import {Lesson} from "./lesson.model";

export class Item{

  constructor(public id:number, public title:string, public idLesson: Lesson) { }

}

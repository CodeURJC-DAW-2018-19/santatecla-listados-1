import {Concept} from "./concept.model";
export class Lesson {
    constructor(public id:number, public title:string, public concept:Concept[]) { }
}

import { Concept } from "./concept.model";

export class Item{

  constructor(public id:number, public title:string, public idConcept: Concept) { }

}

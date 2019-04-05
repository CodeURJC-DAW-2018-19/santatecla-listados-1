import {Question} from "./question.model";

export interface Concept{
   idConcept?:number;
   title:string;
   picture:string;
   setQuestion:Question;

}

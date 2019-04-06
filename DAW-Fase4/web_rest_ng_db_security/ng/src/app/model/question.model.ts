import {Answer} from "./answer.model";
import {Item} from "./item.model";
import {Concept} from "./concept.model";

export interface Question{

    idQuestion?:number;
    idConcept?:Concept;
    info:string;
    type?:number;
    answerSet?: Answer[];
    arrayRespuestas?: number[];
    setItem?: Item[];
    opt?: Item[];

}

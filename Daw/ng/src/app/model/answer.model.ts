
import {UserRest} from "./user.model";
import {Question} from "./question.model";

export interface Answer{

    id?:number;
    info:string;
    idUser?: UserRest ;
    state: string;
    idQuestion?: Question;
    correct: boolean;
    statement?: string;

}

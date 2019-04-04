import {Answer} from "./answer.model";

export interface Question{

    id?:number;
    info:string;
    answerSet: Answer[];

}

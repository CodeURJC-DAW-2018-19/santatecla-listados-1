import {Answer} from "./answer.model";

export interface UserRest {

    id?: number,
    name:string,
    email:string ,
    password:string,
    role?:string,
    setAnswer?: Answer[],

}

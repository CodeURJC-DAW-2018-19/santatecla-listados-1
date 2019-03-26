import {Concept} from "./concept.model";

export interface Lesson {
    id?: number;
    title: string;
    conceptSet:Concept[];

}

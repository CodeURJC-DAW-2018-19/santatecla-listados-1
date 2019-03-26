import { Injectable } from '@angular/core';
import {Lesson} from "../model/lesson.model";
import {HttpClient,HttpHeaders} from '@angular/common/http';

const URL="https://localhost:8443/api/";
const GET_LESSONS = URL+"lesson/pag"

@Injectable()
export class LessonService {

    constructor(private http: HttpClient) {}


    getLessons() {
        return this.http.get<Lesson[]>(GET_LESSONS,{params:{size: '10',page :'0'}});
    }
}

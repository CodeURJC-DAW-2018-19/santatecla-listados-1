import {Injectable} from '@angular/core';
import {Lesson} from "../model/lesson.model";
import {catchError, map} from "rxjs/operators";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";

import { environment } from "../../environments/environment";


const BASE_URL = environment.baseUrl;
const GET_LESSONS = BASE_URL +"/lesson/pag";
const GET_LESSON = BASE_URL +"/lesson/";
const DELETE_LESSONS = BASE_URL +"/lesson/";
const CREATE_LESSONS = BASE_URL +"/lesson/";

@Injectable()
export class LessonService {

    constructor(private http: HttpClient) {
    }

    getLessonsByPage(pagSize:number,indexPag:number) {
        return this.http.get(GET_LESSONS+"?page="+indexPag+"&size="+pagSize, {withCredentials: true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }

    saveLesson(lesson: Lesson):Observable<Lesson> {
        const body = JSON.stringify(lesson);

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
        });

        return this.http
                .post<Lesson>(CREATE_LESSONS, body, { headers })
                .pipe(catchError((error) => this.handleError(error)));
    }



    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

    public getLesson(id: number) {
        return this.http.get(GET_LESSON + id, {withCredentials: true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );

    }

    deleteLesson(id:number){
        return this.http.delete<Lesson>(DELETE_LESSONS +  id)
            .pipe(
                catchError(err => this.handleError(err))
            );
    }
}

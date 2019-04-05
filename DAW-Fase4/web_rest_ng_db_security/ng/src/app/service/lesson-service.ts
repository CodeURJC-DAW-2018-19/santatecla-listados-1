import {Injectable} from '@angular/core';
import {Lesson} from "../model/lesson.model";
import {catchError, map} from "rxjs/operators";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Observable} from "rxjs";
import {PageLesson} from "../model/page.lesson";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Book} from "../book.service";
import {Item} from "../model/item.model";

const GET_LESSONS = "api/lesson/pag";
const GET_LESSON = "api/lesson/";
const DELETE_LESSONS = "api/lesson/";
const CREATE_LESSONS = "api/lesson/";
const URL_BASE="api/lesson/"

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
        return this.http.delete<Lesson>(URL_BASE +  id)
            .pipe(
                catchError(err => this.handleError(err))
            );
    }
}

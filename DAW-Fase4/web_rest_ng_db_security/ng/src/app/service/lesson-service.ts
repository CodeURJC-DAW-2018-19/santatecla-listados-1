import {Injectable} from '@angular/core';
import {Lesson} from "../model/lesson.model";
import {catchError, map} from "rxjs/operators";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Observable} from "rxjs";
import {PageLesson} from "../model/PageLesson";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Book} from "../book.service";

const GET_LESSONS = "api/lesson/pag";
const GET_LESSON = "api/lesson/";
const DELETE_LESSONS = "api/lesson/";
const CREATE_LESSONS = "api/lesson/";


@Injectable()
export class LessonService {

    constructor(private http: HttpClient) {
    }

    getLessons() {
        return this.http.get(GET_LESSONS, {withCredentials: true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }

    saveLesson(lesson: Lesson) {
        const body = JSON.stringify(lesson);
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': 'my-auth-token'
            })
        };
        return this.http.post(CREATE_LESSONS, body, httpOptions)
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );

    }


    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

    public getBook(id: number) {
        return this.http.get(GET_LESSON+"/"+id, {withCredentials: true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );

    }
}

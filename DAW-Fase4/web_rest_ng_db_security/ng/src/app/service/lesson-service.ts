import { Injectable } from '@angular/core';
import {Lesson} from "../model/lesson.model";
import {catchError, map} from "rxjs/operators";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Observable} from "rxjs";
import {PageLesson} from "../model/PageLesson";
import {HttpClient} from "@angular/common/http";

const GET_LESSONS = "api/lesson/pag"
const DELETE_LESSONS = "api/lesson/"


@Injectable()
export class LessonService {

    constructor(private http: HttpClient) {}

    getLessons() {
        return this.http.get(GET_LESSONS, { withCredentials: true })
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }


    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

}

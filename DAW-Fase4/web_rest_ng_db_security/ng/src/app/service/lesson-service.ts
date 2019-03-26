import { Injectable } from '@angular/core';
import {Lesson} from "../model/lesson.model";
import {catchError, map} from "rxjs/operators";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Concept} from "../model/concept.model";
import {Observable} from "rxjs";

const GET_LESSONS = "lesson/pag"
const DELETE_LESSONS = "lesson/"

export interface Lesson {
    id?: number;
    title: string;
    concepts: Concept[];
}

@Injectable()
export class LessonService {

    constructor(private http: Http) {}

    getLessons() {
        return this.http.get(GET_LESSONS, { withCredentials: true, params:{size: '10',page :'0'} })
            .pipe(
                map(response => response.json()),
                catchError(error => this.handleError(error))
            );
    }

    removeLesson(lesson: Lesson) {

        const headers = new Headers({
            'X-Requested-With': 'XMLHttpRequest'
        });
        const options = new RequestOptions({ withCredentials: true, headers });

        return this.http.delete(DELETE_LESSONS +"/"+lesson.id, options)
            .pipe(
                map(response => undefined),
                catchError(error => this.handleError(error))
            );
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

}

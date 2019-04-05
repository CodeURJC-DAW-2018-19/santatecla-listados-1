import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {Observable} from "rxjs";
import {Item} from "../model/item.model";
import {Question} from "../model/question.model";
import {Concept} from "../model/concept.model";
import {Answer} from "../model/answer.model";

const GET_QUESTION = "api/question/";
const CREATE_QUESTION = "api/question/concept"


@Injectable()
export class QuestionService {

    constructor(private http: HttpClient) {
    }

    getQuestions() {
        return this.http.get(GET_QUESTION, {withCredentials: true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

    createQuestion(question: Question, idConcept: Concept): Observable<Question> {
        const body = JSON.stringify(question);

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
        });
        return this.http.post<Question>(CREATE_QUESTION + idConcept, body, {headers})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );

    }

}

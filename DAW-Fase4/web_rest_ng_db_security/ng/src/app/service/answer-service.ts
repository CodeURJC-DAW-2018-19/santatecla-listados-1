import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {Observable} from "rxjs";

const GET_ANSWER = "api/answer/";
const GET_ANSWER_BY_CONCEPT = "api/answer/concept/";


@Injectable()
export class AnswerService {

    constructor(private http: HttpClient) {
    }

    getAnswer() {
        return this.http.get(GET_ANSWER, {withCredentials: true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

    private isCorrect(){

    }

    getAnswersByConcept(id: number){
        return this.http.get(GET_ANSWER_BY_CONCEPT + id, {withCredentials:true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }
}


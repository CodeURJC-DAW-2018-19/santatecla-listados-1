import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {Observable} from "rxjs";

const GET_QUESTION = "api/question/";


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
}

import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {Observable} from "rxjs";
import {UserService} from "./user-service";
import {LoginService} from "../auth/login.service";
import {Answer} from "../model/answer.model";
import {Concept} from "../model/concept.model";

import { environment } from "../../environments/environment";



const BASE_URL=environment.baseUrl;
const GET_ANSWER_BY_USER=BASE_URL + "/answer/user";
const GET_ANSWER = BASE_URL + "/answer/user/";
const GET_ANSWER_BY_CONCEPT = BASE_URL + "/answer/concept/";
const ADD_ANSWER =  BASE_URL + "answer/";


@Injectable()
export class AnswerService {


    constructor(private http: HttpClient) {
    }

    getAnswer(id:number) {
        return this.http.get(GET_ANSWER_BY_CONCEPT + id, {withCredentials: true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }

    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }

    getAnswersByConcept(id: number){
        return this.http.get(GET_ANSWER_BY_CONCEPT + id, {withCredentials:true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }

    correctManually(id: number, info:boolean){

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
        });

        return this.http.put<any>(GET_ANSWER + id + "/" + info,{headers})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }

    getAnswerByUser(idUser:number,idConcept:number){
        return this.http.get(GET_ANSWER_BY_USER+"/"+idUser+"/"+idConcept, {withCredentials:true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }

    addAnswer(answer: Answer, id:number):Observable<Answer> {
        const body = JSON.stringify(answer);

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
        });

        return this.http.post<Answer>(ADD_ANSWER + id, body, {headers})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );

    }
}


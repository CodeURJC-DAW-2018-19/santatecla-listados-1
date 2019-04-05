import { Injectable } from '@angular/core';
import { Concept } from "../model/concept.model";
import {catchError, map} from "rxjs/operators";
import {Observable} from "rxjs";
import {Item} from "../model/item.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";

const GET_CONCEPTS = "api/concept/pag";
const GET_CONCEPT = "api/concept/";
const DELETE_CONCEPTS = "api/concept/";
const CREATE_CONCEPTS = "api/concept/";
const URL_BASE="api/concept/"

@Injectable()
export class ConceptService {

    constructor(private http: HttpClient) {}

    getConceptsByPage(pagSize:number,indexPag:number) {
        return this.http.get(GET_CONCEPTS+"?page="+indexPag+"&size="+pagSize, {withCredentials: true})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }

    getConcepts(id: number | string) {
        return this.http.get(GET_CONCEPT + id , { withCredentials: true })
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }


    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }


    addConcepts(concept: Concept, id:number):Observable<Concept> {
        const body = JSON.stringify(concept);

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
        });
        return this.http.post<Concept>(GET_CONCEPTS + id, body, {headers})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );

    }

    deleteConcepts(id:number){
        return this.http.delete<Concept>(URL_BASE +  id)
            .pipe(
                catchError(err => this.handleError(err))
            );
    }
}

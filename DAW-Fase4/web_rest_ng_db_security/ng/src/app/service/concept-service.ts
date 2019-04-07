import { Injectable } from '@angular/core';
import { Concept } from "../model/concept.model";
import {catchError, map, switchAll} from "rxjs/operators";
import {Observable} from "rxjs";
import {Item} from "../model/item.model";
import {HttpClient, HttpHeaders, HttpRequest, HttpEvent} from "@angular/common/http";
import { Router } from '@angular/router';

const GET_CONCEPTS = "api/concept/lesson/{idLesson}/pag";
const GET_CONCEPT = "api/concept/";
const DELETE_CONCEPTS = "api/concept/";
const CREATE_CONCEPTS = "api/concept/";

@Injectable()
export class ConceptService {
    private urlEndPoint: string = 'https://localhost:8443/api/concept';

    private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

    constructor(private http: HttpClient, private router: Router) {}

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
                map((response: any) => response.concept as Concept),
                catchError(error => this.handleError(error))
            );
    }

    deleteConcepts(id:number){
        return this.http.delete<Concept>(DELETE_CONCEPTS +  id)
            .pipe(
                catchError(err => this.handleError(err))
            );
    }

    uploadFile(file:File, id): Observable<Concept>{
        let formData = new FormData();
        formData.append("file", file);
        formData.append("id", id);
        return this.http.post(`${this.urlEndPoint}/upload/`, formData).pipe(
            map( (Response: any) => Response.concept as Concept),
            
        );
    }
}

import { Injectable } from '@angular/core';
import { Concept } from "../model/concept.model";
import {catchError, map, switchAll} from "rxjs/operators";
import {Observable} from "rxjs";
import {Item} from "../model/item.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { environment } from "../../environments/environment";

const BASE_URL= environment.baseUrl;
const GET_CONCEPTS = BASE_URL + "/concept/pag";
const GET_CONCEPT = BASE_URL + "/concept/";
const DELETE_CONCEPTS = BASE_URL + "/concept/";
const CREATE_CONCEPTS = BASE_URL + "/concept/";

@Injectable()
export class ConceptService {
    private urlEndPoint: string = 'https://localhost:8443/api/concept';

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

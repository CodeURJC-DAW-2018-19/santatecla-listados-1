import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {Observable} from "rxjs";
import {Item} from "../model/item.model";
import {canReportError} from "rxjs/internal/util/canReportError";

const GET_ITEMS="api/items/concept/";
const URL_BASE="api/items/"


@Injectable()
export class ItemService {


    constructor(private http: HttpClient) {}

    getItems(id: number | string) {
        return this.http.get(GET_ITEMS + id , { withCredentials: true })
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );
    }


    private handleError(error: any) {
        console.error(error);
        return Observable.throw('Server error (' + error.status + '): ' + error.text());
    }


    addItem(item: Item, id:number):Observable<Item> {
        const body = JSON.stringify(item);

        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
        });
        return this.http.post<Item>(GET_ITEMS + id, body, {headers})
            .pipe(
                map(response => response),
                catchError(error => this.handleError(error))
            );

    }

    deleteItem(id:number){
        return this.http.delete<Item>(URL_BASE +  id)
            .pipe(
                catchError(err => this.handleError(err))
            );
    }
}

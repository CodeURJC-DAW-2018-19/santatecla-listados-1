import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, map} from "rxjs/operators";
import {Observable} from "rxjs";

const GET_ITEMS="api/items/concept/";


@Injectable()
export class ItemService {


    constructor(private http: HttpClient) {}

    getItems(id: number | string) {
        return this.http.get(GET_ITEMS + id + "?pag", { withCredentials: true })
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

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {Observable} from "rxjs";
import {UserRest} from "../model/user.model"

import { environment } from "../../environments/environment";


const BASE_URL = environment.baseUrl;
const URL = BASE_URL + '/user';

export interface User {
    idUser?: number;
    name: string;
    userType: string[];
    authdata: string;
}

@Injectable()
export class LoginService {

    idUser: number;
    isLogged = false;
    isAdmin = false;
    isUser = false;
    user: User;
    auth: string;

    constructor(private http: HttpClient) {
        let user = JSON.parse(localStorage.getItem('currentUser'));
        if (user) {
            console.log('Logged user');
            this.setCurrentUser(user);
        }
    }


    logIn(user: string, pass: string) {

        let auth = window.btoa(user + ':' + pass);

        const headers = new HttpHeaders({
            Authorization: 'Basic ' + auth,
            'X-Requested-With': 'XMLHttpRequest',
        });

        return this.http.get<User>(URL + '/login', {headers})
            .pipe(map(user => {
                if (user) {
                    this.setCurrentUser(user);
                    user.authdata = auth;
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }

                return user;
            }));
    }

    logOut() {
        return this.http.get(URL + '/logout').pipe(
            map(response => {
                this.removeCurrentUser();
                return response;
            }),
        );
    }


    private setCurrentUser(user: User) {
        this.isLogged = true;
        this.user = user;
        this.isAdmin = this.user.userType.indexOf('ROLE_TEACHER') !== -1;
        this.isUser = this.user.userType.indexOf('ROLE_STUDENT') !== -1;
    }

    removeCurrentUser() {
        localStorage.removeItem('currentUser');
        this.isLogged = false;
        this.isAdmin = false;
        this.isUser = false;
    }

    getRolUserLoged() {
        if (this.isAdmin)
            return 2;
        else if (this.isUser)
            return 1;
        else
            return 0;
    }

    signIn(u: UserRest): Observable<UserRest>{
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',
        });
        const body = JSON.stringify(u);
        return this.http.post<any>(URL + '/register',body,{headers})
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

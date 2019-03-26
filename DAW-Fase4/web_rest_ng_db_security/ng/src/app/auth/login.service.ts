import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

const URL = 'api/user';

export interface User {
    id?: number;
    name: string;
    userType: string[];
    authdata: string;
}

@Injectable()
export class LoginService {

    isLogged = false;
    isAdmin = false;
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

        return this.http.get<User>('api/user/login', { headers })
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
    }

    removeCurrentUser() {
        localStorage.removeItem('currentUser');
        this.isLogged = false;
        this.isAdmin = false;
    }
}

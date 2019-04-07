import {Injectable} from '@angular/core';
import {UserRest } from '../model/user.model';
import {HttpClient,HttpHeaders} from '@angular/common/http';

const URL="https://localhost:8080/api/";
const GET_LOGIN = URL+"user/login";

@Injectable()
export class UserService {

    constructor(private http: HttpClient) {}
    login(user: string, pass: string) {
        const userPass = user + ':' + pass;

        let headersObject = new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + btoa(userPass),
        });
        const httpOptions = {
            headers: headersObject
        }

        return this.http.get<any>(GET_LOGIN,httpOptions);
    }
}

function utf8_to_b64(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
        return String.fromCharCode(<any>'0x' + p1);
    }));
}

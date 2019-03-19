import {Injectable} from '@angular/core';
import {User} from '../model/user.model';
import {HttpClient,HttpHeaders} from '@angular/common/http';

const URL="https://localhost:8443/api/";
const GET_LOGIN = URL+"user/login"

@Injectable()
export class UserService {

  /*private users = [
    new User(1, 'Micael Gallego', 'micaelgallego@urjc.es', '1234', 'TEACHER'),
    new User(2, 'Aitana Cerezo', 'aitanaceca@urjc.es', '1234', 'STUDENT'),
    new User(3, 'Sandra Cañadas', 'sandracg@urjc.es', '1234', 'STUDENT'),
    new User(4, 'Héctor Mediero', 'hectormm@urjc.es', '1234', 'STUDENT'),
    new User(5, 'Ernesto Baltasar', 'ernestob@urjc.es', '1234', 'STUDENT'),
  ];

  getUsers() {
    return this.users;
  }

  getUser(id: number | string) {
    return this.users.find(user => user.id === +id);
  }*/

  constructor(private http: HttpClient) {}
  login(user: string, pass: string) {
    const userPass = user + ':' + pass;

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'my-auth-token'
      })

    return this.http.get<any>(GET_LOGIN,{headers: headers});
  }
}

function utf8_to_b64(str) {
  return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
    return String.fromCharCode(<any>'0x' + p1);
  }));
}

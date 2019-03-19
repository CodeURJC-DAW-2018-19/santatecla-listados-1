import { Injectable } from '@angular/core';
import { User } from "../model/user.model";


@Injectable()
export class UserService {

  private users = [
    new User (1, "Micael Gallego", "micaelgallego@urjc.es", "1234","TEACHER"),
    new User (2, "Aitana Cerezo", "aitanaceca@urjc.es", "1234","STUDENT"),
    new User (3, "Sandra Cañadas", "sandracg@urjc.es", "1234","STUDENT"),
    new User (4, "Héctor Mediero", "hectormm@urjc.es", "1234","STUDENT"),
    new User (5, "Ernesto Baltasar", "ernestob@urjc.es", "1234","STUDENT"),
  ];

  getUsers() {
    return this.users;
  }

  getUser(id: number | string) {
    return this.users.find(user => user.id === +id);
  }
}

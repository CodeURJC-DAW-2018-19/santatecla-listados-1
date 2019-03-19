import { Component, OnInit } from '@angular/core';
import {UserService} from '../service/user-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService) { }

  private user = "";
  private password = "";

  ngOnInit() {
  }

  logIn(){
    this.userService.login(this.user, this.password).subscribe(result=>{
      console.log(result);
    });

  }

}

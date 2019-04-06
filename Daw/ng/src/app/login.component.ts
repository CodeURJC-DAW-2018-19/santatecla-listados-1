import { Component, ViewChild, TemplateRef } from '@angular/core';
import { Router } from '@angular/router';
import {LoginService} from './auth/login.service';
import { MatDialog, MatDialogRef } from '@angular/material';
import { UserRest } from './model/user.model';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
})
export class LoginComponent {
    @ViewChild('loginDialog') loginDialog: TemplateRef<any>;
    dialogRef: MatDialogRef<any, any>;

    @ViewChild('signinDialog') signinDialog: TemplateRef<any>;
    dialogSign: MatDialogRef<any,any>;

    userNew: UserRest;

    constructor(public dialog: MatDialog, public router: Router, public loginService: LoginService, public dialogS:MatDialog) {
        this.userNew = {name:"", password:"",email:""};
    }

    logIn(event: any, user: string, pass: string) {
        event.preventDefault();

        this.loginService.logIn(user, pass).subscribe(
            (u) => {
                console.log(u);
                this.dialogRef.close();
            },
            (error) => alert('Invalid user or password'),
        );
    }

    logOut() {
        this.loginService.logOut().subscribe(
            (response) => {
                this.router.navigate(['/']);
            },
            (error) => console.log('Error when trying to log out: ' + error),
        );
    }

    openLoginDialog() {
        this.dialogRef = this.dialog.open(this.loginDialog, {
            width: '50%',
            height: '50%',
        });
    }

    openSignInDialog(){
        this.dialogSign=this.dialogS.open(
            this.signinDialog,
            {width: '50%' , height: '50%',}
        );
        this.dialogRef.close();
    }

    signIn(){
        this.loginService.signIn(this.userNew).subscribe(
            (u) =>{
                this.dialogSign.close();
            },
            error1 => console.log('Invalidad data'),
        );
    }


}

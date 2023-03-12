import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AlertyfyService } from 'src/app/Service/alertyfy.service';
import { AuthService } from 'src/app/Service/auth.service';
import { UserForLogin } from 'src/app/model/user';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  constructor(private auth: AuthService,private alertyfy: AlertyfyService,
              private router: Router) { }

  ngOnInit() {
  }

  onLogin(loginForm: NgForm){
    this.auth.authUser(loginForm.value).subscribe(
      (response: UserForLogin) => {
        console.log(response);
        const inUser = response;
        localStorage.setItem('token',inUser.token);
        localStorage.setItem('userName',inUser.userName);
        this.alertyfy.success("Login Successful");
        this.router.navigate(['/']);
      }
    );
    // if(user){
    //   localStorage.setItem('token',user.userName);
    //   this.alertyfy.success("Login Successful!");
    //   this.router.navigate(['/']);
    // }else{
    //   this.alertyfy.error("Some error Occurred!");
    // }
  }

}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User, UserForLogin, UserForRegister } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  baseUrl = environment.baseUrl;

  authUser(user: UserForLogin) {
    return this.http.post<UserForLogin>(this.baseUrl + '/api/account/login', user);
    // let userArray:Array<any> = [];
    // if(localStorage.getItem('Users')){
    //   userArray = JSON.parse(localStorage.getItem('Users') || '{}');
    // }
    // return userArray.find(p=>p.userName === user.userName && p.password === user.password);
  }

  registerUser(user:UserForRegister){
    return this.http.post(this.baseUrl + '/api/account/register',user);

  }
}

import { Component, OnInit } from '@angular/core';
import { AlertyfyService } from '../Service/alertyfy.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  loggedinUser: string;
  constructor(private alertyfy: AlertyfyService) { }

  ngOnInit() {
  }

  loggedIn(){
    this.loggedinUser = localStorage.getItem('userName') as string;
    return this.loggedinUser;
  }
  logOut(){
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
    this.alertyfy.success("You are logged out Now!");
  }

}

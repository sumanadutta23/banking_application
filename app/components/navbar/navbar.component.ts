import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn= false;
  user = null;

  constructor(public login:LoginService) { }

  ngOnInit(): void {
    
    this.isLoggedIn = this.login.isLoggedIn();
    this.user= this.login.getUser();
    this.login.loginStatusSubject.asObservable().subscribe((Data) => {
      this.isLoggedIn = this.login.isLoggedIn();
    this.user= this.login.getUser();
    });
  }

  public logoutUser(): void {
    this.login.logout();
    window.location.reload();
  }

}

import { HttpClient } from '@angular/common/http';
import { jsDocComment } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  url="http://localhost:9595"

  constructor(private http:HttpClient) { }

//current user: which is loggedin
  public getCurrentUser() {
    return this.http.get(`${this.url}/currentuser`);
  }

  //calling the server to generate token

  public generateToken(loginData:any) {
    //token generate
    return this.http.post(`${this.url}/token`,loginData);
  }
  
  //for login user
  public loginUser(token: string) {
    localStorage.setItem("token",token);
    
    return true;
  }

  // to check that is it logged in or not
 public isLoggedIn() 
  {
    let tokenstr =localStorage.getItem("token");
    if(tokenstr==undefined || tokenstr=='' || tokenstr==null)
    {
      return false;
    } else{
      return true;
    }
  }
  // for logout the user
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');

    return true;
  }

  //for getting the token
  public getToken()
  {
    return localStorage.getItem('token');
  }

  //set userdetails
  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  //get user
  public getUser() {
    let userstr = localStorage.getItem('user');
    if(userstr != null) {
      return JSON.parse(userstr);
    } else {
      this.logout();
      return null;
    }
  }

  // get user role

  public getUserRole() {
    let user = this.getUser();
    return user.authorities[0].authority;
  }
}

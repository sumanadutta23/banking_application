import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = "http://localhost:9595";

  constructor(private http: HttpClient) { }


  // add user
  public addUser(user: any) {
    return this.http.post(`${this.url}/user/addUser`, user);
  }
}

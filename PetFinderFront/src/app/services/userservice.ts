import { Injectable } from '@angular/core';
import { UserModel } from '../models/user-model';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';

export interface LoginResponse {
  id: number,
  fName: string,
  lName: string,
  email: string,
  password: string,
  phone: number

}

export interface LoginRequest {
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class Userservice {

  private url: string = "http://localhost:8080/api/users";
  private loggedUserSubject = new BehaviorSubject<UserModel | null>(null);

  loggedUser$: Observable<UserModel | null> = this.loggedUserSubject.asObservable();

  constructor(private http: HttpClient) { }

  /*
  getUser2(email: string): Observable<UserModel> {
    const params = { email: email };

    return this.http.get<UserModel>(this.url + "/login", {
      params: params
    });
  }
    */

  getUser(email: string): Observable<UserModel> {
    return this.http.get<UserModel>(this.url + `/${email}`).pipe(
      tap(user => this.loggedUserSubject.next(user))
    );
  }

  setLoggedUser(user: UserModel | null) {
    this.loggedUserSubject.next(user);
  }

  clearLoggedUser() {
    this.loggedUserSubject.next(null);
  }


}

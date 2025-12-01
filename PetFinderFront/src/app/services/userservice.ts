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

  getUserById(id: number): Observable<UserModel> {
    const userUrl = `${this.url}/id/${id}`;
    return this.http.get<UserModel>(userUrl);
  }

  registerUser(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(this.url + "/register", user);
  }


}

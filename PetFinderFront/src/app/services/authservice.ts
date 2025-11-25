import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserModel } from '../models/user-model';
import { BehaviorSubject, Observable } from 'rxjs';

export interface LoginResponse {
  token: string;
  message: string;
}

export interface LoginRequest {
  email: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class Authservice {

  private url: string = "http://localhost:8080/api/users";
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.hasToken());
  
  isAuthenticated$: Observable<boolean> = this.isAuthenticatedSubject.asObservable();

  constructor(private http: HttpClient) {}

    onLogin(email: string, password: string): Observable<LoginResponse> {
    const loginRequest: UserModel = {
      fName: '',
      lName: '',
      phone: '',
      email: email,
      password: password
    }

    return this.http.post<LoginResponse>(this.url + "/login", loginRequest);
  }

  storeToken(token: string): void {
    localStorage.setItem("token", token);
    this.isAuthenticatedSubject.next(true);
    console.log("Stored token: ", localStorage.getItem("token"));
  }

  private hasToken(): boolean {
    return !!localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
    this.isAuthenticatedSubject.next(false);
  }
  
}

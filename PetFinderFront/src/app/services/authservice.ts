import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { UserModel } from '../models/user-model';

export interface RegisterResponse {
  message: string;
  user?: {
    id: number;
    email: string;
  };
}

interface LoginResponse {
  token: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class Authservice {
  private tokenKey = 'jwt_token';
  private currentUserSubject = new BehaviorSubject<any>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient) {
    this.loadToken();
  }

  // FOR TESTING
  public isLogged(): boolean {
    const token = localStorage.getItem('jwt_token');

    if (token) {
      return true;
    }
    return false;

  }


  login(credentials: { email: string; password: string }): Observable<LoginResponse> {
    return this.http.post<LoginResponse>('http://localhost:8080/api/users/login', credentials)
      .pipe(
        tap(response => {
          this.saveToken(response.token);

          const loggedInUser: UserModel = {
            email: response.email,
            password: ''
          }

          this.currentUserSubject.next(loggedInUser);
          console.log(response.email);
        })
      );
  }

  private loadToken(): void {
    const token = this.getToken();
    if (token) {
      try {
        const decodedToken: any = jwt_decode(token); // Decode the token
        const email = decodedToken.email || decodedToken.sub; // Get email/subject claim

        const loggedInUser: UserModel = {
          email: email,
          password: '' // Password is never stored in the token or frontend
        };

        this.currentUserSubject.next(loggedInUser);
      } catch (error) {
        // Handle error if token is invalid/corrupted
        console.error('Failed to decode token:', error);
        this.logout(); // Clear the bad token
      }
    }
  }

  saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
    this.currentUserSubject.next(null);
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  register(credentials: { email: string; password: string }): Observable<RegisterResponse> {
    console.log("test: " + credentials.email + " " + credentials.password);

    return this.http.post<RegisterResponse>(
      'http://localhost:8080/api/users/register',  // Fixed URL
      credentials
    ).pipe(
      tap(response => {
        console.log('Registration successful:', response);
      }),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred';


    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      switch (error.status) {
        case 400:
          errorMessage = error.error?.message || 'Invalid email or password';
          break;
        case 409:
          errorMessage = 'Email already exists';
          break;
        case 500:
          errorMessage = 'Server error. Try again later';
          break;
        default:
          errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
      }
    }

    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}
function jwt_decode(token: string): any {
  throw new Error('Function not implemented.');
}


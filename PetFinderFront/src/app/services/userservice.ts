import { Injectable } from '@angular/core';
import { UserModel } from '../models/user-model';
import { Product } from '../models/product';
import { Pet } from '../models/pet';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class Userservice {
  user?: UserModel;
  isAuthenticated: boolean = false;
  pet: Pet[] = [];


  constructor(private http: HttpClient) { }

  async registerUser(user: UserModel): Promise<string> {
    const registerData = user;
    console.log('Sending:', registerData);

    try {
      // Expect plain text response; adjust if backend sends JSON
      const response = await lastValueFrom(
        this.http.post('http://localhost:8080/users/register', registerData, { responseType: 'text' })
      );
      console.log('Registration successful', response);
      return response || 'Success';  // Return backend message or default
    } catch (error: any) {
      console.error('Registration failed', error);
      // Extract useful message
      return error.error?.text || error.message || 'Registration failed';
    }
  }

  // Login
  public async login(user: UserModel): Promise<string>{
    const loginData = user;
    console.log('Sending:', loginData);

    try {
      // Expect plain text response; adjust if backend sends JSON
      const response = await lastValueFrom(
        this.http.post('http://localhost:8080/users/login', loginData, { responseType: 'text' })
      );
      console.log('Login successful', response);
      this.isAuthenticated = true;
      this.user = user;
      return response || 'Success';  // Return backend message or default
    } catch (error: any) {
      console.error('Login failed', error);
      // Extract useful message
      return error.error?.text || error.message || 'Login failed';
    }
  }

  public getIsAuthenticated(): boolean {
    return this.isAuthenticated;
  }

  public getLoggedUser(): UserModel | undefined {
    return this.user;
  }

    public setIsAuthenticated(isAuthenticated: boolean) {
    this.isAuthenticated = false;
  }

  public setLoggedUser(user: UserModel | undefined) {
    this.user = user;
  }

  // Add pet

  // Report pet lost
}

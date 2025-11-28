import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Authservice } from '../../services/authservice';
import { Router } from '@angular/router';
import { Userservice } from '../../services/userservice';
import { Observable } from 'rxjs';
import { UserModel } from '../../models/user-model';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
  loginForm: FormGroup;
  error: string = '';
  loggedUser$!: Observable<UserModel | null>

  constructor(private fb: FormBuilder, private authService: Authservice, private userService: Userservice,private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.loggedUser$ = this.userService.loggedUser$;
  }

 onSubmit() {
  const email = this.loginForm.value.email;
  const password = this.loginForm.value.password;

  this.authService.onLogin(email, password)
    .subscribe({
      next: (response) => {
        if (response.token) {
          this.authService.storeToken(response.token);
          this.userService.getUser(email).subscribe({
              next: (user) => {
                this.userService.setLoggedUser(user);
                this.router.navigate(['/']);
              },
              error: (err) => {
                console.error('Failed to load user data', err);
                this.error = 'Login successful but failed to load profile';
              }
            });
          this.router.navigate(["/"]);
        }
      },
      error: (err) => {
        console.error("Login failed:", err);
        this.error = "Incorrect Email and/or Password";
      }
    });
 }

}

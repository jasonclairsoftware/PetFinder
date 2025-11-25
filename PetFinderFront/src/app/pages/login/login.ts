import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Authservice } from '../../services/authservice';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
  loginForm: FormGroup;
  error: string = '';

  constructor(private fb: FormBuilder, private authService: Authservice, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

 onSubmit() {
  const email = this.loginForm.value.email;
  const password = this.loginForm.value.password;

  this.authService.onLogin(email, password)
    .subscribe({
      next: (response) => {
        if (response.token) {
          this.authService.storeToken(response.token);
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

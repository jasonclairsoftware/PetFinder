import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserModel } from '../models/user-model';
import { CommonModule } from '@angular/common';
import { Authservice } from '../services/authservice';
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
  if (this.loginForm.valid) {
    // Component calls the service. The service handles the token saving internally.
    this.authService.login(this.loginForm.value).subscribe({
      next: (response) => {
        // We only handle navigation after a successful login
        console.log("Login successful. Token saved by AuthService.");
        this.router.navigate(['/']); 
      },
      error: (err) => {
        // Handle and display error messages
        this.error = 'Login failed. Check username and/or password';
        console.error(err);
      }
    });
  }
}

}

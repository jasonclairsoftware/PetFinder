import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})
export class Register {
  registerForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {

      const loginData = this.registerForm.value;
      console.log(loginData);
      this.http.post('https://localhost:8443/register', loginData)
        .subscribe({
          next: (response) => {
            console.log('Login successful', response);
            // TODO: Handle successful login (e.g., store token, redirect)
          },
          error: (error) => {
            console.error('Login failed', error);
            // TODO: Handle error (e.g., show error message)
          }
        });

      console.log('Form submitted:', this.registerForm.value);
    }
  }
}

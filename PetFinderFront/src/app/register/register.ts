import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Authservice } from '../services/authservice';
import { UserModel } from '../models/user-model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})
export class Register {
  // PROPERTIES
  registerForm: FormGroup;
  errorMessage: string = '';

  // CTOR
  constructor(private authService: Authservice, private fb: FormBuilder, private router: Router) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    console.log("Form Information - " + "Email: " + this.registerForm.value.email + "; Password: " + this.registerForm.value.password);

    let user: UserModel = { id: 0, email: this.registerForm.value.email, password: this.registerForm.value.password };
    this.authService.register(this.registerForm.value).subscribe({
      next: res => {
        console.log('OK', res)
        this.router.navigate(['/login']);
      },
      error: err => console.error('FAIL', err)
    });
  }

}

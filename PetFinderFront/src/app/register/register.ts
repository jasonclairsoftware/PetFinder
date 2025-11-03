import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Userservice } from '../services/userservice';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.html',
  styleUrl: './register.scss'
})
export class Register {
  registerForm: FormGroup;
  isError: boolean = false;
  errorMessage: String = "";

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router, private userService: Userservice) {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  async onSubmit() {
    if (this.registerForm.valid) {

      const registerData = this.registerForm.value;
      let result: String;
      console.log("Test" + registerData);

      result = await this.userService.registerUser(registerData);

      console.log(result); // cant get error message

      if(result == "User Registration Successful") {
        this.router.navigate(["/login"]);
      } 
    }
  }
}

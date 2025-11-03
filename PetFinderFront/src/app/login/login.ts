import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Userservice } from '../services/userservice';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
  loginForm: FormGroup;
  isError: boolean = false;
  errorMessage: String = "";

  constructor(private fb: FormBuilder, private userservice: Userservice, private http: HttpClient, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });

  }

  async onSubmit() {
    if (this.loginForm.valid) {
      const loginData = this.loginForm.value;
      let result: String;
      console.log("Test" + loginData);

      result = await this.userservice.login(loginData);

      console.log(result); // cant get error message

      if (result == "User login Successful") {
        this.router.navigate(["/"]);
        console.log("Login Successful");
      }
    }
  }

}

import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Authservice } from '../../services/authservice';
import { UserModel } from '../../models/user-model';
import { Router } from '@angular/router';
import { Userservice } from '../../services/userservice';

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
  constructor(private userService: Userservice, private fb: FormBuilder, private router: Router) {
    this.registerForm = this.fb.group({
      fName: ['', [Validators.required]],
      lName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      phone: ['', [Validators.required]]
    });
  }

  onSubmit() {

    let user: UserModel = {
      fName: this.registerForm.value.fName,
      lName: this.registerForm.value.lName,
      email: this.registerForm.value.email,
      password: this.registerForm.value.password,
      phone: this.registerForm.value.phone
    };

    this.userService.registerUser(user).subscribe({
      next: (res) => {
        if(res)
          this.router.navigate(["/login"]);
      },
      error: (err) => {
        console.error(err);
      }
    });

  }

}

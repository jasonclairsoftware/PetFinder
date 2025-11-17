import { Component } from '@angular/core';
import { Pet } from '../models/pet';
import { Authservice } from '../services/authservice';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Petservice } from '../services/petservice';

@Component({
  selector: 'app-registerpet',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './registerpet.html',
  styleUrl: './registerpet.scss'
})
export class Registerpet {

   registerForm: FormGroup;
  errorMessage: string = '';

  constructor(private authService: Authservice, private fb: FormBuilder, private router: Router, private petService: Petservice) {
    this.registerForm = this.fb.group({
      id: [0],
      ownerId: [-1],
      name: ['', [Validators.required]],
      breed: ['', [Validators.required]],
      imageUrl: ['']
    });
  }


    onSubmit() {

      let ownerId: number = -1; // Get ownerId from signed in token authservice

      let pet: Pet = { 
        id: 0,
        ownerId: ownerId,
        name: this.registerForm.value.name, 
        breed: this.registerForm.value.breed, 
        imageUrl: this.registerForm.value.imageUrl };

        // Registering pet with pet service

      
    }

}

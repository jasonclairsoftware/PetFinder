import { Component, OnInit } from '@angular/core';
import { Pet } from '../../models/pet';
import { Authservice } from '../../services/authservice';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Petservice } from '../../services/petservice';
import { filter, Observable, switchMap, take } from 'rxjs';
import { UserModel } from '../../models/user-model';
import { Userservice } from '../../services/userservice';

@Component({
  selector: 'app-registerpet',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './registerpet.html',
  styleUrl: './registerpet.scss'
})
export class Registerpet implements OnInit {

  registerForm: FormGroup;
  errorMessage: string = '';
  loggedUser$!: Observable<UserModel | null>;


  constructor(private authService: Authservice, private fb: FormBuilder, private router: Router, private petService: Petservice, private userService: Userservice) {
    this.registerForm = this.fb.group({
      id: [0],
      ownerId: [-1],
      name: ['', [Validators.required]],
      breed: ['', [Validators.required]],
      imageUrl: [''],
      location: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loggedUser$ = this.userService.loggedUser$;
  }


  onSubmit() {
    this.loggedUser$.pipe(
      take(1),

      filter((user: UserModel | null): user is UserModel & { id: number } =>
        !!user && typeof user.id === 'number'),

      switchMap((user: UserModel & { id: number }) => {
        const newPet: Pet = {
          ownerId: user.id,
          name: this.registerForm.value.name,
          breed: this.registerForm.value.breed,
          imageUrl: this.registerForm.value.imageUrl,
          location: this.registerForm.value.location,
        };

        return this.petService.register(newPet);
      })

    ).subscribe({
      next: (registeredPet: Pet) => {
        console.log('Pet registered successfully:', registeredPet);
        this.router.navigate(["/pets"]);
      },
      error: (err: any) => {
        console.error('Registration failed:', err);
        this.errorMessage = 'Failed to register pet. Please try again.';
      }
    });
  }

}

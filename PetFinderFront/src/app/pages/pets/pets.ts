import { Component } from '@angular/core';
import { Pet } from '../../models/pet';
import { Observable, take } from 'rxjs';
import { UserModel } from '../../models/user-model';
import { Userservice } from '../../services/userservice';
import { Router, RouterLink } from '@angular/router';
import { Petservice } from '../../services/petservice';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pets',
  imports: [RouterLink, CommonModule],
  templateUrl: './pets.html',
  styleUrl: './pets.scss'
})
export class Pets {
  pets: Pet[] = [];
  loggedUser$!: Observable<UserModel | null>

  constructor(private userService: Userservice, private router: Router, private petService: Petservice) { }

  ngOnInit() {
    this.loggedUser$ = this.userService.loggedUser$;
    this.loadPetsForUser();
  }

  private getPets(ownerId: number) {
    this.petService.getPets(ownerId).subscribe({
      next: (response) => {
        this.pets = response;
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  loadPetsForUser(): void {
    this.loggedUser$.pipe(
      take(1)
    ).subscribe({
      next: (user: UserModel | null) => {
        if (user && user.id) {
          this.getPets(user.id);
        }
      },
      error: (err) => {
        console.error('Error getting logged user:', err);
      }
    });
  }


}

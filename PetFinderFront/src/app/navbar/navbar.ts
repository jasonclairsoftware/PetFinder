import { Component } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
import { Observable } from 'rxjs';
import { UserModel } from '../models/user-model';
import { Authservice } from '../services/authservice';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss'
})
export class Navbar {
  public currentUser$: Observable<UserModel | null>;

  constructor(private authService: Authservice, private router: Router) { 
    this.currentUser$ = authService.currentUser$;
  }

  logout() {
    this.authService.logout();
    this.router.navigate(["/login"]);
  }

  test() {
    console.log("Logged in with token: " + this.authService.isLogged());
  }

  registerPet() {
    if(this.authService.isLogged())
      this.router.navigate(["/registerpet"]);
  }

}

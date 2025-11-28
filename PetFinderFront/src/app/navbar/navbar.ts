import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
import { Observable } from 'rxjs';
import { Authservice } from '../services/authservice';
import { AsyncPipe } from '@angular/common';
import { UserModel } from '../models/user-model';
import { Userservice } from '../services/userservice';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, AsyncPipe],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss'
})
export class Navbar implements OnInit {

  isAuthenticated$!: Observable<boolean>;
  loggedUser$!: Observable<UserModel | null>;
  

  constructor(private authService: Authservice, private userService: Userservice,private router: Router) { }

  ngOnInit(): void {
    this.isAuthenticated$ = this.authService.isAuthenticated$;
    this.loggedUser$ = this.userService.loggedUser$;
  }

  onLogout(): void {
    this.authService.logout();
  }

  onLogin(): void {
    this.router.navigate(["/login"]);
  }

  test(): void {
    console.log("Test hit");
  }

}

import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
import { Observable } from 'rxjs';
import { Authservice } from '../services/authservice';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, AsyncPipe],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss'
})
export class Navbar implements OnInit {

  isAuthenticated$!: Observable<boolean>;

  constructor(private authService: Authservice, private router: Router) { }

  ngOnInit(): void {
    this.isAuthenticated$ = this.authService.isAuthenticated$;
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

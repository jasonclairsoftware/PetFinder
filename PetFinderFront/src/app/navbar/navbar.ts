import { Component } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Userservice } from '../services/userservice';
import { UserModel } from '../models/user-model';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss'
})
export class Navbar {

  constructor(private userservice: Userservice) { }



}

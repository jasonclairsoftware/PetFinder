import { Component } from '@angular/core';
import { Product } from '../models/product';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-store',
  imports: [ReactiveFormsModule],
  templateUrl: './store.html',
  styleUrl: './store.scss'
})
export class Store {
  public products: Product[] = [];
  private cart: Product[] = [];

  constructor(private router: Router) {}

  ngOnInit() {
    this.products = [
      {id: 1, name: "GPS Collar" },
      {id: 2, name: "LED collar"},
      {id: 3, name: "Teleportation Collar"}
    ];
  }

  addToCart(product: Product) {
    this.cart.push(product);
    console.log(product.name + " Added to cart")
  }

  goToCheckout() {
    this.router.navigate(["/checkout"]);
  }

}

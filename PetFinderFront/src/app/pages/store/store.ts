import { Component } from '@angular/core';
import { Product } from '../../models/product';
import { ReactiveFormsModule } from '@angular/forms';
import { Storeservice } from '../../services/storeservice';

@Component({
  selector: 'app-store',
  imports: [ReactiveFormsModule],
  templateUrl: './store.html',
  styleUrl: './store.scss'
})
export class Store {
  public products: Product[]= [];

  constructor(private storeService: Storeservice) { }

  ngOnInit() {
    this.products = this.storeService.getProducts();
  }

  addToCart(product: Product) {
    this.storeService.addToCart(product);
  }

}

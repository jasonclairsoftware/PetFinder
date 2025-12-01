import { Component } from '@angular/core';
import { Product } from '../../models/product';
import { ReactiveFormsModule } from '@angular/forms';
import { Storeservice } from '../../services/storeservice';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-store',
  imports: [ReactiveFormsModule],
  templateUrl: './store.html',
  styleUrl: './store.scss'
})
export class Store {
  public products: Product[] = [];
  cart$: Observable<Product[] | null>;

  constructor(private storeService: Storeservice) {
    this.cart$ = storeService.cart$;
  }

  ngOnInit() {
    this.products = this.storeService.getProducts();
  }

  addToCart(product: Product) {
    console.log(product.name + ": Added to Cart.");
  }

}

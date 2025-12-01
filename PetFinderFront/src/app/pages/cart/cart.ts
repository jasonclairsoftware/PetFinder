import { Component } from '@angular/core';
import { Storeservice } from '../../services/storeservice';
import { Observable } from 'rxjs';
import { Product } from '../../models/product';

@Component({
  selector: 'app-cart',
  imports: [],
  templateUrl: './cart.html',
  styleUrl: './cart.scss'
})
export class Cart {

  cart$: Observable<Product[] | null>;

  constructor(private storeService: Storeservice) {
    this.cart$ = storeService.cart$;

  }


}

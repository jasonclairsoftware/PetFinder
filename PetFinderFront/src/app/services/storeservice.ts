import { Injectable } from '@angular/core';
import { Product } from '../models/product';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class Storeservice {

    private cartSubject = new BehaviorSubject<Product[] | null>(null);
    cart$: Observable<Product[] | null> = this.cartSubject.asObservable();
    
  private products: Product[] = [
    { id: 1, name: "GPS Collar", description: "Tracks by GPS", imageUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-IEYAk39Dz5Z55fR_yLyJUtWgi-lqx7iekA&s", price: 19.00 },
    { id: 2, name: "LED collar", description: "Light up collar", imageUrl: "https://petsuppliesdealsandsteals.com/cdn/shop/files/75f28f803a233de5d75f52c2d635c40b.jpg?v=1722282531", price: 5.87 },
    { id: 3, name: "Teleportation Collar", description: "Collar from the future", imageUrl: "https://m.media-amazon.com/images/I/81iwjpHCBcL.jpg", price: 2000 }
  ];

  constructor(private http: HttpClient) {}

  public getProducts(): Product[] {
    return this.products;
  }

  addToCart(product: Product):void {
    const currentCart = this.cartSubject.value;

    let newCart: Product[];

    if (currentCart) {
      newCart = [...currentCart, product];
    } else {
      newCart = [product];
    }

    this.cartSubject.next(newCart)

  }


}

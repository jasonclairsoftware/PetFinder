import { Injectable } from '@angular/core';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class Storeservice {
  private products: Product[] = [
    { id: 1, name: "GPS Collar", description: "Tracks by GPS", imageUrl: "https://www.hanksbelts.com/cdn/shop/files/hanks-dog-collars-for-big-dogs-black_600x.jpg?v=1711454958", price: 19.00 },
    { id: 2, name: "LED collar", description: "Light up collar", imageUrl: "https://www.hanksbelts.com/cdn/shop/files/hanks-dog-collars-for-big-dogs-black_600x.jpg?v=1711454958", price: 5.87 },
    { id: 3, name: "Teleportation Collar", description: "Collar from the future", imageUrl: "https://www.hanksbelts.com/cdn/shop/files/hanks-dog-collars-for-big-dogs-black_600x.jpg?v=1711454958", price: 2000 }
  ];

  private cart: Product[] = [];

  public getProducts(): Product[] {
    return this.products;
  }

  public getCart(): Product[] {
    return this.cart;
  }

  public getCartCount(): number {
    return this.cart.length;
  }

  public addToCart(product: Product) {
    this.cart.push(product);
  }


}

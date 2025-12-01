import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Pet } from '../models/pet';

export interface RegisterResponse {
  id: number;
  ownerId: number;
  name: string;
  breed: string;
  imageUrl: string;
  location: string;
}

@Injectable({
  providedIn: 'root'
})
export class Petservice {

  private url: string = "http://localhost:8080/api/pets";

  constructor(private http: HttpClient) { }


  register(credentials: { name: string; breed: string, imageUrl: string, ownerId: number, location: string }): Observable<Pet> {
    return this.http.post<Pet>(this.url + '/register', credentials)
      .pipe(
        tap(response => {

          const newPet: Pet = {
            id: response.id,
            ownerId: response.ownerId,
            name: response.name,
            breed: response.breed,
            imageUrl: response.imageUrl,
            location: response.location
          }

        })
      );
  }

  getPets(id: number): Observable<Pet[]> {
    let params = new HttpParams().set('ownerId', id.toString());
    return this.http.get<Pet[]>(this.url + "/users", { params: params });
  }

  getAllPets(): Observable<Pet[]> {
    return this.http.get<Pet[]>(this.url);
  }

}

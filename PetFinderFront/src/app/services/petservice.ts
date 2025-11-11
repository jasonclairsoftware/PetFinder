import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Pet } from '../models/pet';

export interface RegisterResponse {
  id: number;
  ownerId: number;
  name: string;
  breed: string;
  imageUrl: string;
}

@Injectable({
  providedIn: 'root'
})
export class Petservice {

  constructor(private http: HttpClient) {

  }


    register(credentials: { name: string; breed: string, imageUrl: string }): Observable<RegisterResponse> {
      return this.http.post<RegisterResponse>('http://localhost:8080/api/pets/register', credentials)
        .pipe(
          tap(response => {
  
            const newPet: Pet = {
              id: response.id,
              ownerId: response.ownerId,
              name: response.name,
              breed: response.breed,
              imageUrl: response.imageUrl
            }
  
          })
        );
    }
  
}

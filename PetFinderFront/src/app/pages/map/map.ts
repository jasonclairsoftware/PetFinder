import { AfterViewInit, Component } from '@angular/core';
import * as L from 'leaflet';
import { Petservice } from '../../services/petservice';
import { Userservice } from '../../services/userservice';
import { Pet } from '../../models/pet';
import { Observable } from 'rxjs';
import { UserModel } from '../../models/user-model';

@Component({
  selector: 'app-map',
  imports: [],
  templateUrl: './map.html',
  styleUrl: './map.scss'
})
export class Map implements AfterViewInit {
  // Properties
  private map!: L.Map;
  private pets: Pet[] = [];
  private petMarkers: L.Marker[] = [];
  loggedUser$!: Observable<UserModel | null>;

  // CTOR
  constructor(private petService: Petservice, private userService: Userservice) { }

  // Methods
  private initMap(): void {

    this.map = L.map('map', {
      center: [33.51215880764414, -112.12951845087387],
      zoom: 12
    });

    const tiles = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      minZoom: 3,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    });

    tiles.addTo(this.map);

    this.locateUser();
  }

  ngAfterViewInit(): void {
    this.loggedUser$ = this.userService.loggedUser$;
    this.initMap();
    this.fetchPetsAndPlaceMarkers();
  }

  private fetchPetsAndPlaceMarkers() {
    this.petService.getAllPets().subscribe({
      next: (pets: Pet[]) => {
        this.pets = pets;

        this.pets.forEach(pet => {
          this.addPetMarker(pet);
        });

      },
      error: (err) => {
        console.error('Error fetching pets:', err);
      }
    });
  }

  private addPetMarker(pet: Pet) {
    const petIcon = L.icon({
      iconUrl: pet.imageUrl || 'default-pet-icon.png',
      iconSize: [40, 40],
      iconAnchor: [20, 40],
      popupAnchor: [0, -35]
    });

    let phone: string = '';
    this.userService.getUserById(pet.ownerId).subscribe({
      next: (response) => {
        phone = response.phone;
        const coords = pet.location.split(',').map(s => parseFloat(s.trim()));
        const marker = L.marker([coords[0], coords[1]], { icon: petIcon })
          .addTo(this.map)
          .bindPopup(`<b>${pet.name}</b><br>Contact: ${phone}`);

        this.petMarkers.push(marker);
      },
      error: (err) => {
        console.error(err);
      }
    });

  }

  private locateUser(): void {
    this.map.on('locationfound', (e) => this.onLocationFound(e));
    this.map.on('locationerror', (e) => this.onLocationError(e));

    this.map.locate({ setView: true, maxZoom: 16 });
  }

  private onLocationFound(e: L.LocationEvent): void {

    L.circle(e.latlng, { radius: e.accuracy / 2 }).addTo(this.map);
    L.marker(e.latlng).addTo(this.map)
      .bindPopup("You are here").openPopup();
  }

  private onLocationError(e: L.ErrorEvent): void {
    console.error(e.message);
    alert('Location access denied or unavailable. Showing default location.');
  }

}

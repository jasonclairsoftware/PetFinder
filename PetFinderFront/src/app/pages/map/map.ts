import { Component, AfterViewInit } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  imports: [],
  templateUrl: './map.html',
  styleUrl: './map.scss'
})
export class Map {
  private map!: L.Map;

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
    this.initMap();
  }

  private locateUser(): void {
    this.map.on('locationfound', (e) => this.onLocationFound(e));
    this.map.on('locationerror', (e) => this.onLocationError(e));

    this.map.locate({ setView: true, maxZoom: 16 });
  }

  private onLocationFound(e: L.LocationEvent): void {
    const radius = e.accuracy / 2; // Accuracy in meters

    // Good for reference to post pets ~ Jason C
    const myIcon = L.icon({
      iconUrl: 'Yuri.png',
      iconSize: [40, 40],
      iconAnchor: [10, 20],
      popupAnchor: [0, -20]
    });

    // Add your custom icon at the user's location
    L.marker(e.latlng, { icon: myIcon })
        .addTo(this.map)
        .bindPopup(`Yuri: (602)867-2345`);

  }

  private onLocationError(e: L.ErrorEvent): void {
    console.error(e.message);
    alert('Location access denied or unavailable. Showing default location.');
    // The map remains centered on the initial coordinates
  }

}

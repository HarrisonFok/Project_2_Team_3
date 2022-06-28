import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Place } from '../Place';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  places: any;
  private apiURL = `http://localhost:7000/map/landmark?q=Canada`;

  constructor(private http : HttpClient) { }

  getPlaces(places: string): Observable<any[]> {
    // console.log("Search Service:", places)
    // this.http.get(`http://localhost:7000/map/landmark?q=Canada`).subscribe(res => { const places = of(res); return places; });
    // console.log("Search Service: ", this.places)
    return this.http.get<any[]>(this.apiURL);
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Place } from '../Place';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  places: any;

  constructor(private http : HttpClient) { }

  getPlaces(places: string): Observable<any[]> {
    this.http.get(`http://localhost:7000/map/landmark?q=Canada`).subscribe(res => { this.places = res; });
    return this.places;
  }
}

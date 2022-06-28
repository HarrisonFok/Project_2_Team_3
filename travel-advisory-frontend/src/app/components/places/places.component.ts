import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { SearchService } from 'src/app/services/search.service';
import { Place } from 'src/app/Place';

@Component({
  selector: 'app-places',
  templateUrl: './places.component.html',
  styleUrls: ['./places.component.css']
})
export class PlacesComponent implements OnInit {
  searchPlace: string = "";
  places: any;

  // constructor(private http : HttpClient) { }
  constructor(private searchService: SearchService, private http : HttpClient) { }

  ngOnInit(): void {
    console.log(this.searchPlace)
    this.http.get(`http://localhost:7000/map/landmark?q=${this.searchPlace}`).subscribe(res => { this.places = res; console.log(this.places) });
    // this.searchService.getPlaces("Canada").subscribe((val) => {
    //   this.places = val;
    // });
    // this.places = this.searchService.getPlaces("Canada");
    // console.log(this.searchService.getPlaces("Canada"));
    // this.searchService.getPlaces(this.searchPlace).subscribe((places) => this.places = places);
  }
 
}

import { Component, OnInit } from '@angular/core';
import { Place } from 'src/app/Place';

@Component({
  selector: 'app-places',
  templateUrl: './places.component.html',
  styleUrls: ['./places.component.css']
})
export class PlacesComponent implements OnInit {
  places: Place[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}

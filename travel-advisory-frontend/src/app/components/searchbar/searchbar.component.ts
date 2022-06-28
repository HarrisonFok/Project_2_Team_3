import { Component, OnInit, Input } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent implements OnInit {

  searchPlace: string = "";
  result: any;

  constructor(private http : HttpClient){
  }

  ngOnInit(): void {
  }

  search() {
    console.log(this.searchPlace);
    this.http.get(`http://localhost:7000/map/landmark?q=${this.searchPlace}`).subscribe(res => { this.result = res; console.log(this.result) });
    // this.http.get(`http://localhost:7000/map/state-info?state=${this.searchPlace}&format=json&countrycodes=us`).subscribe(res => {console.log(res)});
    // this.http.get(`http://localhost:7000/map/${this.searchPlace}`).subscribe(res => {console.log(res)});
  }
}
